package dao;

import entity.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import util.JPAUtil;

import java.util.List;

public class Ve_DAO {
    private final EntityManager em;

    public Ve_DAO() {
        this.em = JPAUtil.getEntityManager();
    }

    public List<Ve> getAllVe() {

        try {
            return em.createQuery("from Ve", Ve.class).getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public Ve getVeTheoID(String maVe) {

        return em.find(Ve.class, maVe);
    }

    public Ve getLaiVe() {
        // Lấy vé cuối cùng đã bán
        String sql = "from Ve where tinhTrangVe = 'DaBan' ORDER BY maVe DESC";

        try {
            return em.createQuery(sql, Ve.class).getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public boolean create(Ve ve) {
        return executeTransaction(() -> em.persist(ve));
    }


    public String getAutoGenerateID() {
        String sql = "SELECT dbo.auto_idve()";
        try {
            return em.createNativeQuery(sql, String.class).getSingleResult().toString();
        } catch (Exception e) {
            return null;
        }
    }

    public boolean update(Ve ve) {
        return executeTransaction(() -> em.merge(ve));
    }

    public boolean updateTinhTrangVe(String maVe, String tinhTrangVe) {
        return executeTransaction(() -> {
            Ve ve = getVeTheoID(maVe);
            ve.setTinhTrangVe(tinhTrangVe);
            em.merge(ve);
        });
    }

    public List<Ve> getDSVeTheoMaKH(String maKH) {
        String sql = "from Ve where khachHang.maKH = :makh";

        try {
            return em.createQuery(sql, Ve.class).setParameter("makh", maKH).getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Ve> getVeTheoTinhTrang(String tinhTrangVe) {
        String sql = "from Ve where tinhTrangVe = :tinhTrangVe";

        try {
            return em.createQuery(sql, Ve.class).setParameter("tinhTrangVe", tinhTrangVe).getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    private boolean executeTransaction(Runnable action) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            action.run();
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            return false;
        }
    }
}
