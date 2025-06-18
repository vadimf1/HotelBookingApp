package intexsoft.practice.util.exceptionCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum RoomTypeExceptionCode {
    ID_FIELD_EXPECTED_NULL("ID field expected null"),
    ROOM_TYPE_NOT_FOUNT_BY_ID("Room type not found with ID: ");

    private final String message;
}
