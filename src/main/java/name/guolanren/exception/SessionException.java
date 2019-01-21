package name.guolanren.exception;

/**
 * @autoro guolanren
 * @date 2019-01-18
 */
public class SessionException extends RuntimeException {

    private static final long serialVersionUID = 7484041979257823063L;

    public SessionException() {
        super();
    }

    public SessionException(String message, Throwable cause,
                            boolean enableSuppression,
                            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public SessionException(String message, Throwable cause) {
        super(message, cause);
    }

    public SessionException(String message) {
        super(message);
    }

    public SessionException(Throwable cause) {
        super(cause);
    }
}
