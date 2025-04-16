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

        return em.createQuery("from Ga", Ga.class).getResultList();
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
        String sql = "from Ga where maGa = :maGa";
        return em.createQuery(sql, Ga.class).setParameter("maGa", maGa).getSingleResult();
    }

    public Ga getGaTheoTenGa(String tenGa) {
        String sql = "from Ga where tenGa = :tenGa";
        return em.createQuery(sql, Ga.class).setParameter("tenGa", tenGa).getSingleResult();
    }

    public double KhoangCach(String maGa){
        String sql = "Select khoangCach from Ga where maGa = :maGa";
        return em.createQuery(sql, Double.class).setParameter("maGa", maGa).getSingleResult();
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

