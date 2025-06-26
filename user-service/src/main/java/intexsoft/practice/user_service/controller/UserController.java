package intexsoft.practice.user_service.controller;

import intexsoft.practice.user_service.dto.request.LoginUserRequest;
import intexsoft.practice.user_service.dto.request.RegisterUserRequest;
import intexsoft.practice.user_service.dto.response.JwtAuthenticationResponse;
import intexsoft.practice.user_service.dto.response.UserEmailResponse;
import intexsoft.practice.user_service.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("api/auth/register")
    public ResponseEntity<JwtAuthenticationResponse> registerUser(
            @Valid @RequestBody RegisterUserRequest registerUserRequest) {
        return ResponseEntity.ok(userService.register(registerUserRequest));
    }

    @PostMapping("api/auth/login")
    public ResponseEntity<JwtAuthenticationResponse> loginUser(
            @Valid @RequestBody LoginUserRequest loginUserRequest,
            HttpServletRequest request) {
        return ResponseEntity.ok(userService.login(loginUserRequest, request));
    }

    @GetMapping("user-service/{userId}")
    public ResponseEntity<UserEmailResponse> getUserEmail(@PathVariable("userId") UUID userId) {
        return ResponseEntity.ok(userService.getUserEmailById(userId));
    }
}
