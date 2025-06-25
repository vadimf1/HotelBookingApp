package intexsoft.practice.booking_service.exception;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class ErrorResponse {

    private LocalDateTime timestamp;
    private String message;
}
