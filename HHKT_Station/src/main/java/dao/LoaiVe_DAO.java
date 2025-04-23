package dao;

import entity.LoaiVe;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import util.JPAUtil;

import java.util.List;

public class LoaiVe_DAO {
    private final EntityManager em;

    public LoaiVe_DAO() {
        this.em = JPAUtil.getEntityManager();
    }

    public List<LoaiVe> getAllLoaiVe() {

        try {
            return em.createQuery("from LoaiVe", LoaiVe.class).getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public boolean create(LoaiVe loaiVe) {
        return executeTransaction(() -> em.persist(loaiVe));
    }


    public boolean update(LoaiVe loaiVe) {
        return executeTransaction(() -> em.merge(loaiVe));
    }


    public boolean delete(String maLoaiVe) {
        return executeTransaction(() -> {
            LoaiVe loaiVe = getLoaiVeTheoMa(maLoaiVe);
            em.remove(loaiVe);
        });
    }

    public LoaiVe getLoaiVeTheoTen(String tenLoai) {
        String sql = "from LoaiVe where tenLoaiVe = :tenLoaiVe";

        try {
            return em.createQuery(sql, LoaiVe.class).setParameter("tenLoaiVe", tenLoai).getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public LoaiVe getLoaiVeTheoMa(String maLoaiVe) {

        return em.find(LoaiVe.class, maLoaiVe);
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
