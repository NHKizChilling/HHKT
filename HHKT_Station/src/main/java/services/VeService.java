package services;

import dao.LoaiVe_DAO;
import dao.Ve_DAO;
import entity.LoaiVe;
import entity.Ve;
import jakarta.persistence.EntityManager;

import java.util.ArrayList;

public class VeService {
    private Ve_DAO ve_DAO;
    private LoaiVe_DAO loaiVe_DAO;

    public VeService(EntityManager em) {
        ve_DAO = new Ve_DAO(em);
        loaiVe_DAO = new LoaiVe_DAO(em);
    }

    public boolean taoVe(ArrayList<Ve> dsVe) {
        for (Ve ve : dsVe) {
            if (!ve_DAO.create(ve)) {
                return false;
            }
        }
        return true;
    }

    public boolean doiVe(Ve ve) {
        if (ve.getTinhTrangVe().equals("DaHuy") || ve.getTinhTrangVe().equals("DaDoi")) {
            return false;
        }
        return ve_DAO.updateTinhTrangVe(ve.getMaVe(), "DaDoi");
    }

    public boolean huyVe(Ve ve) {
        return ve_DAO.updateTinhTrangVe(ve.getMaVe(), "DaHuy");
    }

    public Ve getVeTheoID(String maVe) {
        return ve_DAO.getVeTheoID(maVe);
    }

    public int checkInput(Ve ve) {
        String regexName = "^[a-zA-Z\\s]*$";
        String regexPhone = "0\\d{9,10}";
        String regexEmail = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
        String regexCCCD = "0\\d{11}";

        if (!ve.getTenKH().matches(regexName)) {
            return 1;
        }
        if (!ve.getKhachHang().getSdt().matches(regexPhone)) {
            return 2;
        }
        if (!ve.getKhachHang().getEmail().matches(regexEmail)) {
            return 3;
        }
        if (!ve.getKhachHang().getSoCCCD().matches(regexCCCD)) {
            return 4;
        }
        return 0;
    }

    // TODO: Viết thêm business logic sau
}
