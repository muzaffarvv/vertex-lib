package uz.vv.vertexlib.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * JPA Auditing konfiguratsiyasi.
 * BaseEntity-dagi @CreatedDate va @LastModifiedDate annotatsiyalari
 * ishlashi uchun @EnableJpaAuditing kerak.
 */
@Configuration
@EnableJpaAuditing
public class JpaConfig {
}
