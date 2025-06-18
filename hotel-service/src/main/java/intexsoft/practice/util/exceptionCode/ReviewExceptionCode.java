package intexsoft.practice.util.exceptionCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ReviewExceptionCode {
    REVIEW_NOT_FOUNT_BY_ID("Review not found with ID: ");

    private final String message;
}
