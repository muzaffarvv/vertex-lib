package uz.vv.vertexlib.exceptions;

public class InsufficientStockException extends BaseException {

    public InsufficientStockException(String message) {
        super(message);
    }

    public static InsufficientStockException forMovie(String movieTitle) {
        return new InsufficientStockException(
                String.format("'%s' kitobining mavjud nusxalari tugagan", movieTitle)
        );
    }

    @Override
    public int getStatusCode() {
        return 422;
    }
}
