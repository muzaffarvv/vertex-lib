package uz.vv.vertexlib.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Spring Security konfiguratsiyasi.
 * Stateless JWT-based authentication, rol asosida endpoint ruxsatlari.
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;
    private final JwtAuthFilter jwtAuthFilter;

    // ── Bean-lar ──────────────────────────────────────────────────────────────

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config)
            throws Exception {
        return config.getAuthenticationManager();
    }

    // ── SecurityFilterChain ───────────────────────────────────────────────────

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // CSRF — stateless JWT API da shart emas
                .csrf(AbstractHttpConfigurer::disable)

                // Session — JWT ishlatganimiz uchun STATELESS
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // Endpoint ruxsatlari
                .authorizeHttpRequests(auth -> auth

                        // Auth endpointlari — hamma uchun ochiq
                        .requestMatchers("/api/v1/auth/**").permitAll()

                        // Kitob va janrlarni ko'rish — hamma uchun ochiq
                        .requestMatchers(HttpMethod.GET, "/api/v1/books/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/genres/**").permitAll()

                        // Kitob va janr yaratish/yangilash/o'chirish — faqat STAFF
                        .requestMatchers(HttpMethod.POST, "/api/v1/books/**").hasRole("STAFF")
                        .requestMatchers(HttpMethod.PUT,  "/api/v1/books/**").hasRole("STAFF")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/books/**").hasRole("STAFF")

                        .requestMatchers(HttpMethod.POST, "/api/v1/genres/**").hasRole("STAFF")
                        .requestMatchers(HttpMethod.PUT,  "/api/v1/genres/**").hasRole("STAFF")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/genres/**").hasRole("STAFF")

                        // Ijaralar — faqat STAFF
                        .requestMatchers("/api/v1/loans/**").hasRole("STAFF")

                        // Foydalanuvchilar boshqaruvi — faqat STAFF
                        .requestMatchers("/api/v1/users/**").hasRole("STAFF")

                        // Qolgan barcha so'rovlar — autentifikatsiya talab etiladi
                        .anyRequest().authenticated()
                )

                // JWT filteri UsernamePasswordAuthenticationFilter DAN OLDIN ishlashi kerak
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
