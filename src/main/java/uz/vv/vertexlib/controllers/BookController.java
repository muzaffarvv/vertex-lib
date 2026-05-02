package uz.vv.vertexlib.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.vv.vertexlib.dtos.requests.BookCreateRequest;
import uz.vv.vertexlib.dtos.requests.BookUpdateRequest;
import uz.vv.vertexlib.dtos.responses.BookResponse;
import uz.vv.vertexlib.services.BookService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping
    public ResponseEntity<BookResponse> create(@Valid @RequestBody BookCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookResponse> update(
            @PathVariable String id,
            @Valid @RequestBody BookCreateRequest request
    ) {
        return ResponseEntity.ok(bookService.update(id, request));
    }

    @PatchMapping("/{id}/details")
    public ResponseEntity<BookResponse> updateDetailed(
            @PathVariable String id,
            @Valid @RequestBody BookUpdateRequest request
    ) {
        return ResponseEntity.ok(bookService.updateDetailed(id, request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> getById(@PathVariable String id) {
        return ResponseEntity.ok(bookService.getById(id));
    }

    @GetMapping     // TODO: pagination qo'shish kerak
    public ResponseEntity<Page<BookResponse>> getAll(Pageable pageable) {
        return ResponseEntity.ok(bookService.getAll(pageable));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

