package intexsoft.practice.notification_service.service;

import java.util.List;
import java.util.Locale;
import java.util.Map;

public interface LocalizedMessageService {
    String getLocalizedMessage(String code, Locale locale);
    Map<String, String> getBulk(List<String> keys, Locale locale);
}
