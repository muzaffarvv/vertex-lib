package uz.vv.vertexlib.entities;

import jakarta.persistence.*;
import lombok.*;
import uz.vv.vertexlib.base.BaseEntity;
import uz.vv.vertexlib.enums.UserRole;

@Getter
@Setter
@Entity
@Builder
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {

    @Column(nullable = false, length = 132)
    private String fullName;

    @Column(unique = true, nullable = false,  length = 12)
    private String phoneNumber;

    @Column(nullable = false, length = 64)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 12)
    private UserRole role;

}
