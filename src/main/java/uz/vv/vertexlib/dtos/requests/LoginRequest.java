package uz.vv.vertexlib.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

/**
 * Login so'rovi uchun DTO — telefon raqami va parol.
 */
public record LoginRequest(

        @NotBlank(message = "Telefon raqami kiritilishi shart")
        @Pattern(regexp = "^998[0-9]{9}$", message = "Telefon raqami 998XXXXXXXXX formatida bo'lishi kerak")
        String phoneNumber,

        @NotBlank(message = "Parol kiritilishi shart")
        String password
) {}
