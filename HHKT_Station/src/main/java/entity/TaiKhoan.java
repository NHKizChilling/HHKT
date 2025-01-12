package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Nationalized;

import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TaiKhoan {
    @Id
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ma_nv", nullable = false)
    private NhanVien nhanVien;

    @Id
    @Column(name = "mat_khau", nullable = false, length = 50)
    private String matKhau;

    @Nationalized
    @ColumnDefault("N'Đang hoạt động'")
    @Column(name = "trang_thai_tk", length = 20)
    private String trangThaiTK;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TaiKhoan taiKhoan = (TaiKhoan) o;
        return Objects.equals(nhanVien, taiKhoan.nhanVien) && Objects.equals(matKhau, taiKhoan.matKhau);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nhanVien, matKhau);
    }
}