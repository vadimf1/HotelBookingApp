package intexsoft.practice.booking_service.repository;

import feign.Param;
import intexsoft.practice.booking_service.dto.BookedPeriodDTO;
import intexsoft.practice.booking_service.model.RoomBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface BookingRepository extends JpaRepository<RoomBooking, UUID> {
    @Query("SELECT CASE WHEN COUNT(b) > 0 THEN TRUE ELSE FALSE END " +
            "FROM RoomBooking b " +
            "WHERE b.roomId = :roomId AND " +
            "b.bookingStatus.code != 'CANCELLED' AND " +
            "(b.checkInDate <= :checkOutDate AND b.checkOutDate >= :checkInDate)")
    boolean existsByRoomIdOverlappingDates(
            @Param("roomId") UUID roomId,
            @Param("checkIdDate") LocalDate checkInDate,
            @Param("checkOutDate") LocalDate checkOutDate
    );

    @Query("SELECT new intexsoft.practice.booking_service.dto.BookedPeriodDTO(b.checkInDate, b.checkOutDate) " +
            "FROM RoomBooking b " +
            "WHERE b.roomId = :roomId AND b.checkOutDate >= CURRENT_DATE")
    List<BookedPeriodDTO> findBookedPeriodsByRoomId(UUID roomId);

}
