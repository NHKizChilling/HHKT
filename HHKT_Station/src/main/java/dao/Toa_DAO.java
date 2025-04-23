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

        try {
            return em.createQuery("from Toa", Toa.class).getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Toa> getAllToaTheoChuyenTau(String soHieuTau) {
        String sql = "from Toa where soHieuTau.id = :soHieuTau order by sttToa";

        try {
            return em.createQuery(sql, Toa.class)
                    .setParameter("soHieuTau", soHieuTau)
                    .getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public Toa getToaTheoID(String maToa) {

        return em.find(Toa.class, maToa);
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
            return false;
        }
    }
}
