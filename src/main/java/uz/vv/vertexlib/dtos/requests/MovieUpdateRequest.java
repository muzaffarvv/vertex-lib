package uz.vv.vertexlib.dtos.requests;

import jakarta.validation.constraints.*;

public record MovieUpdateRequest(
        @Size(max = 172, message = "Kitob nomi 172 belgidan oshmasligi kerak")
        String title,

        @Size(max = 132, message = "Muallif ismi 132 belgidan oshmasligi kerak")
        String author,

        String genreId,

        @Min(value = 1000, message = "Yil noto'g'ri kiritildi")
        @Max(value = 2026, message = "Kelajakdagi yilni kiritib bo'lmaydi")
        Integer publishedYear,

        @PositiveOrZero(message = "Nusxalar soni manfiy bo'lishi mumkin emas")
        Integer totalCopies
) {}

