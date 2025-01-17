package dao;

import entity.ChiTietHoaDon;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.ArrayList;

public class CT_HoaDon_DAO {
    private final EntityManager em;
    private final EntityTransaction transaction;

    public CT_HoaDon_DAO(EntityManager em) {
        this.em = em;
        this.transaction = em.getTransaction();
    }

    public ArrayList<ChiTietHoaDon> getAllCT_HoaDon() {
        String sql = "Select * from ChiTietHoaDon";
        return (ArrayList<ChiTietHoaDon>) em.createNativeQuery(sql, ChiTietHoaDon.class).getResultList();
    }

    public boolean create(ChiTietHoaDon cthd) {
        transaction.begin();
        try {
            em.persist(cthd);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean update(ChiTietHoaDon cthd) {
        return executeTransaction(() -> em.merge(cthd));
    }

    public boolean delete(ChiTietHoaDon cthd) {
        return executeTransaction(() -> em.remove(cthd));
    }

    public ChiTietHoaDon getCT_HoaDon(String maHD, String maVe) {
        String sql = "Select * from ChiTietHoaDon where ma_hd = ? and ma_ve = ?";
        return (ChiTietHoaDon) em.createNativeQuery(sql, ChiTietHoaDon.class).setParameter(1, maHD).setParameter(2, maVe).getSingleResult();
    }

    public ChiTietHoaDon getCT_HoaDonTheoMaVe(String maVe) {
        String sql = "Select * from ChiTietHoaDon where ma_ve = ?";
        return (ChiTietHoaDon) em.createNativeQuery(sql, ChiTietHoaDon.class).setParameter(1, maVe).getSingleResult();
    }

    public ArrayList<ChiTietHoaDon> getCT_HoaDon(String maHD) {
        String sql = "Select * from ChiTietHoaDon where ma_hd = ?";
        return (ArrayList<ChiTietHoaDon>) em.createNativeQuery(sql, ChiTietHoaDon.class).setParameter(1, maHD).getResultList();
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

