package intexsoft.practice.user_service.service;

import intexsoft.practice.user_service.dto.request.RegisterUserRequest;
import intexsoft.practice.user_service.dto.response.JwtAuthenticationResponse;

public interface UserService {

    JwtAuthenticationResponse register(RegisterUserRequest registerUserRequest);
}
