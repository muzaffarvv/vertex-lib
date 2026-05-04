package uz.vv.vertexlib.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.vv.vertexlib.base.BaseService;
import uz.vv.vertexlib.dtos.requests.MovieCreateRequest;
import uz.vv.vertexlib.dtos.requests.MovieUpdateRequest;
import uz.vv.vertexlib.dtos.responses.MovieResponse;
import uz.vv.vertexlib.entities.Movie;
import uz.vv.vertexlib.exceptions.AlreadyExistsException;
import uz.vv.vertexlib.exceptions.ResourceNotFoundException;
import uz.vv.vertexlib.mappers.MovieMapper;
import uz.vv.vertexlib.repositories.MovieRepository;
import uz.vv.vertexlib.utils.SearchSpecification;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService implements BaseService<MovieCreateRequest, MovieResponse, String> {

    private final MovieRepository repository;
    private final MovieMapper mapper;

    @Override
    @Transactional
    public MovieResponse create(MovieCreateRequest request) {
        if (repository.existsByIsbn(request.isbn())) {
            throw new AlreadyExistsException("Film", "ISBN", request.isbn());
        }
        Movie entity = mapper.toEntity(request);
        return mapper.toResponse(repository.save(entity));
    }

    @Override
    @Transactional
    public MovieResponse update(String id, MovieCreateRequest request) {
        Movie entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Film", "id", id));

        if (!entity.getIsbn().equals(request.isbn()) && repository.existsByIsbn(request.isbn())) {
            throw new AlreadyExistsException("Film", "ISBN", request.isbn());
        }

        entity.setTitle(request.title());
        entity.setIsbn(request.isbn());
        entity.setAuthor(request.author());
        entity.setPublishedYear(request.publishedYear());
        entity.setTotalCopies(request.totalCopies());
        
        entity.setAvailableCopies(request.totalCopies());

        return mapper.toResponse(repository.save(entity));
    }

    @Transactional
    public MovieResponse updateDetailed(String id, MovieUpdateRequest request) {
        Movie entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Film", "id", id));
        mapper.updateEntityFromDto(request, entity);
        return mapper.toResponse(repository.save(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public MovieResponse getById(String id) {
        return repository.findById(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Film", "id", id));
    }

    @Override
    @Transactional
    public void delete(String id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Film", "id", id);
        }
        repository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Page<MovieResponse> getAll(String search, Pageable pageable) {
        List<String> fields = List.of("title", "author", "isbn", "genre.name");
        Specification<Movie> spec = SearchSpecification.globalStringSearch(search, fields);
        return repository.findAll(spec, pageable).map(mapper::toResponse);
    }
}