package intexsoft.practice.notification_service.service.impl;

import intexsoft.practice.notification_service.config.ipinfo.IpInfoProperties;
import intexsoft.practice.notification_service.dto.IpInfoResponse;
import intexsoft.practice.notification_service.exception.IpInfoRetrievalException;
import intexsoft.practice.notification_service.service.IpInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;


@Service
@RequiredArgsConstructor
@Slf4j
public class IpInfoServiceImpl implements IpInfoService {

    private final RestTemplate restTemplate;
    private final IpInfoProperties ipInfoProperties;

    @Override
    public IpInfoResponse getIpInfo(String ip) {
        String url = ipInfoProperties.getApi() + ip;
        try {
            return restTemplate.getForObject(url, IpInfoResponse.class);
        } catch (RestClientException e) {
            log.error("Ошибка получения информации по IP: {}", ip, e);
            throw new IpInfoRetrievalException("Не удалось получить информацию по IP: " + ip, e);
        }
    }
}
