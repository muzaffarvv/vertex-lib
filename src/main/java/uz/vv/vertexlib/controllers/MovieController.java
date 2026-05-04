package uz.vv.vertexlib.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.vv.vertexlib.dtos.requests.MovieCreateRequest;
import uz.vv.vertexlib.dtos.requests.MovieUpdateRequest;
import uz.vv.vertexlib.dtos.responses.MovieResponse;
import uz.vv.vertexlib.services.MovieService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @PostMapping
    public ResponseEntity<MovieResponse> create(@Valid @RequestBody MovieCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(movieService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieResponse> update(
            @PathVariable String id,
            @Valid @RequestBody MovieCreateRequest request
    ) {
        return ResponseEntity.ok(movieService.update(id, request));
    }

    @PatchMapping("/{id}/details")
    public ResponseEntity<MovieResponse> updateDetailed(
            @PathVariable String id,
            @Valid @RequestBody MovieUpdateRequest request
    ) {
        return ResponseEntity.ok(movieService.updateDetailed(id, request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieResponse> getById(@PathVariable String id) {
        return ResponseEntity.ok(movieService.getById(id));
    }

    @GetMapping
    public ResponseEntity<Page<MovieResponse>> getAll(
            @RequestParam(required = false) String search,
            Pageable pageable) {
        return ResponseEntity.ok(movieService.getAll(search, pageable));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        movieService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

