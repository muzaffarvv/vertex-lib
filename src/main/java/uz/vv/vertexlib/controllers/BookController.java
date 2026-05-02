package uz.vv.vertexlib.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.vv.vertexlib.dtos.requests.BookCreateRequest;
import uz.vv.vertexlib.dtos.requests.BookUpdateRequest;
import uz.vv.vertexlib.dtos.responses.BookResponse;
import uz.vv.vertexlib.services.BookService;

import java.util.List;

/**
 * Kitoblarni boshqarish endpoint-lari.
 * GET endpointlar — hamma uchun ochiq.
 * POST, PUT, DELETE — faqat STAFF (SecurityConfig-da belgilangan).
 */
@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    /** POST /api/v1/books — Yangi kitob qo'shish (STAFF) */
    @PostMapping
    public ResponseEntity<BookResponse> create(@Valid @RequestBody BookCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.create(request));
    }

    /**
     * PUT /api/v1/books/{id} — To'liq yangilash (STAFF).
     * Partial update uchun PATCH /api/v1/books/{id}/details ishlatiladi.
     */
    @PutMapping("/{id}")
    public ResponseEntity<BookResponse> update(
            @PathVariable String id,
            @Valid @RequestBody BookCreateRequest request
    ) {
        return ResponseEntity.ok(bookService.update(id, request));
    }

    /** PATCH /api/v1/books/{id}/details — Faqat ba'zi maydonlarni yangilash (STAFF) */
    @PatchMapping("/{id}/details")
    public ResponseEntity<BookResponse> updateDetailed(
            @PathVariable String id,
            @Valid @RequestBody BookUpdateRequest request
    ) {
        return ResponseEntity.ok(bookService.updateDetailed(id, request));
    }

    /** GET /api/v1/books/{id} — ID bo'yicha olish (hamma uchun) */
    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> getById(@PathVariable String id) {
        return ResponseEntity.ok(bookService.getById(id));
    }

    /** GET /api/v1/books — Barcha kitoblar (hamma uchun) */
    @GetMapping
    public ResponseEntity<List<BookResponse>> getAll() {
        return ResponseEntity.ok(bookService.getAll());
    }

    /** DELETE /api/v1/books/{id} — Kitob o'chirish (STAFF) */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
