package uz.vv.vertexlib.dtos.responses;

public record AuthResponse(
        String token,
        String tokenType,   // doim "Bearer"
        UserResponse uresp
) {
    public static AuthResponse of(String token, UserResponse user) {
        return new AuthResponse(token, "Bearer", user);
    }
}
