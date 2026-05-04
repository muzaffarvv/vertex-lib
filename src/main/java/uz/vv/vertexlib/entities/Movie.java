package uz.vv.vertexlib.entities;

import jakarta.persistence.*;
import lombok.*;
import uz.vv.vertexlib.base.BaseEntity;

@Getter
@Setter
@Entity
@Table(name = "movies")
public class Movie extends BaseEntity {

    @Column(nullable = false, length = 172)
    private String title;

    @Column(unique = true,  nullable = false, length = 17)
    private String isbn; // standart raqami

    @Column(nullable = false, length = 132)
    private String author;

    @ManyToOne
    @JoinColumn(name = "genre_id")
    private Genres genre;

    private Integer publishedYear; // olingan yil

    @Column(nullable = false)
    private Integer totalCopies; // Kinoteatrda ushbu kinodan jami nechta borligini ko'rsatadi

    @Column(nullable = false)
    private Integer availableCopies; // aynan hozirda kinoteatr javonida turgan (ijaraga berilmagan) nusxalar soni.
}
