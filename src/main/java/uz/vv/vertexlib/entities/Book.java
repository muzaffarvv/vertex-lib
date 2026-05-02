package uz.vv.vertexlib.entities;

import jakarta.persistence.*;
import lombok.*;
import uz.vv.vertexlib.base.BaseEntity;

@Getter
@Setter
@Entity
@Table(name = "books")
public class Book extends BaseEntity {

    @Column(nullable = false, length = 172)
    private String title;

    @Column(unique = true,  nullable = false, length = 17)
    private String isbn; // Xalqaro standart kitob raqami

    @Column(nullable = false, length = 132)
    private String author;

    @ManyToOne
    @JoinColumn(name = "genre_id")
    private Genres genre;

    private Integer publishedYear; // Chop etilgan yil

    @Column(nullable = false)
    private Integer totalCopies; // Kutubxonada ushbu kitobdan jami nechta borligini ko'rsatadi

    @Column(nullable = false)
    private Integer availableCopies; // aynan hozirda kutubxona javonida turgan (ijaraga berilmagan) nusxalar soni.
}
