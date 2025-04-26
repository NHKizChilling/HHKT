package dao;

import entity.Ga;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import util.JPAUtil;

import java.util.List;

public class Ga_DAO {
    private final EntityManager em;

    public Ga_DAO() {
        this.em = JPAUtil.getEntityManager();
    }

    public List<Ga> getAllGa() {

        try {
            return em.createQuery("from Ga", Ga.class).getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public boolean create(Ga ga) {
        return executeTransaction(() -> em.persist(ga));
    }

    public boolean update(Ga ga) {
        return executeTransaction(() -> em.merge(ga));
    }

    public boolean delete(String maGa) {
        return executeTransaction(() -> {
            Ga ga = getGaTheoMaGa(maGa);
            em.remove(ga);
        });
    }

    public Ga getGaTheoMaGa(String maGa) {

        return em.find(Ga.class, maGa);
    }

    public Ga getGaTheoTenGa(String tenGa) {
        String sql = "from Ga where tenGa = :tenGa";

        try {
            return em.createQuery(sql, Ga.class).setParameter("tenGa", tenGa).getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public double KhoangCach(String maGa) {
        String jpql = "SELECT g FROM Ga g WHERE g.maGa = :maGa";
        try {
            Ga ga = em.createQuery(jpql, Ga.class)
                    .setParameter("maGa", maGa)
                    .getSingleResult();
            return ga.getKhoangCach();
        } catch (Exception e) {
            return 0;
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

