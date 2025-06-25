package intexsoft.practice.exception.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum AmenityStatusExceptionCode {
    ID_FIELD_EXPECTED_NULL("ID field expected null"),
    AMENITY_STATUS_NOT_FOUNT_BY_ID("Amenity status not found with ID: ");

    private final String message;
}