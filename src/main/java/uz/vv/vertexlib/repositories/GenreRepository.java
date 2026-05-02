package uz.vv.vertexlib.repositories;

import org.springframework.stereotype.Repository;
import uz.vv.vertexlib.entities.Genres;
import uz.vv.vertexlib.base.BaseRepository;

import java.util.Optional;

@Repository
public interface GenreRepository extends BaseRepository<Genres> {
    Optional<Genres> findByName(String name);
}