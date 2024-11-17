package labs.prime.primebackend.global.error.exception;

public class PageNotFoundException extends GlobalException{
    public static final GlobalException EXCEPTION = new PageNotFoundException();

    public PageNotFoundException() {
        super(ErrorCode.PAGE_NOT_FOUND);
    }
}
