package dao;

import entity.LoaiTau;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import util.JPAUtil;

import java.util.List;

public class LoaiTau_DAO {
    private final EntityManager em;

    public LoaiTau_DAO() {
        this.em = JPAUtil.getEntityManager();
    }

    public List<LoaiTau> getAllLoaiTau() {

        return em.createQuery("from LoaiTau", LoaiTau.class).getResultList();
    }

    public boolean create(LoaiTau loaiTau) {
        return executeTransaction(() -> em.persist(loaiTau));
    }

    public boolean update(LoaiTau loaiTau) {
        return executeTransaction(() -> em.merge(loaiTau));
    }

    public boolean delete(String maLoaiTau) {
        return executeTransaction(() -> {
            LoaiTau loaiTau = getLoaiTauTheoMa(maLoaiTau);
            em.remove(loaiTau);
        });
    }

    public LoaiTau getLoaiTauTheoMa(String maLoaiTau) {
        String sql = "from LoaiTau where maLoaiTau = :maLoaiTau";
        return em.createQuery(sql, LoaiTau.class).setParameter("maLoaiTau", maLoaiTau).getSingleResult();
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
