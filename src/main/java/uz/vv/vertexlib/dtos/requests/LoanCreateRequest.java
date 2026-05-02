package uz.vv.vertexlib.dtos.requests;

import jakarta.validation.constraints.*;
import java.time.Instant;

public record LoanCreateRequest(
        @NotBlank(message = "Kitob bo'sh bo'lishi mumkin emas")
        String bookId,

        @NotBlank(message = "A'zo bo'sh bo'lishi mumkin emas")
        String memberId,

        @NotBlank(message = "Xodim bo'sh bo'lishi mumkin emas")
        String staffId,

        @NotNull(message = "Qaytarish sanasi belgilanishi shart")
        @Future(message = "Qaytarish sanasi kelajakda bo'lishi kerak")
        Instant dueDate
) {}