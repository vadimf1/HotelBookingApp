package intexsoft.practice.exception.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum AddressExceptionCode {
    ID_FIELD_EXPECTED_NULL("ID field expected null"),
    ADDRESS_NOT_FOUNT_BY_ID("Address not found with ID: ");

    private final String message;
}
