package labs.prime.primebackend.domain.user.entity.exception;

import labs.prime.primebackend.global.error.exception.ErrorCode;
import labs.prime.primebackend.global.error.exception.GlobalException;

public class UserNotCorrectException extends GlobalException {
    public static final GlobalException EXCEPTION = new UserNotCorrectException();

    private UserNotCorrectException() {
        super(ErrorCode.USER_NOT_CORRECT);
    }
}
