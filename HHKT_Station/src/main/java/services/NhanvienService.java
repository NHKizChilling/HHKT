package services;

import dao.NhanVien_DAO;
import entity.NhanVien;
import jakarta.persistence.EntityManager;

import java.util.ArrayList;

public class NhanvienService {

    private NhanVien_DAO nhanVien_DAO;


    public NhanvienService(EntityManager em) {
        nhanVien_DAO = new NhanVien_DAO(em);
    }

    public boolean addNhanVien(NhanVien nv) {
        return nhanVien_DAO.create(nv);
    }

    public boolean updateNhanVien(NhanVien nv) {
        return nhanVien_DAO.updateInfo(nv);
    }

    public boolean updateTinhTrangCV(String maNV, String tinhTrangCV) {
        return nhanVien_DAO.updateTinhTrangCV(maNV, tinhTrangCV);
    }

    public NhanVien getNhanVienTheoId(String maNV) {
        return nhanVien_DAO.getNhanVien(maNV);
    }

    public NhanVien getNhanVienTheoTen(String tenNV) {
        return nhanVien_DAO.getNhanVienTheoTen(tenNV);
    }

    public NhanVien getNhanVienTheoSDT(String sdt) {
        return nhanVien_DAO.getNhanVienTheoSDT(sdt);
    }

    public ArrayList<NhanVien> getAll() {
        return nhanVien_DAO.getAll();
    }

    public ArrayList<NhanVien> getDSQuanLy() {
        return nhanVien_DAO.getDSQuanLy();
    }

    public ArrayList<NhanVien> getDSNhanVien() {
        return nhanVien_DAO.getDSNhanVien();
    }

}
