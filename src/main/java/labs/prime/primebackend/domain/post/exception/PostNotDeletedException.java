package labs.prime.primebackend.domain.post.exception;

import labs.prime.primebackend.global.error.exception.ErrorCode;
import labs.prime.primebackend.global.error.exception.GlobalException;

public class PostNotDeletedException extends GlobalException {
    public static final GlobalException EXCEPTION = new PostNotDeletedException();

    private PostNotDeletedException() {
        super(ErrorCode.POST_NOT_DELETED);
    }
}
