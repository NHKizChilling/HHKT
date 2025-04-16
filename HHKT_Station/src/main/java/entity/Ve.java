package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Nationalized;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Ve implements Serializable {
    @Id
    @ColumnDefault("[dbo].[auto_idve]()")
    @Column(name = "ma_ve", nullable = false, columnDefinition = "char(14)")
    private String maVe;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ma_kh", nullable = false)
    private KhachHang khachHang;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "ma_cho", referencedColumnName = "ma_cho", nullable = false),
            @JoinColumn(name = "ma_lich_trinh", referencedColumnName = "ma_lich_trinh", nullable = false)
    })
    private ChiTietLichTrinh chiTietLichTrinh;

    @ManyToOne
    @JoinColumn(name = "ma_loai_ve", nullable = false)
    private LoaiVe loaiVe;

    @Nationalized
    @Column(name = "ten_kh", length = 30)
    private String tenKH;

    @Column(name = "socccd", length = 12)
    private String soCCCD;

    @Column(name = "ngay_sinh")
    private LocalDate ngaySinh;

    @Nationalized
    @ColumnDefault("'DaBan'")
    @Column(name = "tinh_trang_ve", length = 50)
    private String tinhTrangVe;

    @ColumnDefault("0")
    @Column(name = "khu_hoi", nullable = false)
    private boolean khuHoi = false;

    public Ve(String maVe) {
        this.maVe = maVe;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Ve ve = (Ve) o;
        return Objects.equals(maVe, ve.maVe);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(maVe);
    }
}