package labs.prime.primebackend.global.error;

import labs.prime.primebackend.global.error.exception.GlobalException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(GlobalException.class)
    protected ResponseEntity<ErrorResponse> handlerCustomException(GlobalException customException){
        return ErrorResponse.responseEntity(customException.getErrorCode());
    }
}
