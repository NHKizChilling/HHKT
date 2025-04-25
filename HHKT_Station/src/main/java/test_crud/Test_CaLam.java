package test_crud;

import dao.CaLamViec_DAO;
import dao.NhanVien_DAO;
import entity.CaLamViec;
import entity.NhanVien;

import java.time.LocalDateTime;

public class Test_CaLam {
    public static void main(String[] args) {
        CaLamViec_DAO caLamViec_dao = new CaLamViec_DAO();
        NhanVien_DAO nhanVien_dao = new NhanVien_DAO();

        NhanVien nv = nhanVien_dao.getNhanVien("21814");

        // Test create
        try {
            CaLamViec tmp = new CaLamViec(nv, LocalDateTime.of(2025, 1, 17, 8, 0), LocalDateTime.of(2025, 1, 17, 16, 0),
                    1000000, 10000000, "Không có", true);
            if (caLamViec_dao.create(tmp)) {
                System.out.println("Thêm ca làm việc thành công");
            } else {
                System.out.println("Thêm ca làm việc thất bại");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Test update
        try {
            CaLamViec tmp = caLamViec_dao.getCaLamViec(nv.getMaNV(), LocalDateTime.of(2025, 1, 17, 8, 0));
            System.out.println("Trước khi update:");
            System.out.println(tmp.getGhiChu());

            tmp.setGhiChu("Hết tiền rồi");
            if (caLamViec_dao.update(tmp)) {
                System.out.println("Cập nhật ca làm việc thành công");
            } else {
                System.out.println("Cập nhật ca làm việc thất bại");
            }
            tmp = caLamViec_dao.getCaLamViec(nv.getMaNV(), LocalDateTime.of(2025, 1, 17, 8, 0));
            System.out.println("Sau khi update:");
            System.out.println(tmp.getGhiChu());

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Test delete
        try {
            CaLamViec tmp = caLamViec_dao.getCaLamViec(nv.getMaNV(), LocalDateTime.of(2025, 1, 17, 8, 0));
            if (caLamViec_dao.delete(tmp.getNhanVien().getMaNV(), LocalDateTime.of(2025, 1, 17, 8, 0))) {
                System.out.println("Xóa ca làm việc thành công");
            } else {
                System.out.println("Xóa ca làm việc thất bại");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
