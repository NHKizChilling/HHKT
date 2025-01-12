package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ChuyenTau {
    @Id
    @Column(name = "so_hieu_tau", nullable = false, length = 5)
    private String soHieuTau;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ma_loai_tau", nullable = false)
    private entity.LoaiTau loaiTau;

    public ChuyenTau(String soHieuTau) {
        this.soHieuTau = soHieuTau;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ChuyenTau chuyenTau = (ChuyenTau) o;
        return Objects.equals(soHieuTau, chuyenTau.soHieuTau);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(soHieuTau);
    }
}