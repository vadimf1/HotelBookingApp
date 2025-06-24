package intexsoft.practice.exception.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum RoomExceptionCode {
    ROOM_NOT_FOUNT_BY_ID("Room not found with ID: ");

    private final String message;
}
