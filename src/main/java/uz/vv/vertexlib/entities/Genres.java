package uz.vv.vertexlib.entities;

import jakarta.persistence.*;
import lombok.*;
import uz.vv.vertexlib.base.BaseEntity;

@Getter
@Setter
@Entity
@Table(name = "genres")
public class Genres extends BaseEntity { // kutubxona bor kitoblaring janrlari

    @Column(unique = true, nullable = false, length = 60)
    private String name;

    @Column(length = 132)
    private String description;
}
