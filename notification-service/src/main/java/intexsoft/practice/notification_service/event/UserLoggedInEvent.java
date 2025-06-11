package intexsoft.practice.notification_service.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserLoggedInEvent {
    private Long userId;
    private String timestamp;
    private String ip;
    private String userAgent;
    private String country;
}
