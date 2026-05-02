package uz.vv.vertexlib.dtos.requests;

import jakarta.validation.constraints.*;
import java.time.Instant;

public record LoanUpdateRequest(
        @PastOrPresent(message = "Qaytarish sanasi kelajakda bo'lishi mumkin emas")
        Instant returnDate,

        @PositiveOrZero(message = "Jarima miqdori manfiy bo'lishi mumkin emas")
        Double fineAmount
) {}