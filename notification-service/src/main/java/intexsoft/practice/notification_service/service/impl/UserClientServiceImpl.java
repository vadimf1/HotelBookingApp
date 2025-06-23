package intexsoft.practice.notification_service.service.impl;

import intexsoft.practice.notification_service.config.client.UserClientProperties;
import intexsoft.practice.notification_service.dto.UserDto;
import intexsoft.practice.notification_service.service.UserClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserClientServiceImpl implements UserClientService {

    private final RestTemplate restTemplate;
    private final UserClientProperties userClientProperties;

    @Override
    public UserDto getUserById(UUID userId) {
        String url = userClientProperties.getApi() + "/" + userId;
        return restTemplate.getForObject(url, UserDto.class);
    }
}
