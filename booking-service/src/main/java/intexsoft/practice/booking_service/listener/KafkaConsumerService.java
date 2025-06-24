package intexsoft.practice.booking_service.listener;

import intexsoft.practice.booking_service.dto.RoomAvailabilityDTO;
import intexsoft.practice.booking_service.listener.cache.KafkaResponseCache;
import intexsoft.practice.booking_service.service.BookingService;
import intexsoft.practice.booking_service_kafka_dto.dto.KafkaRoomIdResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
public class KafkaConsumerService {

    private final BookingService bookingService;
    private final KafkaResponseCache kafkaResponseCache;

    @KafkaListener(topics = "hotel.roomId.request", containerFactory = "kafkaRoomIdResponseDTOFactory")
    public void handleRoomIdRequest(KafkaRoomIdResponseDTO requestDTO) {
        log.warn(requestDTO.getRoomId());

        UUID roomId = UUID.fromString(requestDTO.getRoomId());

        RoomAvailabilityDTO bookedPeriods = bookingService.getBookedPeriods(roomId);

        kafkaResponseCache.getCache().put(roomId, bookedPeriods);
    }
}
