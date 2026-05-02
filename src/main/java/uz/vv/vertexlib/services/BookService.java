package uz.vv.vertexlib.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.vv.vertexlib.base.BaseService;
import uz.vv.vertexlib.dtos.requests.BookCreateRequest;
import uz.vv.vertexlib.dtos.requests.BookUpdateRequest;
import uz.vv.vertexlib.dtos.responses.BookResponse;
import uz.vv.vertexlib.entities.Book;
import uz.vv.vertexlib.exceptions.AlreadyExistsException;
import uz.vv.vertexlib.exceptions.ResourceNotFoundException;
import uz.vv.vertexlib.mappers.BookMapper;
import uz.vv.vertexlib.repositories.BookRepository;

import java.util.List;

/**
 * Kitoblarni boshqarish xizmati.
 * ISBN unikal bo'lishi shart; yangi kitob qo'shilganda barcha nusxalar mavjud deb hisoblanadi.
 */
@Service
@RequiredArgsConstructor
public class BookService implements BaseService<BookCreateRequest, BookResponse, String> {

    private final BookRepository repository;
    private final BookMapper mapper;

    // ── CRUD ──────────────────────────────────────────────────────────────────

    @Override
    @Transactional
    public BookResponse create(BookCreateRequest request) {
        if (repository.existsByIsbn(request.isbn())) {
            throw new AlreadyExistsException("Kitob", "ISBN", request.isbn());
        }
        Book entity = mapper.toEntity(request);
        return mapper.toResponse(repository.save(entity));
    }

    /**
     * BaseService.update() — BookCreateRequest bilan ishlaydi.
     * Agar faqat ba'zi maydonlarni yangilash kerak bo'lsa, {@link #updateDetailed} ni ishlating.
     */
    @Override
    @Transactional
    public BookResponse update(String id, BookCreateRequest request) {
        Book entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Kitob", "id", id));

        // ISBN o'zgargan bo'lsa, yangi ISBN band emasligini tekshirish
        if (!entity.getIsbn().equals(request.isbn()) && repository.existsByIsbn(request.isbn())) {
            throw new AlreadyExistsException("Kitob", "ISBN", request.isbn());
        }

        entity.setTitle(request.title());
        entity.setIsbn(request.isbn());
        entity.setAuthor(request.author());
        entity.setPublishedYear(request.publishedYear());
        entity.setTotalCopies(request.totalCopies());
        // availableCopies ni oddiy yangilash siyosati: total bilan bir xil qilinadi
        entity.setAvailableCopies(request.totalCopies());

        return mapper.toResponse(repository.save(entity));
    }

    /**
     * Faqat yuborilgan maydonlarni yangilaydigan partial update (PATCH mantiq).
     */
    @Transactional
    public BookResponse updateDetailed(String id, BookUpdateRequest request) {
        Book entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Kitob", "id", id));
        mapper.updateEntityFromDto(request, entity);
        return mapper.toResponse(repository.save(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public BookResponse getById(String id) {
        return repository.findById(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Kitob", "id", id));
    }

    @Override
    @Transactional
    public void delete(String id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Kitob", "id", id);
        }
        repository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<BookResponse> getAll() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .toList();
    }
}