package dao;

import entity.Toa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import util.JPAUtil;

import java.util.List;

public class Toa_DAO {
    private final EntityManager em;

    public Toa_DAO() {
        this.em = JPAUtil.getEntityManager();
    }

    public List<Toa> getAllToa() {

        return em.createQuery("from Toa", Toa.class).getResultList();
    }

    public List<Toa> getAllToaTheoChuyenTau(String soHieuTau) {
        String sql = "from Toa where soHieuTau.id = :soHieuTau order by sttToa";
        return em.createQuery(sql, Toa.class).setParameter("soHieuTau", soHieuTau).getResultList();
    }

    public Toa getToaTheoID(String maToa) {
        String sql = "from Toa where maToa = :maToa";
        return em.createQuery(sql, Toa.class).setParameter("maToa", maToa).getSingleResult();
    }

    public boolean create(Toa toa) {
        return executeTransaction(() -> em.persist(toa));
    }

    public boolean update(Toa toa) {
        return executeTransaction(() -> em.merge(toa));
    }

    public boolean delete(String maToa) {
        return executeTransaction(() -> {
            Toa toa = getToaTheoID(maToa);
            em.remove(toa);
        });
    }

    private boolean executeTransaction(Runnable action) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            action.run();
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }
}
