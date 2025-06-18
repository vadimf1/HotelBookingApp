package intexsoft.practice.util.exceptionCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ClientExceptionCode {
    CLIENT_NOT_FOUNT_BY_ID("Client not found with ID: ");

    private final String message;
}
