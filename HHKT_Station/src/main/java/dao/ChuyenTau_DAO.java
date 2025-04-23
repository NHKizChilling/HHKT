package dao;

import entity.ChuyenTau;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import util.JPAUtil;

import java.util.List;

public class ChuyenTau_DAO {
    private final EntityManager em;

    public ChuyenTau_DAO() {
        this.em = JPAUtil.getEntityManager();
    }

    public List<ChuyenTau> getAll() {

        try {
            return em.createQuery("from ChuyenTau", ChuyenTau.class).getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public ChuyenTau getChuyenTauTheoID(String soHieuTau) {

        return em.find(ChuyenTau.class, soHieuTau);
    }



    public boolean create(ChuyenTau chuyenTau) {
        return executeTransaction(() -> em.persist(chuyenTau));
    }

    public boolean update(ChuyenTau chuyenTau) {
        return executeTransaction(() -> em.merge(chuyenTau));
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
