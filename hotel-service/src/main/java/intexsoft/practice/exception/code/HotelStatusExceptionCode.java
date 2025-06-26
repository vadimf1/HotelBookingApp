package intexsoft.practice.exception.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum HotelStatusExceptionCode {
    ID_FIELD_EXPECTED_NULL("ID field expected null"),
    HOTEL_STATUS_NOT_FOUNT_BY_ID("Hotel status not found with ID: ");

    private final String message;
}