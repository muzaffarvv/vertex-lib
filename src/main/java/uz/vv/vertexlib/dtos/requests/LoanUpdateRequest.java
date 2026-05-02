package uz.vv.vertexlib.dtos.requests;

import java.time.Instant;

public record LoanUpdateRequest(
        Instant returnDate,
        Double fineAmount
) {}
