package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CaLamViec implements Serializable {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ma_nv", nullable = false)
    private entity.NhanVien nhanVien;

    @Id
    @Column(name = "gio_mo_ca", nullable = false)
    private LocalDateTime gioMoCa;

    @Column(name = "gio_ket_ca")
    private LocalDateTime gioKetCa;

    @Column(name = "tien_dau_ca", nullable = false, columnDefinition = "money")
    private double tienDauCa;

    @Column(name = "tien_ket_ca", nullable = false, columnDefinition = "money")
    private double tienKetCa;

    @Nationalized
    @Column(name = "ghi_chu")
    private String ghiChu;

    @Column(name = "trang_thai_ca")
    private boolean trangThaiCa;

    public CaLamViec(NhanVien nhanVien, LocalDateTime gioMoCa, double tienDauCa, String ghiChu, boolean trangThaiCa) {
        this.nhanVien = nhanVien;
        this.gioMoCa = gioMoCa;
        this.tienDauCa = tienDauCa;
        this.ghiChu = ghiChu;
        this.trangThaiCa = trangThaiCa;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CaLamViec caLamViec = (CaLamViec) o;
        return Objects.equals(nhanVien, caLamViec.nhanVien) && Objects.equals(gioMoCa, caLamViec.gioMoCa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nhanVien, gioMoCa);
    }
}