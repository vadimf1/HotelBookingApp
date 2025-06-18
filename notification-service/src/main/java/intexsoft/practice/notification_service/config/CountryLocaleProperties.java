package intexsoft.practice.notification_service.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@ConfigurationProperties(prefix = "locale.mapping")
@Getter
@Setter
public class CountryLocaleProperties {

    private Map<String, String> countries = new HashMap<>();

    public Locale getLocale(String countryCode) {
        String language = countries.getOrDefault(countryCode, "en");
        return Locale.forLanguageTag(language);
    }
}
