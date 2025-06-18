package intexsoft.practice.util.exceptionCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum AmenityExceptionCode {
    AMENITY_NOT_FOUNT_BY_ID("Amenity not found with ID: ");

    private final String message;
}