package name.guolanren.exception;

/**
 * @author 郭耀展
 * @create 2019-01-17
 */
public class InternalException extends RuntimeException {

    private static final long serialVersionUID = 8357096485790269600L;

    public InternalException(String message, Throwable cause,
                             boolean enableSuppression,
                             boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public InternalException(String message, Throwable cause) {
        super(message, cause);
    }

    public InternalException(String message) {
        super(message);
    }

    public InternalException(Throwable cause) {
        super(cause);
    }
}
