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

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;
    private final JwtAuthFilter jwtAuthFilter;


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

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)

                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                .authorizeHttpRequests(auth -> auth

                        .requestMatchers("/api/v1/auth/**", "/login", "/register", "/css/**", "/js/**", "/images/**").permitAll()

                        .requestMatchers(HttpMethod.GET, "/api/v1/movies/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/genres/**").permitAll()

                        .requestMatchers(HttpMethod.POST, "/api/v1/movies/**").hasRole("STAFF")
                        .requestMatchers(HttpMethod.PUT,  "/api/v1/movies/**").hasRole("STAFF")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/movies/**").hasRole("STAFF")

                        .requestMatchers(HttpMethod.POST, "/api/v1/genres/**").hasRole("STAFF")
                        .requestMatchers(HttpMethod.PUT,  "/api/v1/genres/**").hasRole("STAFF")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/genres/**").hasRole("STAFF")

                        .requestMatchers("/api/v1/loans/**").hasRole("STAFF")

                        .requestMatchers("/api/v1/users/**").hasRole("STAFF")

                        .anyRequest().authenticated()
                )

                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
