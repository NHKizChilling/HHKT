package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class HoaDon implements Serializable {
    @Id
    @ColumnDefault("[dbo].[auto_idhd]()")
    @Column(name = "ma_hd", nullable = false, columnDefinition = "char(14)")
    private String maHD;

    @ManyToOne
    @JoinColumn(name = "ma_nv", nullable = false)
    private entity.NhanVien nhanVien;

    @ManyToOne
    @JoinColumn(name = "ma_kh", nullable = false)
    private entity.KhachHang khachHang;

    @ColumnDefault("getdate()")
    @Column(name = "ngay_lap_hoa_don", nullable = false)
    private LocalDateTime ngayLapHoaDon;

    @ManyToOne
    @JoinColumn(name = "ma_km")
    private entity.KhuyenMai khuyenMai;

    @Column(name = "tong_tien", nullable = false, columnDefinition = "money")
    private double tongTien;

    @Column(name = "tong_giam_gia", nullable = false, columnDefinition = "money")
    private double tongGiamGia;

    @Column(name = "trang_thai", nullable = false)
    private boolean trangThai = false;

    public HoaDon(String maHD) {
        this.maHD = maHD;
    }

    public HoaDon(String maHD, NhanVien nhanVien, KhachHang khachHang, LocalDateTime ngayLapHoaDon, KhuyenMai khuyenMai, boolean trangThai) {
        this.maHD = maHD;
        this.nhanVien = nhanVien;
        this.khachHang = khachHang;
        this.ngayLapHoaDon = ngayLapHoaDon;
        this.khuyenMai = khuyenMai;
        this.trangThai = trangThai;
    }

    public void tinhTongTien(ArrayList<ChiTietHoaDon> dsChiTietHoaDon) {
        double tongTien = 0;
        double phiDichVu = dsChiTietHoaDon.size() * 2000;
        double tongKM = 0;

        for (ChiTietHoaDon chiTietHoaDon : dsChiTietHoaDon) {
            tongTien += (chiTietHoaDon.getGiaVe() - 2000 );
            tongKM += (chiTietHoaDon.getGiaVe() - 2000) * khuyenMai.getMucKM();
        }
        tongTien -= tongKM;
        tongTien *= 1.1;
        tongTien += phiDichVu;
        this.tongTien = tongTien;
    }

    public void tinhTongGiamGia(ArrayList<ChiTietHoaDon> dsChiTietHoaDon) {
        double tongGiamGia = 0;
        double tongKM = 0;
        for (ChiTietHoaDon chiTietHoaDon : dsChiTietHoaDon) {
            tongGiamGia += chiTietHoaDon.getGiaGiam();
            tongKM += (chiTietHoaDon.getGiaVe() - 2000) * khuyenMai.getMucKM();
        }
        this.tongGiamGia = tongGiamGia + tongKM;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        HoaDon hoaDon = (HoaDon) o;
        return Objects.equals(maHD, hoaDon.maHD);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(maHD);
    }
}