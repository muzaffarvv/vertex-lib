package uz.vv.vertexlib.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.vv.vertexlib.dtos.requests.LoanCreateRequest;
import uz.vv.vertexlib.dtos.requests.LoanUpdateRequest;
import uz.vv.vertexlib.dtos.responses.LoanResponse;
import uz.vv.vertexlib.entities.Movie;
import uz.vv.vertexlib.entities.Loans;
import uz.vv.vertexlib.entities.User;
import uz.vv.vertexlib.enums.UserRole;
import uz.vv.vertexlib.exceptions.BadRequestException;
import uz.vv.vertexlib.exceptions.InsufficientStockException;
import uz.vv.vertexlib.exceptions.ResourceNotFoundException;
import uz.vv.vertexlib.mappers.LoanMapper;
import uz.vv.vertexlib.repositories.MovieRepository;
import uz.vv.vertexlib.repositories.LoanRepository;
import uz.vv.vertexlib.repositories.UserRepository;
import uz.vv.vertexlib.security.SecurityUtils;
import uz.vv.vertexlib.utils.SearchSpecification;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LoanService {

    private final LoanRepository loanRepository;
    private final MovieRepository movieRepository;
    private final UserRepository userRepository;
    private final LoanMapper mapper;

    @Transactional
    public LoanResponse createLoan(LoanCreateRequest request) {
        Movie movie = movieRepository.findById(request.movieId())
                .orElseThrow(() -> new ResourceNotFoundException("Kitob", "id", request.movieId()));

        if (movie.getAvailableCopies() <= 0) {
            throw InsufficientStockException.forMovie(movie.getTitle());
        }

        User member = userRepository.findById(request.memberId())
                .orElseThrow(() -> new ResourceNotFoundException("Foydalanuvchi (a'zo)", "id", request.memberId()));
        if (member.getRole() != UserRole.MEMBER) {
            throw new BadRequestException("Foydalanuvchi a'zo (MEMBER) bo'lishi kerak: " + request.memberId());
        }

        User staff = userRepository.findById(request.staffId())
                .orElseThrow(() -> new ResourceNotFoundException("Foydalanuvchi (xodim)", "id", request.staffId()));
        if (staff.getRole() != UserRole.STAFF) {
            throw new BadRequestException("Foydalanuvchi xodim (STAFF) bo'lishi kerak: " + request.staffId());
        }

        movie.setAvailableCopies(movie.getAvailableCopies() - 1);
        movieRepository.save(movie);

        Loans loan = mapper.toEntity(request);
        return mapper.toResponse(loanRepository.save(loan));
    }

    @Transactional
    public LoanResponse returnMovie(String loanId, LoanUpdateRequest request) {
        Loans loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new ResourceNotFoundException("Ijara", "id", loanId));

        if (loan.getReturnDate() != null) {
            throw new BadRequestException("Bu kitob allaqachon qaytarilgan (ijara ID: " + loanId + ")");
        }

        loan.setReturnDate(request.returnDate() != null ? request.returnDate() : Instant.now());
        loan.setFineAmount(request.fineAmount());

        Movie movie = loan.getMovie();
        movie.setAvailableCopies(movie.getAvailableCopies() + 1);
        movieRepository.save(movie);

        return mapper.toResponse(loanRepository.save(loan));
    }

    @Transactional(readOnly = true)
    public LoanResponse getById(String id) {
        return loanRepository.findById(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Ijara", "id", id));
    }

    @Transactional(readOnly = true)
    public Page<LoanResponse> getAll(String search, Pageable pageable) {
        List<String> fields = List.of("movie.title", "member.fullName", "member.phoneNumber","staff.fullName", "staff.phoneNumber");
        Specification<Loans> spec = SearchSpecification.globalStringSearch(search, fields);
        return loanRepository.findAll(spec, pageable).map(mapper::toResponse);
    }

    @Transactional(readOnly = true)
    public Page<LoanResponse> getMyLoans(Pageable pageable) {
        String currentUserId = SecurityUtils.getCurrentUserId();
        return loanRepository.findAllByMemberId(currentUserId, pageable)
                .map(mapper::toResponse);
    }
}