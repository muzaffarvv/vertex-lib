package uz.vv.vertexlib.exceptions;

/**
 * Resurs (entity) bazada topilmagan holatlarda tashlanadi.
 * HTTP 404 Not Found qaytaradi.
 */
public class ResourceNotFoundException extends BaseException {

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s '%s' = '%s' bilan topilmadi", resourceName, fieldName, fieldValue));
    }

    @Override
    public int getStatusCode() {
        return 404;
    }
}
