package intexsoft.practice.notification_service.service;

import intexsoft.practice.notification_service.dto.IpInfoResponse;

public interface IpInfoService {
    IpInfoResponse getIpInfo(String ip);
}
