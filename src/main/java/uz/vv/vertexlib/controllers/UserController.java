package uz.vv.vertexlib.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.vv.vertexlib.dtos.requests.UserRequest;
import uz.vv.vertexlib.dtos.responses.UserResponse;
import uz.vv.vertexlib.services.UserService;

import java.util.List;

/**
 * Foydalanuvchilarni boshqarish endpoint-lari.
 * Barcha endpointlar faqat STAFF roli uchun (SecurityConfig-da belgilangan).
 */
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /** POST /api/v1/users — Yangi foydalanuvchi yaratish */
    @PostMapping
    public ResponseEntity<UserResponse> create(@Valid @RequestBody UserRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(request));
    }

    /** PUT /api/v1/users/{id} — Foydalanuvchini yangilash */
    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> update(
            @PathVariable String id,
            @Valid @RequestBody UserRequest request
    ) {
        return ResponseEntity.ok(userService.update(id, request));
    }

    /** GET /api/v1/users/{id} — ID bo'yicha olish */
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getById(@PathVariable String id) {
        return ResponseEntity.ok(userService.getById(id));
    }

    /** GET /api/v1/users — Barcha foydalanuvchilar */
    @GetMapping
    public ResponseEntity<List<UserResponse>> getAll() {
        return ResponseEntity.ok(userService.getAll());
    }

    /** DELETE /api/v1/users/{id} — Foydalanuvchini o'chirish */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
