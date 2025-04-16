import dao.*;
import entity.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

import java.time.LocalDateTime;

public class Test_LichTrinh {
    public static void main(String[] args) {
        EntityManager em = Persistence.createEntityManagerFactory("HHKT Station").createEntityManager();
        LichTrinh_DAO lichTrinh_dao = new LichTrinh_DAO();
        CT_LichTrinh_DAO ctLichTrinh_dao = new CT_LichTrinh_DAO();
        try {
            ChuyenTau ct = new ChuyenTau("SE2", new LoaiTau("SE"));
            new ChuyenTau_DAO().create(ct);
            new Toa_DAO().create(new Toa("SE2T1", 1, new ChuyenTau("SE2"), new LoaiToa("TVIP")));
            new ChoNgoi_DAO().create(new ChoNgoi("SE2T1CN1", new Toa("SE2T1"), 1, 1, 1));
            new ChoNgoi_DAO().create(new ChoNgoi("SE2T1CN2", new Toa("SE2T1"), 2, 1, 1));
            LichTrinh lt = new LichTrinh("temp", new ChuyenTau("SE2"), new Ga("SG"), new Ga("HN"), LocalDateTime.now().plusDays(2), LocalDateTime.now().plusDays(4), true);
            lichTrinh_dao.create(lt);
            ctLichTrinh_dao.addChiTietLichTrinh(lichTrinh_dao.getAll().getLast().getMaLichTrinh());
            LichTrinh lt_before = lichTrinh_dao.getLichTrinhTheoID(lichTrinh_dao.getAll().getLast().getMaLichTrinh());
            LichTrinh lt_after = new LichTrinh();
            if (lichTrinh_dao.updateTrangThaiChuyenTau(lt_before.getMaLichTrinh(), true)) {
                lt_after = lichTrinh_dao.getLichTrinhTheoID(lichTrinh_dao.getAll().getLast().getMaLichTrinh());
            }
            System.out.println("Trước khi update: " + lt_before.getMaLichTrinh() + " - " + (lt_before.isTrangThai() ? "Đang hoạt động" : "Đã kết thúc"));
            System.out.println("Sau khi update: " + lt_after.getMaLichTrinh() + " - " + (lt_after.isTrangThai() ? "Đang hoạt động" : "Đã kết thúc"));
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
