package uz.vv.vertexlib.exceptions;

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
