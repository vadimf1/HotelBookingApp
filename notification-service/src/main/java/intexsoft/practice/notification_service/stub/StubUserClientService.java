package intexsoft.practice.notification_service.stub;

import intexsoft.practice.notification_service.dto.UserDto;
import intexsoft.practice.notification_service.service.UserClientService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Profile("dev")
@Service
public class StubUserClientService implements UserClientService {
    @Override
    public UserDto getUserById(UUID userId) {
        UserDto user = new UserDto();
        user.setEmail("default@example.com");
        return user;
    }
}
