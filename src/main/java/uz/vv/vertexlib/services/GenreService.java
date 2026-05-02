package uz.vv.vertexlib.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

@Service
@RequiredArgsConstructor
public class GenreService implements BaseService<GenreRequest, GenreResponse, String> {

    private final GenreRepository repository;
    private final GenreMapper mapper;

    @Override
    @Transactional
    public GenreResponse create(GenreRequest request) {
        if (repository.existsByName(request.name())) throw new AlreadyExistsException("Janr", "nomi", request.name());

        return mapper.toResponse(repository.save(mapper.toEntity(request)));
    }

    @Override
    @Transactional
    public GenreResponse update(String id, GenreRequest request) {
        Genres entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Janr", "id", id));

        if (!entity.getName().equals(request.name()) && repository.existsByName(request.name()))
            throw new AlreadyExistsException("Janr", "nomi", request.name());

        entity.setName(request.name());
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
        // TODO: kitoblari mavjud janrni o'chirib yuborish mumkin emas
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Janr", "id", id);
        }
        repository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Page<GenreResponse> getAll(Pageable pageable) {
        return repository.findAll(pageable)
                .map(mapper::toResponse);
    }
}