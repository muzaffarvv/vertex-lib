package uz.vv.vertexlib.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.vv.vertexlib.dtos.requests.GenreRequest;
import uz.vv.vertexlib.dtos.responses.GenreResponse;
import uz.vv.vertexlib.services.GenreService;

import java.util.List;

/**
 * Kitob janrlarini boshqarish endpoint-lari.
 * GET — hamma uchun ochiq.
 * POST, PUT, DELETE — faqat STAFF (SecurityConfig-da belgilangan).
 */
@RestController
@RequestMapping("/api/v1/genres")
@RequiredArgsConstructor
public class GenreController {

    private final GenreService genreService;

    /** POST /api/v1/genres — Yangi janr qo'shish (STAFF) */
    @PostMapping
    public ResponseEntity<GenreResponse> create(@Valid @RequestBody GenreRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(genreService.create(request));
    }

    /** PUT /api/v1/genres/{id} — Janrni yangilash (STAFF) */
    @PutMapping("/{id}")
    public ResponseEntity<GenreResponse> update(
            @PathVariable String id,
            @Valid @RequestBody GenreRequest request
    ) {
        return ResponseEntity.ok(genreService.update(id, request));
    }

    /** GET /api/v1/genres/{id} — ID bo'yicha olish (hamma uchun) */
    @GetMapping("/{id}")
    public ResponseEntity<GenreResponse> getById(@PathVariable String id) {
        return ResponseEntity.ok(genreService.getById(id));
    }

    /** GET /api/v1/genres — Barcha janrlar (hamma uchun) */
    @GetMapping
    public ResponseEntity<List<GenreResponse>> getAll() {
        return ResponseEntity.ok(genreService.getAll());
    }

    /** DELETE /api/v1/genres/{id} — Janr o'chirish (STAFF) */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        genreService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
