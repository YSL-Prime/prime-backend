package labs.prime.primebackend.domain.user.entity.exception;

import labs.prime.primebackend.domain.post.exception.PostNotDeletedException;
import labs.prime.primebackend.global.error.exception.ErrorCode;
import labs.prime.primebackend.global.error.exception.GlobalException;

public class UserAuthorizedException extends GlobalException {
    public static final GlobalException EXCEPTION = new UserAuthorizedException();

    public UserAuthorizedException() {
        super(ErrorCode.USER_NOT_AUTHORIZED);
    }
}
