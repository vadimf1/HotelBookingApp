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
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

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

        validateDates(requestDTO);

        checkRoomAvailability(requestDTO);

        BookingStatusEntity bookingStatus = bookingStatusService.getByCode(BookingStatus.CONFIRMED);

        RoomBooking roomBooking = saveBooking(requestDTO, bookingStatus);

        sendKafkaEvent(roomBooking);

        return roomBookingMapper.toDTO(roomBooking);
    }

    @Override
    @Transactional
    public BookingResponseDTO cancelBooking(UUID bookingId) {
        RoomBooking roomBooking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new EntityNotFoundException("Бронирование не найдено"));

        if (roomBooking.getCheckOutDate().isBefore(LocalDate.now())) {
            throw new InvalidBookingRequestException("Нельзя отменить бронирование после даты выезда");
        }

        BookingStatusEntity bookingStatus = bookingStatusService.getByCode(BookingStatus.CANCELLED);

        roomBooking.setBookingStatus(bookingStatus);
        roomBooking.setUpdatedAt(LocalDateTime.now());
        roomBooking = bookingRepository.save(roomBooking);

        KafkaBookingEventDTO eventDTO = kafkaEventMapper.toKafkaEventDTOWithCancelledType(roomBooking);
        kafkaProducerService.sendBookingEvent(eventDTO);

        return roomBookingMapper.toDTO(roomBooking);
    }

    private void validateDates(BookingRequestDTO requestDTO) {
        if (requestDTO.getCheckInDate().isAfter(requestDTO.getCheckOutDate())) {
            throw new IllegalArgumentException("Дата заселения не должна быть позже даты выезда");
        }
    }

    private void checkRoomAvailability(BookingRequestDTO requestDTO) {
        if (bookingRepository.existsByRoomIdOverlappingDates(
                requestDTO.getRoomId(), requestDTO.getCheckInDate(), requestDTO.getCheckOutDate())) {
            throw new InvalidBookingRequestException("Номер уже занят на указанные даты");
        }
    }

    private RoomBooking saveBooking(BookingRequestDTO requestDTO, BookingStatusEntity bookingStatus) {
        RoomBooking roomBooking = roomBookingMapper.toEntity(requestDTO, bookingStatus);

        roomBooking = bookingRepository.save(roomBooking);
        bookingRepository.flush();

        return roomBooking;
    }

    private void sendKafkaEvent(RoomBooking roomBooking) {
        KafkaBookingEventDTO eventDTO = kafkaEventMapper.toKafkaEventDTOWithCreatedType(roomBooking);

        kafkaProducerService.sendBookingEvent(eventDTO);
    }
}
