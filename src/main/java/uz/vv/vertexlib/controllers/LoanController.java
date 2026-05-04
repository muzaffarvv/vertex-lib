package uz.vv.vertexlib.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.vv.vertexlib.dtos.requests.LoanCreateRequest;
import uz.vv.vertexlib.dtos.requests.LoanUpdateRequest;
import uz.vv.vertexlib.dtos.responses.LoanResponse;
import uz.vv.vertexlib.services.LoanService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/loans")
@RequiredArgsConstructor
public class LoanController {

    private final LoanService loanService;

    @PostMapping
    public ResponseEntity<LoanResponse> createLoan(@Valid @RequestBody LoanCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(loanService.createLoan(request));
    }

    @PutMapping("/{id}/return")
    public ResponseEntity<LoanResponse> returnMovie(
            @PathVariable String id,
            @Valid @RequestBody LoanUpdateRequest request
    ) {
        return ResponseEntity.ok(loanService.returnMovie(id, request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoanResponse> getById(@PathVariable String id) {
        return ResponseEntity.ok(loanService.getById(id));
    }

    @GetMapping
    public ResponseEntity<Page<LoanResponse>> getAll(
            @RequestParam(required = false) String search,
            Pageable pageable) {
        return ResponseEntity.ok(loanService.getAll(search, pageable));
    }

    @GetMapping("/my-loans")
    public ResponseEntity<Page<LoanResponse>> getMyLoans(Pageable pageable) {
        return ResponseEntity.ok(loanService.getMyLoans(pageable));
    }
}

