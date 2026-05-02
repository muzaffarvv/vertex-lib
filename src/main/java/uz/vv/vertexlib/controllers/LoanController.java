package uz.vv.vertexlib.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.vv.vertexlib.dtos.requests.LoanCreateRequest;
import uz.vv.vertexlib.dtos.requests.LoanUpdateRequest;
import uz.vv.vertexlib.dtos.responses.LoanResponse;
import uz.vv.vertexlib.services.LoanService;

import java.util.List;

/**
 * Kitob ijarasi endpoint-lari.
 * Barcha endpointlar faqat STAFF roli uchun (SecurityConfig-da belgilangan).
 */
@RestController
@RequestMapping("/api/v1/loans")
@RequiredArgsConstructor
public class LoanController {

    private final LoanService loanService;

    /**
     * POST /api/v1/loans — Kitob ijarasini rasmiylashtirish (STAFF).
     * memberId = MEMBER foydalanuvchi, staffId = STAFF foydalanuvchi bo'lishi shart.
     * availableCopies avtomatik kamaytiriladi.
     */
    @PostMapping
    public ResponseEntity<LoanResponse> createLoan(@Valid @RequestBody LoanCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(loanService.createLoan(request));
    }

    /**
     * PUT /api/v1/loans/{id}/return — Kitobni qaytarish (STAFF).
     * returnDate va fineAmount o'rnatiladi, availableCopies oshiriladi.
     */
    @PutMapping("/{id}/return")
    public ResponseEntity<LoanResponse> returnBook(
            @PathVariable String id,
            @Valid @RequestBody LoanUpdateRequest request
    ) {
        return ResponseEntity.ok(loanService.returnBook(id, request));
    }

    /** GET /api/v1/loans/{id} — Ijara yozuvini olish (STAFF) */
    @GetMapping("/{id}")
    public ResponseEntity<LoanResponse> getById(@PathVariable String id) {
        return ResponseEntity.ok(loanService.getById(id));
    }

    /** GET /api/v1/loans — Barcha ijara yozuvlari (STAFF) */
    @GetMapping
    public ResponseEntity<List<LoanResponse>> getAll() {
        return ResponseEntity.ok(loanService.getAll());
    }
}
