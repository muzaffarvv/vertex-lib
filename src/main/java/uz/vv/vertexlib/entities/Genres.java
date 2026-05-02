package uz.vv.vertexlib.entities;

import jakarta.persistence.*;
import lombok.*;
import uz.vv.vertexlib.base.BaseEntity;

@Getter
@Setter
@Entity
@Table(name = "genres")
public class Genres extends BaseEntity { // kutubxona bor kitoblaring janrlari

    @Column(unique = true, nullable = false)
    private String name;

    private String description;
}
