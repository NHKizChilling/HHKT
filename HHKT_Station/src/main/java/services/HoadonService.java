package services;

import dao.CT_HoaDon_DAO;
import dao.HoaDon_DAO;
import dao.KhuyenMai_DAO;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.KhuyenMai;
import jakarta.persistence.EntityManager;

import java.util.ArrayList;

public class HoadonService {
    private HoaDon_DAO hoaDon_DAO;
    private CT_HoaDon_DAO ct_hoaDon_DAO;
    private KhuyenMai_DAO khuyenMai_DAO;

    public HoadonService(EntityManager em) {
        hoaDon_DAO = new HoaDon_DAO(em);
        ct_hoaDon_DAO = new CT_HoaDon_DAO(em);
        khuyenMai_DAO = new KhuyenMai_DAO(em);
    }

    public boolean taoHoaDon(HoaDon hoaDon) {
        return hoaDon_DAO.create(hoaDon);
    }

    public boolean updateHoaDon(HoaDon hoaDon) {
        return hoaDon_DAO.update(hoaDon);
    }

    public boolean xoaHoaDon(HoaDon hoaDon) {
        return hoaDon_DAO.delete(hoaDon);
    }

    public boolean taoCTHoaDon(ArrayList<ChiTietHoaDon> dsCTHoaDon) {
        for (ChiTietHoaDon ctHoaDon : dsCTHoaDon) {
            if (!ct_hoaDon_DAO.create(ctHoaDon)) {
                return false;
            }
        }
        return true;
    }

    public ArrayList<HoaDon> getDSHoaDon() {
        return hoaDon_DAO.getAllHoaDon();
    }

    public ArrayList<ChiTietHoaDon> getDSCTHoaDon(String maHoaDon) {
        return ct_hoaDon_DAO.getCT_HoaDon(maHoaDon);
    }

    public HoaDon getHoaDonTheoMa(String maHoaDon) {
        return hoaDon_DAO.getHoaDonTheoMa(maHoaDon);
    }

    public HoaDon getHoaDonVuaTao() {
        return hoaDon_DAO.getHoaDonVuaTao();
    }

    public boolean taoHoaDonTemp(HoaDon hoaDon) {
        return hoaDon_DAO.createTempInvoice(hoaDon);
    }

    // TODO: Viết thêm business logic sau
}
