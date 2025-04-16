package dao;

import entity.ChiTietHoaDon;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import util.JPAUtil;

import java.util.List;

public class CT_HoaDon_DAO {
    private final EntityManager em;

    public CT_HoaDon_DAO() {
        this.em = JPAUtil.getEntityManager();
    }

    public List<ChiTietHoaDon> getAllCT_HoaDon() {

        return em.createQuery("from ChiTietHoaDon", ChiTietHoaDon.class).getResultList();
    }

    public boolean create(ChiTietHoaDon cthd) {
        return executeTransaction(() -> em.persist(cthd));
    }

    public boolean update(ChiTietHoaDon cthd) {
        return executeTransaction(() -> em.merge(cthd));
    }

    public boolean delete(ChiTietHoaDon cthd) {
        return executeTransaction(() -> em.remove(cthd));
    }

    public ChiTietHoaDon getCT_HoaDon(String maHD, String maVe) {
        String sql = "from ChiTietHoaDon where hoaDon.maHD = :mahd and ve.maVe = :mave";
        return em.createQuery(sql, ChiTietHoaDon.class)
                .setParameter("mahd", maHD)
                .setParameter("mave", maVe)
                .getSingleResult();
    }

    public ChiTietHoaDon getCT_HoaDonTheoMaVe(String maVe) {
        String sql = "from ChiTietHoaDon where ve.maVe = :mave";
        return em.createQuery(sql, ChiTietHoaDon.class)
                .setParameter("mave", maVe)
                .getSingleResult();
    }

    public List<ChiTietHoaDon> getCT_HoaDon(String maHD) {
        String sql = "from ChiTietHoaDon where hoaDon.maHD = :mahd";
        return em.createQuery(sql, ChiTietHoaDon.class)
                .setParameter("mahd", maHD)
                .getResultList();
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

