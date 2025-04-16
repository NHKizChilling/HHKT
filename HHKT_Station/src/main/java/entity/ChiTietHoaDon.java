package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;
import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ChiTietHoaDon implements Serializable {
    @Id
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "ma_hd", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private entity.HoaDon hoaDon;

    @Id
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "ma_ve", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private entity.Ve ve;

    @Column(name = "gia_ve", nullable = false, columnDefinition = "money")
    private double giaVe;

    @Column(name = "gia_giam", nullable = false, columnDefinition = "money")
    private double giaGiam;

    private static final double PHI_DICH_VU = 2000;

    public ChiTietHoaDon(HoaDon hoaDon, Ve ve) {
        this.hoaDon = hoaDon;
        this.ve = ve;
    }

    public void tinhGiaVe() {
        double giaVe = 0;
        double giaCho = ve.getChiTietLichTrinh().getGiaCho();
        LoaiVe loaiVe = ve.getLoaiVe();
        if(!loaiVe.getMaLoaiVe().equals("VNL")) {
            giaVe = giaCho * (1 - loaiVe.getMucGiamGia()) + PHI_DICH_VU;
        } else {
            if (ve.isKhuHoi()) {
                giaVe = giaCho * 0.9 + PHI_DICH_VU;
            } else {
                giaVe = giaCho + PHI_DICH_VU;
            }
        }
        this.giaVe = Math.round(giaVe/1000) * 1000;
    }

    public void tinhGiaGiam() {
        double giaGiam = 0;
        double giaCho = ve.getChiTietLichTrinh().getGiaCho();
        LoaiVe loaiVe = ve.getLoaiVe();

        if(!loaiVe.getMaLoaiVe().equals("VNL")) {
            giaGiam = giaCho * loaiVe.getMucGiamGia();
        } else {
            if (ve.isKhuHoi()) {
                giaGiam = giaCho * 0.1;
            }
        }
        this.giaGiam = Math.round(giaGiam/1000) * 1000;
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