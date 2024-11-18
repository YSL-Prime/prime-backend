package labs.prime.primebackend.global.error;

import labs.prime.primebackend.global.error.exception.ErrorCode;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

@Getter
@Builder
public class ErrorResponse {
    private int status;
    private String code;
    private String message;
    private LocalDateTime createLocalDateTime;

    public static ResponseEntity<ErrorResponse> responseEntity(ErrorCode errorCode) {
        return ResponseEntity
                .status(errorCode.getStatus())
                .body(ErrorResponse.builder()
                        .status(errorCode.getStatus())
                        .code(errorCode.name())
                        .message(errorCode.getMessage())
                        .createLocalDateTime(LocalDateTime.now())
                        .build()
                );
    }
}
