package intexsoft.practice.user_service.service;

import intexsoft.practice.user_service.dto.request.LoginUserRequest;
import intexsoft.practice.user_service.dto.request.RegisterUserRequest;
import intexsoft.practice.user_service.dto.response.JwtAuthenticationResponse;
import intexsoft.practice.user_service.dto.response.UserEmailResponse;
import jakarta.servlet.http.HttpServletRequest;

import java.util.UUID;

public interface UserService {

    JwtAuthenticationResponse register(RegisterUserRequest registerUserRequest);

    JwtAuthenticationResponse login(LoginUserRequest loginUserRequest, HttpServletRequest httpServletRequest);

    UserEmailResponse getUserEmailById(UUID userId);
}
