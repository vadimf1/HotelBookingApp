package intexsoft.practice.exception.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum HotelExceptionCode {
    HOTEL_NOT_FOUNT_BY_ID("Hotel not found with ID: ");

    private final String message;
}