package intexsoft.practice.util.exceptionCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum RoomStatusExceptionCode {
    ID_FIELD_EXPECTED_NULL("ID field expected null"),
    ROOM_STATUS_NOT_FOUNT_BY_ID("Room status not found with ID: ");

    private final String message;
}
