package services;

import dao.KhachHang_DAO;
import dao.Ve_DAO;
import entity.KhachHang;
import entity.Ve;
import jakarta.persistence.EntityManager;

import java.util.ArrayList;

public class KhachhangService {

    private KhachHang_DAO khachHang_DAO;
    private Ve_DAO ve_DAO;

    public KhachhangService(EntityManager em) {
        khachHang_DAO = new KhachHang_DAO(em);
        ve_DAO = new Ve_DAO(em);
    }

    public boolean addKhachHang(KhachHang kh) {
        return khachHang_DAO.create(kh);
    }

    public KhachHang getKhachHangTheoId(String maKH) {
        return khachHang_DAO.getKhachHang(maKH);
    }

    public KhachHang getKhachHangTheoCCCD(String soCCCD) {
        return khachHang_DAO.getKHTheoCCCD(soCCCD);
    }

    public KhachHang getKhachHangTheoSDT(String sdt) {
        return khachHang_DAO.getKhachHangTheoSDT(sdt);
    }

    public boolean updateKhachHang(KhachHang kh) {
        return khachHang_DAO.update(kh);
    }

    private ArrayList<Ve> getDSVeTheoMaKH(String maKH) {
        return ve_DAO.getDSVeTheoMaKH(maKH);
    }
}
