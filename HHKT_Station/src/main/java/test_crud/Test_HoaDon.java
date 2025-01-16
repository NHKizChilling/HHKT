package test_crud;

import dao.CT_HoaDon_DAO;
import dao.HoaDon_DAO;
import dao.Ve_DAO;
import entity.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Test_HoaDon {
    public static void main(String[] args) {
        EntityManager em = Persistence.createEntityManagerFactory("HHKT Station").createEntityManager();
        Ve_DAO ve_dao = new Ve_DAO(em);
        HoaDon_DAO hoaDon_dao = new HoaDon_DAO(em);
        CT_HoaDon_DAO ct_hoaDon_dao = new CT_HoaDon_DAO(em);
        try {
            hoaDon_dao.create(new HoaDon("HD250116000002", new NhanVien("NV0001"), new KhachHang("KH00000001"), LocalDateTime.now(), null, 0, 0, false));
            ve_dao.create(new Ve("VE250116000002", new KhachHang("KH00000001"), new ChiTietLichTrinh(new ChoNgoi("SE2T1CN1"), new LichTrinh("LTSE2180125SGHN")), new LoaiVe("VHSSV"), "Nguyễn Văn A", "072910029182", null, "DaBan", false));
            hoaDon_dao.update(new HoaDon("HD250116000002", new NhanVien("NV0001"), new KhachHang("KH00000001"), LocalDateTime.now(), null, 100000, 15000, true));
            ct_hoaDon_dao.create(new ChiTietHoaDon(new HoaDon("HD250116000002"), new Ve("VE250116000002"), 100000, 15000));
            ve_dao.update(new Ve("VE250116000002", new KhachHang("KH00000001"), new ChiTietLichTrinh(new ChoNgoi("SE2T1CN2"), new LichTrinh("LTSE2180125SGHN")), new LoaiVe("VHSSV"), "Nguyễn Văn A", "072910029182", null, "DaDoi", false));
//            hoaDon_dao.delete(new HoaDon("HD250116000002"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
