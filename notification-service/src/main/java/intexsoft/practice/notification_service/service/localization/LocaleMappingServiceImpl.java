package intexsoft.practice.notification_service.service.localization;

import intexsoft.practice.notification_service.entity.LocaleMapping;
import intexsoft.practice.notification_service.repository.LocalMappingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@RequiredArgsConstructor
public class LocaleMappingServiceImpl implements LocaleMappingService {

    private final LocalMappingRepository localMappingRepository;

    @Override
    public Locale getLocaleForCountry(String countryCode) {
        String locale = localMappingRepository.findByCountryCode(countryCode)
                .map(LocaleMapping::getLocaleCode)
                .orElse("en");

        return Locale.forLanguageTag(locale);
    }
}
