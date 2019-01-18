package name.guolanren.exception;

/**
 * @author 郭耀展
 * @create 2019-01-17
 */
public class RequestException extends RuntimeException {

    private static final long serialVersionUID = 4900067270557008643L;

    public RequestException() {
        super();
    }

    public RequestException(String message, Throwable cause,
                            boolean enableSuppression,
                            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public RequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public RequestException(String message) {
        super(message);
    }

    public RequestException(Throwable cause) {
        super(cause);
    }
}
