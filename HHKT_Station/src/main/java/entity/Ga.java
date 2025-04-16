package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Ga", uniqueConstraints = {
        @UniqueConstraint(name = "UQ_ten_ga", columnNames = {"ten_ga"})
})
public class Ga implements Serializable {
    @Id
    @Column(name = "ma_ga", nullable = false, length = 10)
    private String maGa;

    @Nationalized
    @Column(name = "ten_ga", nullable = false, length = 50)
    private String tenGa;

    @Nationalized
    @Column(name = "vi_tri", length = 20)
    private String viTri;

    @Column(name = "khoang_cach", nullable = false)
    private int khoangCach;

    public Ga(String maGa) {
        this.maGa = maGa;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Ga ga = (Ga) o;
        return Objects.equals(maGa, ga.maGa);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(maGa);
    }
}