package test_crud;

import dao.*;
import entity.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test_HoaDon {
    public static void main(String[] args) {
        EntityManager em = Persistence.createEntityManagerFactory("HHKT Station").createEntityManager();
        Ve_DAO ve_dao = new Ve_DAO(em);
        HoaDon_DAO hoaDon_dao = new HoaDon_DAO(em);
        CT_HoaDon_DAO ct_hoaDon_dao = new CT_HoaDon_DAO(em);
        CT_LichTrinh_DAO ct_lichTrinh_dao = new CT_LichTrinh_DAO(em);
        LoaiVe_DAO loaiVe_dao = new LoaiVe_DAO(em);
        KhuyenMai_DAO khuyenMai_dao = new KhuyenMai_DAO(em);
        try {
            //create
            KhuyenMai khuyenMai = new KhuyenMai("KM250116SV", "Giảm 10% cho sinh viên", LocalDate.now(), LocalDate.now().plusDays(7), 0.1F, true);
            khuyenMai_dao.themKhuyenMai(khuyenMai);
            HoaDon hoaDon = new HoaDon("HD250116000002", new NhanVien("NV0001"), new KhachHang("KH00000001"), LocalDateTime.now(), null, 0, 0, false);
            hoaDon_dao.create(hoaDon);
            ChiTietLichTrinh chiTietLichTrinh = ct_lichTrinh_dao.getCTLTTheoCN("LTSE2180125SGHN", "SE2T1CN1");
            Ve ve = new Ve("VE250116000002", new KhachHang("KH00000001"), chiTietLichTrinh, loaiVe_dao.getLoaiVeTheoMa("VHSSV"), "Nguyễn Văn A", "072910029182", null, "DaBan", false);
            ve_dao.create(ve);
            ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon(new HoaDon("HD250116000002"), ve);
            chiTietHoaDon.tinhGiaVe();
            chiTietHoaDon.tinhGiaGiam();
            ct_hoaDon_dao.create(chiTietHoaDon);
            hoaDon.setKhuyenMai(khuyenMai);
            hoaDon.tinhTongGiamGia(new ArrayList<>(List.of(chiTietHoaDon)));
            hoaDon.tinhTongTien(new ArrayList<>(List.of(chiTietHoaDon)));
            hoaDon.setTrangThai(true);
            hoaDon_dao.update(hoaDon);
            ct_lichTrinh_dao.updateCTLT(chiTietLichTrinh, false);

            //update
            ChiTietLichTrinh ctlt = ve.getChiTietLichTrinh();
            ve_dao.update(new Ve("VE250116000002", new KhachHang("KH00000001"), ct_lichTrinh_dao.getCTLTTheoCN("LTSE2180125SGHN", "SE2T1CN2"), new LoaiVe("VHSSV"), "Nguyễn Văn A", "072910029182", null, "DaDoi", false));
            chiTietHoaDon.tinhGiaVe();
            chiTietHoaDon.tinhGiaGiam();
            hoaDon.tinhTongGiamGia(new ArrayList<>(List.of(chiTietHoaDon)));
            hoaDon.tinhTongTien(new ArrayList<>(List.of(chiTietHoaDon)));
            ct_hoaDon_dao.update(chiTietHoaDon);
            hoaDon_dao.update(hoaDon);
            ct_lichTrinh_dao.updateCTLT(ctlt, true);
            ct_lichTrinh_dao.updateCTLT(ve.getChiTietLichTrinh(), false);

//            hoaDon_dao.delete(new HoaDon("HD250116000002"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
