package services;

import dao.CT_LichTrinh_DAO;
import dao.LichTrinh_DAO;
import entity.ChiTietLichTrinh;
import entity.LichTrinh;
import jakarta.persistence.EntityManager;

import java.time.LocalDate;
import java.util.ArrayList;

public class LichtrinhService {
    private LichTrinh_DAO lichTrinh_DAO;
    private CT_LichTrinh_DAO ct_lichTrinh_DAO;

    public LichtrinhService(EntityManager em) {
        lichTrinh_DAO = new LichTrinh_DAO(em);
        ct_lichTrinh_DAO = new CT_LichTrinh_DAO(em);
    }

    public boolean taoLichTrinh(LichTrinh lichTrinh) {
        return lichTrinh_DAO.create(lichTrinh);
    }

    public boolean taoCTLichTrinh(ChiTietLichTrinh ctLichTrinh) {
        return ct_lichTrinh_DAO.create(ctLichTrinh);
    }

    public boolean updateLichTrinh(LichTrinh lichTrinh) {
        return lichTrinh_DAO.update(lichTrinh);
    }

    public boolean updateCTLichTrinh(ChiTietLichTrinh ctLichTrinh) {
        return ct_lichTrinh_DAO.update(ctLichTrinh);
    }

    public ArrayList<LichTrinh> getDSLichTrinh() {
        return lichTrinh_DAO.getAll();
    }

    public ArrayList<LichTrinh> getDSLichTrinhTheoTrangThai(boolean trangThai) {
        return lichTrinh_DAO.getDSLichTrinhTheoTrangThai(trangThai);
    }

    public LichTrinh getLichTrinhTheoMa(String maLichTrinh) {
        return lichTrinh_DAO.getLichTrinhTheoID(maLichTrinh);
    }

    public ArrayList<ChiTietLichTrinh> getDSCTLichTrinh(String maLichTrinh) {
        return ct_lichTrinh_DAO.getCtltTheoMaLichTrinh(maLichTrinh);
    }

    public ArrayList<LichTrinh> filterLichTrinh(String diemDi, String diemDen, LocalDate ngayDi) {
        return lichTrinh_DAO.traCuuDSLichTrinh(diemDi, diemDen, ngayDi);
    }

    public ArrayList<LichTrinh> filterLichTrinh(String diemDi, String diemDen) {
        return lichTrinh_DAO.traCuuDSLichTrinh(diemDi, diemDen);
    }

    public int getSoLuongChoConTrong(String maLichTrinh) {
        return lichTrinh_DAO.getSoLuongChoConTrong(maLichTrinh);
    }
}
