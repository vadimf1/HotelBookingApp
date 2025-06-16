package intexsoft.practice.booking_service.service.impl;

import intexsoft.practice.booking_service.dto.BookingRequestDTO;
import intexsoft.practice.booking_service.dto.BookingResponseDTO;
import intexsoft.practice.booking_service.dto.KafkaBookingEventDTO;
import intexsoft.practice.booking_service.mapper.KafkaEventMapper;
import intexsoft.practice.booking_service.mapper.RoomBookingMapper;
import intexsoft.practice.booking_service.model.BookingStatus;
import intexsoft.practice.booking_service.model.BookingStatusEntity;
import intexsoft.practice.booking_service.model.RoomBooking;
import intexsoft.practice.booking_service.repository.BookingRepository;
import intexsoft.practice.booking_service.repository.BookingStatusRepository;
import intexsoft.practice.booking_service.service.BookingService;
import intexsoft.practice.booking_service.service.producer.KafkaProducerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final BookingStatusRepository bookingStatusRepository;
    private final KafkaProducerService kafkaProducerService;

    @Override
    public BookingResponseDTO createBooking(BookingRequestDTO requestDTO) {
        if (bookingRepository.existsByRoomIdOverlappingDates(
                requestDTO.getRoomId(), requestDTO.getCheckInDate(), requestDTO.getCheckOutDate())) {
            throw new RuntimeException("Номер уже занят на указанные даты");
        }

        BookingStatusEntity bookingStatus = bookingStatusRepository.findByCode(BookingStatus.CONFIRMED)
                .orElseThrow(() -> new RuntimeException("Статус не найден"));

        RoomBooking roomBooking = RoomBookingMapper.toEntity(requestDTO, bookingStatus);

        RoomBooking savedBooking = bookingRepository.save(roomBooking);

        KafkaBookingEventDTO eventDTO = KafkaEventMapper.toKafkaEventWithCreatedType(savedBooking);

        kafkaProducerService.sendBookingEvent(eventDTO);

        return RoomBookingMapper.toDTO(savedBooking);
    }
}
