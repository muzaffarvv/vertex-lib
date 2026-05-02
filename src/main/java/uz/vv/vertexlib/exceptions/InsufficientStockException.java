package uz.vv.vertexlib.exceptions;

public class InsufficientStockException extends BaseException {

    public InsufficientStockException(String message) {
        super(message);
    }

    public static InsufficientStockException forBook(String bookTitle) {
        return new InsufficientStockException(
                String.format("'%s' kitobining mavjud nusxalari tugagan", bookTitle)
        );
    }

    @Override
    public int getStatusCode() {
        return 422;
    }
}
