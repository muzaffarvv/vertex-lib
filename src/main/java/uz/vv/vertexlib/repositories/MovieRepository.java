package uz.vv.vertexlib.repositories;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import uz.vv.vertexlib.entities.Movie;
import uz.vv.vertexlib.base.BaseRepository;

import java.util.Optional;

@Repository
public interface MovieRepository extends BaseRepository<Movie>  {
    @Override
    Page<Movie> findAll(Pageable pageable);
    Optional<Movie> findByIsbn(String isbn);
    boolean existsByIsbn(String isbn);
}
