package intexsoft.practice.notification_service.service.impl;

import intexsoft.practice.notification_service.entity.LoginNotification;
import intexsoft.practice.notification_service.repository.LoginNotificationRepository;
import intexsoft.practice.notification_service.service.LoginNotificationDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LoginNotificationDataServiceImpl implements LoginNotificationDataService {

    private final LoginNotificationRepository loginNotificationRepository;

    @Override
    @Transactional
    public void saveLoginNotification(LoginNotification loginNotification) {
        loginNotificationRepository.save(loginNotification);
    }
}

