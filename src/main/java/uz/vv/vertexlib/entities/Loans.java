package uz.vv.vertexlib.entities;

import jakarta.persistence.*;
import lombok.*;
import uz.vv.vertexlib.base.BaseEntity;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "loans")
public class Loans extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private User member; // Role = MEMBER !!

    @ManyToOne
    @JoinColumn(name = "staff_id", nullable = false)
    private User staff; // Role = STAFF !!   ijarani rasmiylashtirgan xodim

    @Column(nullable = false)
    private Instant loanDate; // Olingan sana

    private Instant dueDate; // Qaytarish kerak bo'lgan sana

    private Instant returnDate; // Haqiqiy qaytarilgan sana

    private Double fineAmount; // Kechikkan kunlar uchun jarima[cite: 1]
}