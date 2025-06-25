package intexsoft.practice.booking_service.service;

import intexsoft.practice.booking_service.model.BookingStatus;
import intexsoft.practice.booking_service.model.BookingStatusEntity;
import intexsoft.practice.booking_service.repository.BookingStatusRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BookingStatusService {

    private final BookingStatusRepository bookingStatusRepository;
    private Map<BookingStatus, BookingStatusEntity> statusCache;

    @PostConstruct
    public void init() {
        List<BookingStatusEntity> statuses = bookingStatusRepository.findAll();
        this.statusCache = statuses.stream()
                .collect(Collectors.toMap(BookingStatusEntity::getCode, Function.identity()));
    }

    public BookingStatusEntity getByCode(BookingStatus code) {
        return Optional.ofNullable(statusCache.get(code))
                .orElseThrow(() -> new IllegalStateException("Статус не найден: " + code));
    }
}
