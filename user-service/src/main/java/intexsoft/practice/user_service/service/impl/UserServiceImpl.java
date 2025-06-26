package intexsoft.practice.user_service.service.impl;

import intexsoft.practice.user_service.dto.request.RegisterUserRequest;
import intexsoft.practice.user_service.dto.response.JwtAuthenticationResponse;
import intexsoft.practice.user_service.entity.User;
import intexsoft.practice.user_service.entity.enums.UserRole;
import intexsoft.practice.user_service.mapper.UserMapper;
import intexsoft.practice.user_service.repository.UserRepository;
import intexsoft.practice.user_service.security.JwtProvider;
import intexsoft.practice.user_service.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final JwtProvider jwtProvider;

    @Override
    @Transactional
    public JwtAuthenticationResponse register(@Valid RegisterUserRequest registerUserRequest) {
        if (userRepository.existsByEmail(registerUserRequest.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        User user = userMapper.toEntity(registerUserRequest);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(UserRole.ROLE_USER);
        userRepository.save(user);

        return new JwtAuthenticationResponse(jwtProvider.generateToken(user.getEmail(), user.getRole()));
    }
}
