package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class KhuyenMai {
    @Id
    @Column(name = "ma_km", nullable = false, length = 11)
    private String maKM;

    @Nationalized
    @Column(name = "mo_ta", nullable = false, length = 50)
    private String moTa;

    @Column(name = "ngay_ap_dung", nullable = false)
    private LocalDate ngayApDung;

    @Column(name = "ngay_het_han", nullable = false)
    private LocalDate ngayHetHan;

    @Column(name = "muc_km", nullable = false)
    private float mucKM;

    @Column(name = "trang_thai", nullable = false)
    private boolean trangThai = false;

    public KhuyenMai(String maKM) {
        this.maKM = maKM;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        KhuyenMai khuyenMai = (KhuyenMai) o;
        return Objects.equals(maKM, khuyenMai.maKM);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(maKM);
    }
}