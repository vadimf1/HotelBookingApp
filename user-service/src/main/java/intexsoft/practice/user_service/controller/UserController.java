package intexsoft.practice.user_service.controller;

import intexsoft.practice.user_service.dto.request.RegisterUserRequest;
import intexsoft.practice.user_service.dto.response.JwtAuthenticationResponse;
import intexsoft.practice.user_service.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<JwtAuthenticationResponse> registerUser(
            @Valid @RequestBody RegisterUserRequest registerUserRequest) {
        return ResponseEntity.ok(userService.register(registerUserRequest));
    }


}
