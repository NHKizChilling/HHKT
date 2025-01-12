package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class LichTrinh {
    @Id
    @Column(name = "ma_lich_trinh", nullable = false, length = 20)
    private String maLichTrinh;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "so_hieu_tau", nullable = false)
    private ChuyenTau soHieuTau;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ma_ga_di", nullable = false)
    private Ga gaDi;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ma_ga_den", nullable = false)
    private Ga gaDen;

    @Column(name = "thoi_gian_khoi_hanh", nullable = false)
    private LocalDateTime thoiGianKhoiHanh;

    @Column(name = "thoi_gian_du_kien_den", nullable = false)
    private LocalDateTime thoiGianDuKienDen;

    @ColumnDefault("1")
    @Column(name = "trang_thai", nullable = false)
    private boolean trangThai = false;

    public LichTrinh(String maLichTrinh) {
        this.maLichTrinh = maLichTrinh;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        LichTrinh lichTrinh = (LichTrinh) o;
        return Objects.equals(maLichTrinh, lichTrinh.maLichTrinh);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(maLichTrinh);
    }
}