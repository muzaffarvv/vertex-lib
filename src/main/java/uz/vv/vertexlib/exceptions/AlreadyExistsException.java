package uz.vv.vertexlib.exceptions;

public class AlreadyExistsException extends BaseException {

    public AlreadyExistsException(String message) {
        super(message);
    }

    public AlreadyExistsException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s allaqachon mavjud: %s = '%s'", resourceName, fieldName, fieldValue));
    }

    @Override
    public int getStatusCode() {
        return 409;
    }
}
