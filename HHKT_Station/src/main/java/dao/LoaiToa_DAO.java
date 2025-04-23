package dao;

import entity.LoaiToa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import util.JPAUtil;

import java.util.List;

public class LoaiToa_DAO {
    private final EntityManager em;

    public LoaiToa_DAO() {
        this.em = JPAUtil.getEntityManager();
    }

    public List<LoaiToa> getAllLoaiToa() {

        try {
            return em.createQuery("from LoaiToa ", LoaiToa.class).getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public boolean create(LoaiToa loaiToa) {
        return executeTransaction(() -> em.persist(loaiToa));
    }

    public boolean update(LoaiToa loaiToa) {
        return executeTransaction(() -> em.merge(loaiToa));
    }

    public boolean delete(String maLoaiToa) {
        return executeTransaction(() -> {
            LoaiToa loaiToa = getLoaiToaTheoMa(maLoaiToa);
            em.remove(loaiToa);
        });
    }

    public LoaiToa getLoaiToaTheoMa(String maLoaiToa) {

        return em.find(LoaiToa.class, maLoaiToa);
    }

    public boolean xoaLoaiToaTheoMa(String maLoaiToa) {
        return executeTransaction(() -> {
            LoaiToa loaiToa = getLoaiToaTheoMa(maLoaiToa);
            em.remove(loaiToa);
        });
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
