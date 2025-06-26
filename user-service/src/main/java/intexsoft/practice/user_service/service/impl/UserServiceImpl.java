package intexsoft.practice.user_service.service.impl;

import intexsoft.practice.dto.notification.AccountLoginNotification;
import intexsoft.practice.kafka_producer_lib.service.KafkaProducerService;
import intexsoft.practice.user_service.dto.request.LoginUserRequest;
import intexsoft.practice.user_service.dto.request.RegisterUserRequest;
import intexsoft.practice.user_service.dto.response.JwtAuthenticationResponse;
import intexsoft.practice.user_service.dto.response.UserEmailResponse;
import intexsoft.practice.user_service.entity.User;
import intexsoft.practice.user_service.entity.enums.UserRole;
import intexsoft.practice.user_service.mapper.UserMapper;
import intexsoft.practice.user_service.repository.UserRepository;
import intexsoft.practice.user_service.security.JwtProvider;
import intexsoft.practice.user_service.security.UserDetailsImpl;
import intexsoft.practice.user_service.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final JwtProvider jwtProvider;
    private final AuthenticationManager authenticationManager;
    private final KafkaProducerService kafkaProducerService;

    @Override
    @Transactional
    public JwtAuthenticationResponse register(@Valid RegisterUserRequest registerUserRequest) {
        if (userRepository.existsByEmail(registerUserRequest.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        User user = userMapper.toEntity(registerUserRequest);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Set.of(UserRole.ROLE_USER));
        userRepository.save(user);

        return new JwtAuthenticationResponse(jwtProvider.generateToken(user.getId(),
                user.getEmail(), user.getRoles()));
    }

    @Override
    @Transactional
    public JwtAuthenticationResponse login(LoginUserRequest loginRequest, HttpServletRequest httpRequest) {
        try {

            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(),
                            loginRequest.getPassword()
                    );

            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            User user = userDetails.getUser();

            String token = jwtProvider.generateToken(user.getId(), user.getEmail(), user.getRoles());

            AccountLoginNotification event = new AccountLoginNotification(
                    user.getId(),
                    getClientIp(httpRequest),
                    httpRequest.getHeader("User-Agent"),
                    Instant.now()
            );
            kafkaProducerService.send(user.getId().toString(), event);

            log.info("User {} successfully logged in", user.getEmail());

            return new JwtAuthenticationResponse(token);

        } catch (BadCredentialsException e) {
            log.warn("Invalid login attempt for email: {}", loginRequest.getEmail());
            throw new RuntimeException("Invalid email or password");
        } catch (Exception e) {
            log.error("Login error for email: {}", loginRequest.getEmail(), e);
            throw new RuntimeException("Login failed");
        }
    }

    @Override
    public UserEmailResponse getUserEmailById(UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return new UserEmailResponse(user.getEmail());
    }

    private String getClientIp(HttpServletRequest request) {
        String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader == null) {
            return request.getRemoteAddr();
        }
        return xfHeader.split(",")[0];
    }
}

