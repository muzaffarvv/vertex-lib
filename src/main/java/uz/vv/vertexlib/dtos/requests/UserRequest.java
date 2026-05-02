package uz.vv.vertexlib.dtos.requests;

import jakarta.validation.constraints.*;
import uz.vv.vertexlib.enums.UserRole;

public record UserRequest(
        @NotBlank(message = "To'liq ism kiritilishi shart")
        @Size(min = 3, max = 132, message = "Ism 3 dan 132 gacha belgi bo'lishi kerak")
        String fullName,

        @NotBlank(message = "Telefon raqami kiritilishi shart")
        @Pattern(regexp = "^998[0-9]{9}$", message = "Telefon raqami 998 XX XXX XX XX formatida bo'lishi kerak")
        String phoneNumber,

        @NotBlank(message = "Parol kiritilishi shart")
        @Size(min = 6, max = 64, message = "Parol 6 dan kam bo'lmasligi kerak")
        @Pattern(
                regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$",
                message = "Parolda kamida bitta harf va bitta son bo'lishi shart"
        )
        String password,

        @NotNull(message = "Roli ko'rsatilishi shart")
        UserRole role
) {

}