package uz.vv.vertexlib.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.vv.vertexlib.base.BaseService;
import uz.vv.vertexlib.dtos.requests.UserRequest;
import uz.vv.vertexlib.dtos.responses.UserResponse;
import uz.vv.vertexlib.entities.User;
import uz.vv.vertexlib.exceptions.AlreadyExistsException;
import uz.vv.vertexlib.exceptions.ResourceNotFoundException;
import uz.vv.vertexlib.mappers.UserMapper;
import uz.vv.vertexlib.repositories.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService implements BaseService<UserRequest, UserResponse, String> {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserResponse create(UserRequest request) {
        if (userRepository.existsByPhoneNumber(request.phoneNumber())) {
            throw new AlreadyExistsException("Foydalanuvchi", "telefon raqami", request.phoneNumber());
        }

        User user = userMapper.toEntity(request);
        user.setPassword(passwordEncoder.encode(request.password()));

        return userMapper.toResponse(userRepository.save(user));
    }

    @Override
    @Transactional
    public UserResponse update(String id, UserRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Foydalanuvchi", "id", id));

        if (!user.getPhoneNumber().equals(request.phoneNumber()) &&
                userRepository.existsByPhoneNumber(request.phoneNumber())) {
            throw new AlreadyExistsException("Foydalanuvchi", "telefon raqami", request.phoneNumber());
        }

        user.setFullName(request.fullName());
        user.setPhoneNumber(request.phoneNumber());
        user.setRole(request.role());

        if (request.password() != null && !request.password().isBlank()) {
            user.setPassword(passwordEncoder.encode(request.password()));
        }

        return userMapper.toResponse(userRepository.save(user));
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponse getById(String id) {
        return userRepository.findById(id)
                .map(userMapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Foydalanuvchi", "id", id));
    }

    @Override
    @Transactional
    public void delete(String id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("Foydalanuvchi", "id", id);
        }
        userRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Page<UserResponse> getAll(Pageable pageable) {
        return userRepository.findAll(pageable)
                .map(userMapper::toResponse);
    }
}