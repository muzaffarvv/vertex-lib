package uz.vv.vertexlib.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.vv.vertexlib.dtos.requests.LoginRequest;
import uz.vv.vertexlib.dtos.requests.RegisterRequest;
import uz.vv.vertexlib.dtos.responses.AuthResponse;
import uz.vv.vertexlib.dtos.responses.UserResponse;
import uz.vv.vertexlib.entities.User;
import uz.vv.vertexlib.enums.UserRole;
import uz.vv.vertexlib.exceptions.AlreadyExistsException;
import uz.vv.vertexlib.mappers.UserMapper;
import uz.vv.vertexlib.repositories.UserRepository;
import uz.vv.vertexlib.security.JwtUtil;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;

    @Transactional
    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByPhoneNumber(request.phoneNumber())) {
            throw new AlreadyExistsException("Foydalanuvchi", "telefon raqami", request.phoneNumber());
        }

        User user = User.builder()
                .fullName(request.fullName())
                .phoneNumber(request.phoneNumber())
                .password(passwordEncoder.encode(request.password()))
                .role(UserRole.MEMBER)
                .build();

        User savedUser = userRepository.save(user);

        String token = jwtUtil.generateToken(
                userDetailsService.loadUserByUsername(
                        savedUser.getPhoneNumber())
        );

        return AuthResponse.of(token, userMapper.toResponse(savedUser));
    }

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.phoneNumber(),
                        request.password()
                )
        );

        User user = userRepository.findByPhoneNumber(request.phoneNumber())
                .orElseThrow(); 

        String token = jwtUtil.generateToken(userDetailsService.loadUserByUsername(user.getPhoneNumber()));

        return AuthResponse.of(token, userMapper.toResponse(user));
    }
}

