package uz.vv.vertexlib.exceptions;

/**
 * Kitobning mavjud nusxalari tugaganda tashlanadi (availableCopies <= 0).
 * HTTP 422 Unprocessable Entity qaytaradi.
 */
public class InsufficientStockException extends BaseException {

    public InsufficientStockException(String message) {
        super(message);
    }

    /**
     * Kitob nomi bo'yicha standart xabar hosil qiluvchi factory method.
     * Ikkita bir xil imzoli konstruktor bo'lishining oldini olish uchun static factory ishlatiladi.
     */
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
