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

        return em.createQuery("from Ve", Ve.class).getResultList();
    }

    public Ve getVeTheoID(String maVe) {
        String sql = "from Ve where maVe = :maVe";
        return em.createQuery(sql, Ve.class).setParameter("maVe", maVe).getSingleResult();
    }

    public Ve getLaiVe() {
        // Lấy vé cuối cùng đã bán
        String sql = "from Ve where tinhTrangVe = 'DaBan' ORDER BY maVe DESC LIMIT 1";
        return em.createQuery(sql, Ve.class).getSingleResult();
    }

    public boolean create(Ve ve) {
        return executeTransaction(() -> em.persist(ve));
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
        return em.createQuery(sql, Ve.class).setParameter("makh", maKH).getResultList();
    }

    public List<Ve> getVeTheoTinhTrang(String tinhTrangVe) {
        String sql = "from Ve where tinhTrangVe = :tinhTrangVe";
        return em.createQuery(sql, Ve.class).setParameter("tinhTrangVe", tinhTrangVe).getResultList();
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
            e.printStackTrace();
            return false;
        }
    }
}
