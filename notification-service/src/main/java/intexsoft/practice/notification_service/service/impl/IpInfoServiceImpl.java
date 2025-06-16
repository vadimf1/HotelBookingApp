package intexsoft.practice.notification_service.service.impl;

import intexsoft.practice.notification_service.config.IpInfoProperties;
import intexsoft.practice.notification_service.service.IpInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class IpInfoServiceImpl implements IpInfoService {

    private final RestTemplate restTemplate;
    private final IpInfoProperties ipInfoProperties;

    @Override
    public String getCountryAndCity(String ip) {
        String url = ipInfoProperties.getApi() + ip + "/json";
        Map<String, Object> response = restTemplate.getForObject(url, Map.class);
        String country = response.getOrDefault("country_name", "").toString();
        String city = response.getOrDefault("city", "").toString();

        return country + ", " + city;
    }
}
