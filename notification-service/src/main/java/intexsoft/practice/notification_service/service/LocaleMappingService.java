package intexsoft.practice.notification_service.service;

import java.util.Locale;

public interface LocaleMappingService {
    Locale getLocaleForCountry(String countryCode);
}
