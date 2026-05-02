package uz.vv.vertexlib.dtos.requests;

import jakarta.validation.constraints.*;

public record BookCreateRequest(
        @NotBlank(message = "Kitob nomi kiritilishi shart")
        @Size(max = 172, message = "Kitob nomi 172 belgidan oshmasligi kerak")
        String title,

        @NotBlank(message = "ISBN kiritilishi shart")
        @Size(min = 13, max = 17, message = "ISBN formati noto'g'ri")
        String isbn,

        @NotBlank(message = "Muallif kiritilishi shart")
        @Size(max = 132)
        String author,

        @NotBlank(message = "Janr kiritilishi shart")
        String genreId,

        @Min(value = 1000, message = "Yil noto'g'ri kiritildi")
        @Max(value = 2026, message = "Kelajakdagi yilni kiritib bo'lmaydi")
        Integer publishedYear,

        @NotNull(message = "Nusxalar soni kiritilishi shart")
        @PositiveOrZero(message = "Nusxalar soni manfiy bo'lishi mumkin emas")
        Integer totalCopies
) {}
