package uz.vv.vertexlib.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uz.vv.vertexlib.entities.User;
import uz.vv.vertexlib.repositories.UserRepository;

import java.util.List;

/**
 * Spring Security-ning UserDetailsService implementatsiyasi.
 * Login uchun username sifatida telefon raqami ishlatiladi.
 */
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    /**
     * @param phoneNumber login uchun username (telefon raqami)
     * @throws UsernameNotFoundException foydalanuvchi topilmasa
     */
    @Override
    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
        User user = userRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new UsernameNotFoundException(
                        "Foydalanuvchi topilmadi: " + phoneNumber
                ));

        // Spring Security ROLE_ prefix kutadi
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getPhoneNumber())
                .password(user.getPassword())
                .authorities(List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().name())))
                .build();
    }
}
