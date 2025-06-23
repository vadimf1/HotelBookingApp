package intexsoft.practice.notification_service.service.localization;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LocalizedMessageServiceImpl implements LocalizedMessageService {

    private final MessageSource messageSource;

    @Override
    public String getLocalizedMessage(String code, Locale locale) {
        return messageSource.getMessage(code, null, "unknown", locale);
    }

    @Override
    public Map<String, String> getBulk(List<String> keys, Locale locale) {
        return keys.stream()
                .collect(Collectors.toMap(
                        key -> key,
                        key -> getLocalizedMessage(key, locale)
                ));
    }
}
