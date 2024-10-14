package site.dadangsinhhoc.exception;

public class SwaggerInitializationException extends RuntimeException {
    public SwaggerInitializationException(String message) {
        super(message);
    }

    public SwaggerInitializationException(String message, Throwable cause) {
        super(message, cause);
    }
}
