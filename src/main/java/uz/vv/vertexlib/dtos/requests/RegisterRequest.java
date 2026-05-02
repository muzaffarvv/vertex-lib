package uz.vv.vertexlib.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * Ro'yxatdan o'tish so'rovi uchun DTO.
 * Foydalanuvchi roli avtomatik MEMBER qilib belgilanadi.
 */
public record RegisterRequest(

        @NotBlank(message = "To'liq ism kiritilishi shart")
        @Size(min = 3, max = 132, message = "Ism 3 dan 132 gacha belgi bo'lishi kerak")
        String fullName,

        @NotBlank(message = "Telefon raqami kiritilishi shart")
        @Pattern(regexp = "^998[0-9]{9}$", message = "Telefon raqami 998XXXXXXXXX formatida bo'lishi kerak")
        String phoneNumber,

        @NotBlank(message = "Parol kiritilishi shart")
        @Size(min = 6, message = "Parol kamida 6 belgidan iborat bo'lishi kerak")
        @Pattern(
                regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$",
                message = "Parolda kamida bitta harf va bitta son bo'lishi shart"
        )
        String password
) {}
