package dao;

import entity.HoaDon;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class HoaDon_DAO {
    private final EntityManager em;
    private final EntityTransaction transaction;

    public HoaDon_DAO(EntityManager em) {
        this.em = em;
        this.transaction = em.getTransaction();
    }

    public ArrayList<HoaDon> getAllHoaDon() {
        String sql = "Select * from HoaDon";
        return (ArrayList<HoaDon>) em.createNativeQuery(sql, HoaDon.class).getResultList();
    }

    public HoaDon getHoaDonTheoMa(String maHoaDon) {
        return em.find(HoaDon.class, maHoaDon);
    }

    public ArrayList<HoaDon> getHoaDonTheoNV(String maNV, LocalDateTime ngayLap) {
        String sql = "Select * from HoaDon where ma_nv = ? and YEAR(ngay_lap_hoa_don) = ? and MONTH(ngay_lap_hoa_don) = ? and DAY(ngay_lap_hoa_don) = ?";
        return (ArrayList<HoaDon>)  em.createNativeQuery(sql, HoaDon.class)
                .setParameter(1, maNV)
                .setParameter(2, ngayLap.getYear())
                .setParameter(3, ngayLap.getMonthValue())
                .setParameter(4, ngayLap.getDayOfMonth())
                .getResultList();
    }

    public ArrayList<HoaDon> getHoaDonTheoKH(String maKH) {
        String sql = "Select * from HoaDon where ma_kh = ?";
        return (ArrayList<HoaDon>) em.createNativeQuery(sql, HoaDon.class).setParameter(1, maKH).getResultList();
    }

    public ArrayList<HoaDon> getHoaDonTheoNV(String maNV) {
        String sql = "Select * from HoaDon where ma_nv = ?";
        return (ArrayList<HoaDon>) em.createNativeQuery(sql, HoaDon.class).setParameter(1, maNV).getResultList();
    }

    public HoaDon getHoaDonVuaTao() {
        String sql = "Select * from HoaDon where trang_thai = 0 and tong_tien = 0";
        return (HoaDon) em.createNativeQuery(sql, HoaDon.class).getSingleResult();
    }

    public boolean create(HoaDon hoaDon) {
        return executeTransaction(() -> em.persist(hoaDon));
    }

    public boolean createTempInvoice(HoaDon hoaDon) {
        String sql = "insert into HoaDon(ma_nv, ma_kh, ngay_lap_hoa_don, tong_tien, tong_giam_gia, trang_thai) values(?,?,?,0,0,?)";
        try {
            em.createNativeQuery(sql)
                    .setParameter(1, hoaDon.getNhanVien().getMaNV())
                    .setParameter(2, hoaDon.getKhachHang().getMaKH())
                    .setParameter(3, Timestamp.valueOf(hoaDon.getNgayLapHoaDon()))
                    .setParameter(4, hoaDon.isTrangThai())
                    .executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public ArrayList<HoaDon> getDSHDLuuTam() {
        String sql = "Select * from HoaDon where trang_thai = 0 and tong_tien != 0";
        return (ArrayList<HoaDon>) em.createNativeQuery(sql, HoaDon.class).getResultList();
    }

    public boolean update(HoaDon hoaDon) {
        return executeTransaction(() -> em.merge(hoaDon));
    }

    public boolean delete(HoaDon hoaDon) {
        return executeTransaction(() -> em.remove(hoaDon));
    }

    public ArrayList<HoaDon> getDSHDTheoNam(String nam) {
        String sql = "Select * from HoaDon where YEAR(ngay_lap_hoa_don) = ?";
        return (ArrayList<HoaDon>) em.createNativeQuery(sql, HoaDon.class).setParameter(1, nam).getResultList();
    }

    public ArrayList<HoaDon> getDSHDTheoThang(int thang, int nam) {
        String sql = "Select * from HoaDon where YEAR(ngay_lap_hoa_don) = ? and MONTH(ngay_lap_hoa_don) = ?";
        return (ArrayList<HoaDon>) em.createNativeQuery(sql, HoaDon.class)
                .setParameter(1, nam)
                .setParameter(2, thang)
                .getResultList();
    }

    public ArrayList<HoaDon> getDSHDTheoNgay(LocalDateTime ngayLap) {
        String sql = "Select * from HoaDon where YEAR(ngay_lap_hoa_don) = ? and MONTH(ngay_lap_hoa_don) = ? and DAY(ngay_lap_hoa_don) = ?";
        return (ArrayList<HoaDon>) em.createNativeQuery(sql, HoaDon.class)
                .setParameter(1, ngayLap.getYear())
                .setParameter(2, ngayLap.getMonthValue())
                .setParameter(3, ngayLap.getDayOfMonth())
                .getResultList();
    }

    private boolean executeTransaction(Runnable action) {
        try {
            transaction.begin();
            action.run();
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }
}
