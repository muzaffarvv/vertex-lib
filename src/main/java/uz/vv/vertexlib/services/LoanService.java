package uz.vv.vertexlib.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.vv.vertexlib.dtos.requests.LoanCreateRequest;
import uz.vv.vertexlib.dtos.requests.LoanUpdateRequest;
import uz.vv.vertexlib.dtos.responses.LoanResponse;
import uz.vv.vertexlib.entities.Book;
import uz.vv.vertexlib.entities.Loans;
import uz.vv.vertexlib.entities.User;
import uz.vv.vertexlib.enums.UserRole;
import uz.vv.vertexlib.exceptions.BadRequestException;
import uz.vv.vertexlib.exceptions.InsufficientStockException;
import uz.vv.vertexlib.exceptions.ResourceNotFoundException;
import uz.vv.vertexlib.mappers.LoanMapper;
import uz.vv.vertexlib.repositories.BookRepository;
import uz.vv.vertexlib.repositories.LoanRepository;
import uz.vv.vertexlib.repositories.UserRepository;

import java.time.Instant;
import java.util.List;

/**
 * Kitob ijarasi amallari xizmati.
 * Ijarada kitob mavjudligi, foydalanuvchi rollari qat'iy tekshiriladi.
 * Barcha o'zaro bog'liq operatsiyalar @Transactional bilan himoyalangan.
 */
@Service
@RequiredArgsConstructor
public class LoanService {

    private final LoanRepository loanRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final LoanMapper mapper;

    // ── Ijara yaratish ────────────────────────────────────────────────────────

    /**
     * Kitob ijarasini rasmiylashtiradi.
     * Shartlar:
     * <ul>
     *   <li>Kitob mavjud bo'lishi va {@code availableCopies > 0} bo'lishi shart</li>
     *   <li>memberId — roli MEMBER bo'lgan foydalanuvchi bo'lishi shart</li>
     *   <li>staffId  — roli STAFF bo'lgan foydalanuvchi bo'lishi shart</li>
     * </ul>
     */
    @Transactional
    public LoanResponse createLoan(LoanCreateRequest request) {
        // 1. Kitobni tekshirish
        Book book = bookRepository.findById(request.bookId())
                .orElseThrow(() -> new ResourceNotFoundException("Kitob", "id", request.bookId()));

        if (book.getAvailableCopies() <= 0) {
            throw InsufficientStockException.forBook(book.getTitle());
        }

        // 2. Member tekshirish (faqat MEMBER roli)
        User member = userRepository.findById(request.memberId())
                .orElseThrow(() -> new ResourceNotFoundException("Foydalanuvchi (a'zo)", "id", request.memberId()));
        if (member.getRole() != UserRole.MEMBER) {
            throw new BadRequestException("Foydalanuvchi a'zo (MEMBER) bo'lishi kerak: " + request.memberId());
        }

        // 3. Staff tekshirish (faqat STAFF roli)
        User staff = userRepository.findById(request.staffId())
                .orElseThrow(() -> new ResourceNotFoundException("Foydalanuvchi (xodim)", "id", request.staffId()));
        if (staff.getRole() != UserRole.STAFF) {
            throw new BadRequestException("Foydalanuvchi xodim (STAFF) bo'lishi kerak: " + request.staffId());
        }

        // 4. Kitob nusxasini kamaytirish (atomic: @Transactional himoyasida)
        book.setAvailableCopies(book.getAvailableCopies() - 1);
        bookRepository.save(book);

        // 5. Ijarani saqlash
        Loans loan = mapper.toEntity(request);
        return mapper.toResponse(loanRepository.save(loan));
    }

    // ── Kitob qaytarish ───────────────────────────────────────────────────────

    /**
     * Kitobni qaytarishni rasmiylashtiradi va nusxa sonini oshiradi.
     */
    @Transactional
    public LoanResponse returnBook(String loanId, LoanUpdateRequest request) {
        Loans loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new ResourceNotFoundException("Ijara", "id", loanId));

        if (loan.getReturnDate() != null) {
            throw new BadRequestException("Bu kitob allaqachon qaytarilgan (ijara ID: " + loanId + ")");
        }

        // Qaytarish ma'lumotlarini yangilash
        loan.setReturnDate(request.returnDate() != null ? request.returnDate() : Instant.now());
        loan.setFineAmount(request.fineAmount());

        // Kitob nusxasini qaytarish (atomic)
        Book book = loan.getBook();
        book.setAvailableCopies(book.getAvailableCopies() + 1);
        bookRepository.save(book);

        return mapper.toResponse(loanRepository.save(loan));
    }

    // ── Qidirish ──────────────────────────────────────────────────────────────

    @Transactional(readOnly = true)
    public LoanResponse getById(String id) {
        return loanRepository.findById(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Ijara", "id", id));
    }

    @Transactional(readOnly = true)
    public List<LoanResponse> getAll() {
        return loanRepository.findAll().stream()
                .map(mapper::toResponse)
                .toList();
    }
}