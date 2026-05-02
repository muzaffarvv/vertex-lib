package uz.vv.vertexlib.exceptions;

/**
 * Barcha custom exception-lar shu abstract sinfdan meros oladi.
 * HTTP status kodi har bir subclass tomonidan aniqlanadi.
 */
public abstract class BaseException extends RuntimeException {

    protected BaseException(String message) {
        super(message);
    }

    protected BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @return bu exception uchun mos HTTP status kodi
     */
    public abstract int getStatusCode();
}
