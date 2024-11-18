package labs.prime.primebackend.domain.post.exception;

import labs.prime.primebackend.global.error.exception.ErrorCode;
import labs.prime.primebackend.global.error.exception.GlobalException;

public class PostNotUpdatedException extends GlobalException {
    public final static GlobalException globalException = new PostNotUpdatedException();

    public PostNotUpdatedException() {
        super(ErrorCode.POST_NOT_UPDATED);
    }
}
