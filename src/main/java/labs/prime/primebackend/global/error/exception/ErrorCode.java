package labs.prime.primebackend.global.error.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    INTERNAL_SERVER_ERROR(500, "Internal Server Error."),
    POST_NOT_FOUND(404, "Post Not Found."),
    POST_NOT_DELETED(401, "Post Not Deleted."),
    POST_NOT_UPDATED(401, "Post Not Updated."),
    USER_NOT_AUTHORIZED(403, "User Not Authorized."),
    USER_NOT_CORRECT(401, "USER NOT CORRECT.");

    private final int status;
    private final String message;
}
