package intexsoft.practice.booking_service.service.impl;

import intexsoft.practice.booking_service.dto.BookingRequestDTO;
import intexsoft.practice.booking_service.dto.BookingResponseDTO;
import intexsoft.practice.booking_service.dto.KafkaBookingEventDTO;
import intexsoft.practice.booking_service.model.BookingStatus;
import intexsoft.practice.booking_service.model.RoomBooking;
import intexsoft.practice.booking_service.repository.BookingRepository;
import intexsoft.practice.booking_service.service.BookingService;
import intexsoft.practice.booking_service.service.producer.KafkaProducerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final KafkaProducerService kafkaProducerService;

    @Override
    public BookingResponseDTO createBooking(BookingRequestDTO requestDTO) {
        RoomBooking roomBooking = new RoomBooking();
        roomBooking.setUserId(requestDTO.getUserId());
        roomBooking.setCheckInDate(requestDTO.getCheckInDate());
        roomBooking.setCheckOutDate(requestDTO.getCheckOutDate());
        roomBooking.setRoomNumber(requestDTO.getRoomNumber());
        roomBooking.setRoomStatus(BookingStatus.CONFIRMED);
        roomBooking.setCreatedAt(LocalDateTime.now());

        RoomBooking savedBooking = bookingRepository.save(roomBooking);

        BookingResponseDTO responseDTO = new BookingResponseDTO();
        responseDTO.setBookingId(savedBooking.getBookingId());
        responseDTO.setUserId(savedBooking.getUserId());
        responseDTO.setCheckInDate(savedBooking.getCheckInDate());
        responseDTO.setCheckOutDate(savedBooking.getCheckOutDate());
        responseDTO.setRoomNumber(savedBooking.getRoomNumber());
        responseDTO.setRoomStatus(savedBooking.getRoomStatus());
        responseDTO.setCreatedAt(savedBooking.getCreatedAt());

        KafkaBookingEventDTO eventDTO = new KafkaBookingEventDTO();
        eventDTO.setEventType("BOOKING_CREATED");
        eventDTO.setBookingId(savedBooking.getBookingId());
        eventDTO.setUserId(savedBooking.getUserId());
        eventDTO.setCheckInDate(savedBooking.getCheckInDate());
        eventDTO.setCheckOutDate(savedBooking.getCheckOutDate());
        eventDTO.setEventTime(LocalDateTime.now());

        kafkaProducerService.sendBookingEvent(eventDTO);

        return responseDTO;
    }
}
