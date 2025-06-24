package intexsoft.practice.notification_service;

import intexsoft.practice.notification_service.service.ip.IpInfoService;
import intexsoft.practice.notification_service.service.localization.LocaleMappingService;
import intexsoft.practice.notification_service.service.localization.LocalizedMessageService;
import intexsoft.practice.notification_service.service.mail.FreeMarkerMailContentBuilder;
import intexsoft.practice.notification_service.service.mail.MailSenderService;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import static org.mockito.Mockito.mock;

@TestConfiguration
public class TestMocksConfig {

    @Bean
    @Primary
    public MailSenderService mailSenderService() {
        return mock(MailSenderService.class);
    }

    @Bean
    @Primary
    public LocaleMappingService localeMappingService() {
        return mock(LocaleMappingService.class);
    }

    @Bean
    @Primary
    public LocalizedMessageService localizedMessageService() {
        return mock(LocalizedMessageService.class);
    }

    @Bean
    @Primary
    public FreeMarkerMailContentBuilder contentBuilder() {
        return mock(FreeMarkerMailContentBuilder.class);
    }

    @Bean
    @Primary
    public IpInfoService ipInfoService() {
        return mock(IpInfoService.class);
    }
}
