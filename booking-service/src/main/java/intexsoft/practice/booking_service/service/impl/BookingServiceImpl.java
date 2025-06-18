package intexsoft.practice.booking_service.service.impl;

import intexsoft.practice.booking_service.dto.BookingRequestDTO;
import intexsoft.practice.booking_service.dto.BookingResponseDTO;
import intexsoft.practice.booking_service.exception.InvalidBookingRequestException;
import intexsoft.practice.booking_service.mapper.KafkaEventMapper;
import intexsoft.practice.booking_service.mapper.RoomBookingMapper;
import intexsoft.practice.booking_service.model.BookingStatus;
import intexsoft.practice.booking_service.model.BookingStatusEntity;
import intexsoft.practice.booking_service.model.RoomBooking;
import intexsoft.practice.booking_service.repository.BookingRepository;
import intexsoft.practice.booking_service.service.BookingService;
import intexsoft.practice.booking_service.service.BookingStatusService;
import intexsoft.practice.booking_service_kafka_dto.dto.KafkaBookingEventDTO;
import intexsoft.practice.booking_service_kafka_producer.service.KafkaProducerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final BookingStatusService bookingStatusService;
    private final KafkaProducerService kafkaProducerService;
    private final RoomBookingMapper roomBookingMapper;
    private final KafkaEventMapper kafkaEventMapper;

    @Override
    @Transactional
    public BookingResponseDTO createBooking(BookingRequestDTO requestDTO) {
        if (requestDTO.getCheckInDate().isAfter(requestDTO.getCheckOutDate())) {
            throw new IllegalArgumentException("Дата заселения не должна быть позже даты выезда");
        }

        if (bookingRepository.existsByRoomIdOverlappingDates(
                requestDTO.getRoomId(), requestDTO.getCheckInDate(), requestDTO.getCheckOutDate())) {
            throw new InvalidBookingRequestException("Номер уже занят на указанные даты");
        }

        BookingStatusEntity bookingStatus = bookingStatusService.getByCode(BookingStatus.CONFIRMED);

        RoomBooking roomBooking = roomBookingMapper.toEntity(requestDTO, bookingStatus);

        roomBooking = bookingRepository.save(roomBooking);
        bookingRepository.flush();

        KafkaBookingEventDTO eventDTO = kafkaEventMapper.toKafkaEventDTOWithCreatedType(roomBooking);

        kafkaProducerService.sendBookingEvent(eventDTO);

        return roomBookingMapper.toDTO(roomBooking);
    }
}
