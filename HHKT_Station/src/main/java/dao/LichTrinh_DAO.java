package dao;

import entity.LichTrinh;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class LichTrinh_DAO {
    private final EntityManager em;
    private final EntityTransaction transaction;

    public LichTrinh_DAO(EntityManager em) {
        this.em = em;
        this.transaction = em.getTransaction();
    }

    public ArrayList<LichTrinh> getAll() {
        String sql = "Select * from LichTrinh";
        return (ArrayList<LichTrinh>) em.createNativeQuery(sql, LichTrinh.class).getResultList();
    }

    public LichTrinh getLichTrinhTheoID(String maLichTrinh) {
        String sql = "Select * from LichTrinh where ma_lich_trinh = ?";
        return (LichTrinh) em.createNativeQuery(sql, LichTrinh.class).setParameter(1, maLichTrinh).getSingleResult();
    }

    public ArrayList<LichTrinh> getDSLichTrinhTheoTrangThai(boolean trangThai) {
        String sql = "Select * from LichTrinh where trang_thai = ?";
        return (ArrayList<LichTrinh>) em.createNativeQuery(sql, LichTrinh.class).setParameter(1, trangThai).getResultList();
    }

    public ArrayList<LichTrinh> traCuuDSLichTrinh(String MaGaDi, String MaGaDen) {
        String sql = "Select * from LichTrinh where ma_ga_di = ? and ma_ga_den = ?";
        return (ArrayList<LichTrinh>) em.createNativeQuery(sql, LichTrinh.class).setParameter(1, MaGaDi).setParameter(2, MaGaDen).getResultList();
    }

    public ArrayList<LichTrinh> traCuuDSLichTrinh(String MaGaDi, String MaGaDen, LocalDate ngayDi) {
        String sql = "Select * from LichTrinh where ma_ga_di = ? and ma_ga_den = ? and year(thoi_gian_khoi_hanh) = ? and month(thoi_gian_khoi_hanh) = ? and day(thoi_gian_khoi_hanh) = ?";
        return (ArrayList<LichTrinh>) em.createNativeQuery(sql, LichTrinh.class).setParameter(1, MaGaDi).setParameter(2, MaGaDen).setParameter(3, ngayDi.getYear()).setParameter(4, ngayDi.getMonth().getValue()).setParameter(5, ngayDi.getDayOfMonth()).getResultList();
    }

    public ArrayList<LichTrinh> traCuuDSLichTrinhSauNgayHienTai() {
        String sql = "Select * from LichTrinh where thoi_gian_khoi_hanh > ?";
        return (ArrayList<LichTrinh>) em.createNativeQuery(sql, LichTrinh.class).setParameter(1, Timestamp.valueOf(LocalDateTime.now())).getResultList();
    }

    public ArrayList<LichTrinh> traCuuDSLichTrinhTheoNgay(LocalDate ngayDi) {
        String sql = "Select * from LichTrinh where year(thoi_gian_khoi_hanh) = ? and month(thoi_gian_khoi_hanh) = ? and day(thoi_gian_khoi_hanh) = ?";
        return (ArrayList<LichTrinh>) em.createNativeQuery(sql, LichTrinh.class).setParameter(1, ngayDi.getYear()).setParameter(2, ngayDi.getMonth().getValue()).setParameter(3, ngayDi.getDayOfMonth()).getResultList();
    }

    public int getSoLuongChoConTrong(String maLichTrinh) {
        int soLuongChoConTrong = 0;
        String sql = "select count(*) from LichTrinh a join ChiTietLichTrinh b on a.ma_lich_trinh = b.ma_lich_trinh join ChoNgoi c on b.ma_cho = c.ma_cho where a.ma_lich_trinh= ? and b.trang_thai = 1";
        soLuongChoConTrong = (int) em.createNativeQuery(sql, LichTrinh.class).setParameter(1, maLichTrinh).getSingleResult();
        return soLuongChoConTrong;
    }

    public boolean updateTrangThaiChuyenTau(String maLichTrinh, boolean trangThai) {
        return executeTransaction(() -> {
            String sql = "UPDATE LichTrinh SET trang_thai = ? WHERE ma_lich_trinh = ?";
            em.createNativeQuery(sql, LichTrinh.class)
                    .setParameter(1, trangThai)
                    .setParameter(2, maLichTrinh)
                    .executeUpdate();
        });
    }

    public boolean updateTrangThaiCT(boolean trangThai) {
        return executeTransaction(() -> {
            String sql = "UPDATE LichTrinh SET trang_thai = ?";
            em.createNativeQuery(sql)
                    .setParameter(1, trangThai)
                    .executeUpdate();
        });
    }

    public boolean update(LichTrinh lichTrinh) {
        return executeTransaction(() -> em.merge(lichTrinh));
    }

    public boolean updateInfo(LichTrinh lichTrinh) {
        return executeTransaction(() -> {
            String sql = "UPDATE LichTrinh SET ma_ga_di = ?, ma_ga_den = ?, thoi_gian_khoi_hanh = ?, thoi_gian_du_kien_den = ?, trang_thai = ? WHERE ma_lich_trinh = ?";
            em.createNativeQuery(sql)
                    .setParameter(1, lichTrinh.getGaDi().getMaGa())
                    .setParameter(2, lichTrinh.getGaDen().getMaGa())
                    .setParameter(3, Timestamp.valueOf(lichTrinh.getThoiGianKhoiHanh()))
                    .setParameter(4, Timestamp.valueOf(lichTrinh.getThoiGianDuKienDen()))
                    .setParameter(5, lichTrinh.isTrangThai())
                    .setParameter(6, lichTrinh.getMaLichTrinh())
                    .executeUpdate();
        });
    }

    public boolean create(LichTrinh lichTrinh) {
        return executeTransaction(() -> em.persist(lichTrinh));
    }

    public LichTrinh getOne(String maLichTrinh) {
        LichTrinh lt = em.find(LichTrinh.class, maLichTrinh);
        return lt;
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
