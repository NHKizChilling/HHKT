package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ChiTietHoaDon {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ma_hd", nullable = false)
    private entity.HoaDon hoaDon;

    @Id
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ma_ve", nullable = false)
    private entity.Ve ve;

    @Column(name = "gia_ve", nullable = false, columnDefinition = "money")
    private double giaVe;

    @Column(name = "gia_giam", nullable = false, columnDefinition = "money")
    private double giaGiam;

    public ChiTietHoaDon(HoaDon hoaDon, Ve ve) {
        this.hoaDon = hoaDon;
        this.ve = ve;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ChiTietHoaDon that = (ChiTietHoaDon) o;
        return Objects.equals(hoaDon, that.hoaDon) && Objects.equals(ve, that.ve);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hoaDon, ve);
    }
}