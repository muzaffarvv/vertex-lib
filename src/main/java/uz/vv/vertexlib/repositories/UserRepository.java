package uz.vv.vertexlib.repositories;

import org.springframework.stereotype.Repository;
import uz.vv.vertexlib.entities.User;
import uz.vv.vertexlib.base.BaseRepository;

import java.util.Optional;

@Repository
public interface UserRepository extends BaseRepository<User> {
    Optional<User> findByPhoneNumber(String phoneNumber);
    boolean existsByPhoneNumber(String phoneNumber);
}