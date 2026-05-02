package uz.vv.vertexlib.repositories;

import org.springframework.stereotype.Repository;
import uz.vv.vertexlib.entities.Loans;
import uz.vv.vertexlib.base.BaseRepository;

import java.util.List;

@Repository
public interface LoanRepository extends BaseRepository<Loans> {
    List<Loans> findAllByMemberId(String memberId);

    List<Loans> findAllByReturnDateIsNull();
}