package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import java.util.Objects;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class LoaiTau {
    @Id
    @Column(name = "ma_loai_tau", nullable = false, length = 5)
    private String maLoaiTau;

    @Nationalized
    @Column(name = "ten_loai_tau", nullable = false, length = 30)
    private String tenLoaiTau;

    public LoaiTau(String maLoaiTau) {
        this.maLoaiTau = maLoaiTau;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        LoaiTau loaiTau = (LoaiTau) o;
        return Objects.equals(maLoaiTau, loaiTau.maLoaiTau);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(maLoaiTau);
    }
}