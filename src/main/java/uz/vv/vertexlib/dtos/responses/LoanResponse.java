package uz.vv.vertexlib.dtos.responses;

import java.time.Instant;

public record LoanResponse(
        String id,
        BookShortResponse book,
        UserShortResponse member,
        UserShortResponse staff,
        Instant loanDate,
        Instant dueDate,
        Instant returnDate,
        Double fineAmount
) {}