package uz.vv.vertexlib.exceptions;

/**
 * Noto'g'ri so'rov parametrlari yoki biznes mantiq xatolari uchun.
 * HTTP 400 Bad Request qaytaradi.
 */
public class BadRequestException extends BaseException {

    public BadRequestException(String message) {
        super(message);
    }

    @Override
    public int getStatusCode() {
        return 400;
    }
}
