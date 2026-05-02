package uz.vv.vertexlib.exceptions;

public class BadRequestException extends BaseException {

    public BadRequestException(String message) {
        super(message);
    }

    @Override
    public int getStatusCode() {
        return 400;
    }
}
