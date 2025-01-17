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
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "KhachHang", uniqueConstraints = {
        @UniqueConstraint(name = "UQ__socccd__kh", columnNames = {"socccd"}),
        @UniqueConstraint(name = "UQ__sdt__kh", columnNames = {"sdt"})
})
public class KhachHang {
    @Id
//    @ColumnDefault("[dbo].[auto_idkh]()")
    @Column(name = "ma_kh", nullable = false, columnDefinition = "char(10)")
    private String maKH;

    @Nationalized
    @Column(name = "ten_kh", nullable = false, length = 50)
    private String tenKH;

    @Column(name = "socccd", nullable = false, columnDefinition = "char(12)")
    private String soCCCD;

    @Column(name = "sdt", nullable = false, columnDefinition = "char(10)")
    private String sdt;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    public KhachHang(String maKH) {
        this.maKH = maKH;
    }

    public KhachHang(String tenKH, String soCCCD, String sdt, String email) {
        this.tenKH = tenKH;
        this.soCCCD = soCCCD;
        this.sdt = sdt;
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        KhachHang khachHang = (KhachHang) o;
        return Objects.equals(maKH, khachHang.maKH);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(maKH);
    }
}