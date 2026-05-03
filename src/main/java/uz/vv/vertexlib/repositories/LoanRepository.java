package uz.vv.vertexlib.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import uz.vv.vertexlib.entities.Loans;
import uz.vv.vertexlib.base.BaseRepository;

import java.util.List;

@Repository
public interface LoanRepository extends BaseRepository<Loans> {
    Page<Loans> findAllByMemberId(String memberId, Pageable pageable);

    List<Loans> findAllByReturnDateIsNull();
}