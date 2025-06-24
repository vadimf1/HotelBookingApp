package intexsoft.practice.booking_service.controller;

import intexsoft.practice.booking_service.dto.BookingRequestDTO;
import intexsoft.practice.booking_service.dto.BookingResponseDTO;
import intexsoft.practice.booking_service.dto.RoomAvailabilityDTO;
import intexsoft.practice.booking_service.listener.cache.KafkaResponseCache;
import intexsoft.practice.booking_service.service.BookingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.UUID;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;
    private final KafkaResponseCache kafkaResponseCache;
    private final JwtUtil jwtUtil;

    @Autowired
    public BookingController(BookingService bookingService, KafkaResponseCache kafkaResponseCache, JwtUtil jwtUtil) {
        this.bookingService = bookingService;
        this.kafkaResponseCache = kafkaResponseCache;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping
    public BookingResponseDTO createBooking(@Valid @RequestBody BookingRequestDTO requestDTO,
                                            @RequestHeader("Authorization") String authHeader) {
        String token;
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7); // Убираем "Bearer "
        } else {
            token = authHeader; // Используем как есть (для тестов)
        }

        UUID userId = UUID.fromString(jwtUtil.extractUserId(token));

        return bookingService.createBooking(requestDTO, userId);
    }

    @PutMapping("cancel/{bookingId}")
    public BookingResponseDTO cancelBooking(@PathVariable UUID bookingId) {
        return bookingService.cancelBooking(bookingId);
    }

    @GetMapping("/booked-periods")
    public RoomAvailabilityDTO getBookedPeriods(@RequestParam UUID roomId) {
        return bookingService.getBookedPeriods(roomId);
    }

    @GetMapping("/kafka-result/{roomId}")
    public RoomAvailabilityDTO getKafkaResult(@PathVariable UUID roomId) {
        return kafkaResponseCache.getCache().getOrDefault(roomId, new RoomAvailabilityDTO(Collections.emptyList()));
    }
}
