package uz.vv.vertexlib.exceptions;

import java.time.Instant;

/**
 * GlobalExceptionHandler tomonidan qaytariladigan standart JSON xato javobi.
 */
public record ErrorResponse(
        Instant timestamp,
        int status,
        String message,
        String details
) {
    /**
     * Qulay factory method — timestamp avtomatik set qilinadi.
     */
    public static ErrorResponse of(int status, String message, String details) {
        return new ErrorResponse(Instant.now(), status, message, details);
    }
}
