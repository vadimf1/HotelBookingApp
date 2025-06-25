package intexsoft.practice.booking_service.controller;

import intexsoft.practice.booking_service.dto.BookingRequestDTO;
import intexsoft.practice.booking_service.dto.BookingResponseDTO;
import intexsoft.practice.booking_service.dto.RoomAvailabilityDTO;
import intexsoft.practice.booking_service.service.BookingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public BookingResponseDTO createBooking(@Valid @RequestBody BookingRequestDTO requestDTO) {
        Authentication auth =  SecurityContextHolder.getContext().getAuthentication();
        UUID userId = UUID.fromString(auth.getPrincipal().toString());

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
}
