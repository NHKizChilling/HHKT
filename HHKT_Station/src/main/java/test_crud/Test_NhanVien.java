import dao.NhanVien_DAO;
import dao.TaiKhoan_DAO;
import entity.NhanVien;
import entity.TaiKhoan;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

import java.time.LocalDate;

public class Test_NhanVien {
    public static void main(String[] args) {
        EntityManager em = Persistence.createEntityManagerFactory("HHKT Station").createEntityManager();
        NhanVien_DAO nhanVien_dao = new NhanVien_DAO();
        TaiKhoan_DAO taiKhoan_dao = new TaiKhoan_DAO();
        NhanVien nv;
        TaiKhoan tk;

        // Test create
        try {
            nv = new NhanVien("NV0001", "Nguyen Van A", "0123456789",
                    LocalDate.of(1999, 1, 1), true, "0798066472",
                    "kkk@gmail.com", "Nhân viên", "Đang làm");

            tk = new TaiKhoan(nv, "123456", "Đang hoạt động");

            if (nhanVien_dao.create(nv)) {
                System.out.println("Thêm nhân viên thành công");
            } else {
                System.out.println("Thêm nhân viên thất bại");
            }

            if (taiKhoan_dao.create(tk)) {
                System.out.println("Thêm tài khoản thành công");
            } else {
                System.out.println("Thêm tài khoản thất bại");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

       // Test update
        try {
            nv = nhanVien_dao.getNhanVien("NV0001");
            tk = taiKhoan_dao.getTaiKhoanTheoMaNV("NV0001");

            System.out.println("Trước khi update:");
            System.out.println(nv.getTenNV());
            System.out.println(nv.getSdt());
            System.out.println(tk.getMatKhau());

            System.out.println("\nSau khi update:");
            nhanVien_dao.updateInfo(nv);
            tk.setMatKhau("654321");
            taiKhoan_dao.doiMatKhau("NV0001", "654321");
            System.out.println(nhanVien_dao.getNhanVien("NV0001").getSdt());
            System.out.println(taiKhoan_dao.getTaiKhoanTheoMaNV("NV0001").getMatKhau());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // Test delete
        try {
            if (taiKhoan_dao.delete("NV0001")) {
                System.out.println("Xóa tài khoản thành công");
            } else {
                System.out.println("Xóa tài khoản thất bại");
            }

            if (nhanVien_dao.delete("NV0001")) {
                System.out.println("Xóa nhân viên thành công");
            } else {
                System.out.println("Xóa nhân viên thất bại");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
