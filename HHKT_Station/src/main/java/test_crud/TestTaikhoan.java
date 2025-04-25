package test_crud;

import dao.NhanVien_DAO;
import dao.TaiKhoan_DAO;
import entity.NhanVien;
import entity.TaiKhoan;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

public class TestTaikhoan {
    public static void main(String[] args) {
        NhanVien_DAO nhanVien_dao = new NhanVien_DAO();
        TaiKhoan_DAO taiKhoan_dao = new TaiKhoan_DAO();

        NhanVien nv = nhanVien_dao.getNhanVien("NV000000001");

        // Test create
        try {
            TaiKhoan tk = new TaiKhoan(nv, "123456", "Đang hoạt động");
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
            TaiKhoan tk = taiKhoan_dao.getTaiKhoanTheoMaNV("NV000000001");
            System.out.println("Trước khi update:");
            System.out.println("Mật khẩu: " + tk.getMatKhau());

            System.out.println("\nSau khi update:");
            tk = taiKhoan_dao.getTaiKhoanTheoMaNV("NV000000001");
            taiKhoan_dao.doiMatKhau("NV000000001", "654321");
            System.out.println("Mật khẩu: " + taiKhoan_dao.getTaiKhoanTheoMaNV("NV000000001").getMatKhau());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // Test delete
        try {
            if (taiKhoan_dao.delete("NV000000001")) {
                System.out.println("Xóa tài khoản thành công");
            } else {
                System.out.println("Xóa tài khoản thất bại");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
