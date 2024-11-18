package labs.prime.primebackend.domain.post.exception;

import labs.prime.primebackend.global.error.exception.ErrorCode;
import labs.prime.primebackend.global.error.exception.GlobalException;

public class PostNotFoundException extends GlobalException {
    public static final GlobalException EXCEPTION = new PostNotFoundException();

    private PostNotFoundException() {
        super(ErrorCode.POST_NOT_FOUND);
    }
}
