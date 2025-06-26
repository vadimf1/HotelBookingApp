package intexsoft.practice.user_service.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterUserRequest {

    @NotBlank(message = "First name is required")
    @Size(min = 4, max = 50, message = "First name should be between 4 and 50 characters")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(min = 4, max = 50, message = "Last name should be between 4 and 50 characters")
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 60, message = "Password must be between 8 and 60 characters")
    private String password;
}
