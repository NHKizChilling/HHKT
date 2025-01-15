package dao;

import entity.KhachHang;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.ArrayList;

public class KhachHang_DAO {
    private final EntityManager em;
    private final EntityTransaction transaction;

    public KhachHang_DAO(EntityManager em) {
        this.em = em;
        this.transaction = em.getTransaction();
    }

    public ArrayList<KhachHang> getAllKhachHang() {
        String sql = "Select * from KhachHang";
        return (ArrayList<KhachHang>) em.createNativeQuery(sql, KhachHang.class).getResultList();
    }

    public boolean create(KhachHang kh) {
        return executeTransaction(() -> em.persist(kh));
    }

    public boolean update(KhachHang kh) {
        return executeTransaction(() -> em.merge(kh));
    }

    public boolean delete(String maKH) {
        return executeTransaction(() -> {
            KhachHang kh = getKhachHangTheoMaKH(maKH);
            em.remove(kh);
        });
    }

    public KhachHang getKhachHangTheoMaKH(String maKH) {
        String sql = "Select * from KhachHang where ma_kh = ?";
        return (KhachHang) em.createNativeQuery(sql, KhachHang.class).setParameter(1, maKH).getSingleResult();
    }

    public KhachHang getKHTheoCCCD(String soCCCD) {
        String sql = "Select * from KhachHang where socccd = ?";
        return (KhachHang) em.createNativeQuery(sql, KhachHang.class).setParameter(1, soCCCD).getSingleResult();
    }

    public KhachHang getKhachHangTheoSDT(String sdt) {
        String sql = "Select * from KhachHang where sdt = ?";
        return (KhachHang) em.createNativeQuery(sql, KhachHang.class).setParameter(1, sdt).getSingleResult();
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

    public KhachHang getKhachHang(String maKH) {
        return em.find(KhachHang.class, maKH);
    }
}


