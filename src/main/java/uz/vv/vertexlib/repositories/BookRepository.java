package uz.vv.vertexlib.repositories;


import org.springframework.stereotype.Repository;
import uz.vv.vertexlib.entities.Book;
import uz.vv.vertexlib.base.BaseRepository;

import java.util.Optional;

@Repository
public interface BookRepository extends BaseRepository<Book> {
    Optional<Book> findByIsbn(String isbn);
    boolean existsByIsbn(String isbn);
}
