package uz.vv.vertexlib.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.vv.vertexlib.base.BaseService;
import uz.vv.vertexlib.dtos.requests.GenreRequest;
import uz.vv.vertexlib.dtos.responses.GenreResponse;
import uz.vv.vertexlib.entities.Genres;
import uz.vv.vertexlib.exceptions.AlreadyExistsException;
import uz.vv.vertexlib.exceptions.ResourceNotFoundException;
import uz.vv.vertexlib.mappers.GenreMapper;
import uz.vv.vertexlib.repositories.GenreRepository;

import java.util.List;

/**
 * Kitob janrlarini boshqarish xizmati.
 * Janr nomi unikal bo'lishi shart.
 */
@Service
@RequiredArgsConstructor
public class GenreService implements BaseService<GenreRequest, GenreResponse, String> {

    private final GenreRepository repository;
    private final GenreMapper mapper;

    // ── CRUD ──────────────────────────────────────────────────────────────────

    @Override
    @Transactional
    public GenreResponse create(GenreRequest request) {
        if (repository.existsByName(request.name())) {
            throw new AlreadyExistsException("Janr", "nomi", request.name());
        }
        Genres entity = mapper.toEntity(request);
        return mapper.toResponse(repository.save(entity));
    }

    @Override
    @Transactional
    public GenreResponse update(String id, GenreRequest request) {
        Genres entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Janr", "id", id));

        // Nom o'zgargan bo'lsa, yangi nom band emasligini tekshirish
        if (!entity.getName().equals(request.name()) && repository.existsByName(request.name())) {
            throw new AlreadyExistsException("Janr", "nomi", request.name());
        }

        mapper.updateEntityFromDto(request, entity);
        return mapper.toResponse(repository.save(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public GenreResponse getById(String id) {
        return repository.findById(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Janr", "id", id));
    }

    @Override
    @Transactional
    public void delete(String id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Janr", "id", id);
        }
        repository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<GenreResponse> getAll() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .toList();
    }
}