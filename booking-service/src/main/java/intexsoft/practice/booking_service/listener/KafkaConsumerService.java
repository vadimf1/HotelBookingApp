package intexsoft.practice.booking_service.listener;

import intexsoft.practice.booking_service.dto.RoomAvailabilityDTO;
import intexsoft.practice.booking_service.listener.cache.KafkaResponseCache;
import intexsoft.practice.booking_service.service.BookingService;
import intexsoft.practice.booking_service_kafka_dto.dto.KafkaRoomIdRequestDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class KafkaConsumerService {

    private final BookingService bookingService;
    private final KafkaResponseCache kafkaResponseCache;

    public KafkaConsumerService(BookingService bookingService,  KafkaResponseCache kafkaResponseCache) {
        this.bookingService = bookingService;
        this.kafkaResponseCache = kafkaResponseCache;
    }

    @KafkaListener(topics = "hotel.roomId.request", containerFactory = "kafkaRoomIdRequestDTOFactory")
    public void handleRoomIdRequest(KafkaRoomIdRequestDTO requestDTO) {
        log.warn(requestDTO.getRoomId());

        UUID roomId = UUID.fromString(requestDTO.getRoomId());

        RoomAvailabilityDTO bookedPeriods = bookingService.getBookedPeriods(roomId);

        kafkaResponseCache.getCache().put(roomId, bookedPeriods);
    }
}
