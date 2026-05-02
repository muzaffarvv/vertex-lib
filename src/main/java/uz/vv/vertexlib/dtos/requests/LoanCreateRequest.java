package uz.vv.vertexlib.dtos.requests;

import java.time.Instant;

public record LoanCreateRequest(
        String bookId,
        String memberId,
        String staffId,
        Instant dueDate
) {}
