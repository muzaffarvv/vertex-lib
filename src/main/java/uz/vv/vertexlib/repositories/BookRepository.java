package uz.vv.vertexlib.repositories;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import uz.vv.vertexlib.entities.Book;
import uz.vv.vertexlib.base.BaseRepository;

import java.util.Optional;

@Repository
public interface BookRepository extends BaseRepository<Book> {
    @Override
    Page<Book> findAll(Pageable pageable);
    Optional<Book> findByIsbn(String isbn);
    boolean existsByIsbn(String isbn);
}
