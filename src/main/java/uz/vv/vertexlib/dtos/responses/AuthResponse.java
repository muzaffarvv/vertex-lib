package uz.vv.vertexlib.dtos.responses;

/**
 * Muvaffaqiyatli login/register javobi — JWT token va foydalanuvchi ma'lumotlari.
 */
public record AuthResponse(
        String token,
        String tokenType,   // doim "Bearer"
        UserResponse uresp
) {
    public static AuthResponse of(String token, UserResponse user) {
        return new AuthResponse(token, "Bearer", user);
    }
}
