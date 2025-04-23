package dao;

import entity.ChoNgoi;
import entity.LichTrinh;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import util.JPAUtil;

import java.util.List;

public class ChoNgoi_DAO {
    private final EntityManager em;

    public ChoNgoi_DAO() {
        this.em = JPAUtil.getEntityManager();
    }

    public List<ChoNgoi> getAllChoNgoi() {
        try {

            return em.createQuery("from ChoNgoi ", ChoNgoi.class).getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public ChoNgoi getChoNgoiTheoToa(String maToa, int sttCho) {
        String sql = "from ChoNgoi where toa.maToa = :maToa and sttCho = :stt";
        try {
            return em.createQuery(sql, ChoNgoi.class)
                    .setParameter("maToa", maToa)
                    .setParameter("stt", sttCho)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public ChoNgoi getChoNgoiTheoMa(String maCho) {

        return em.find(ChoNgoi.class, maCho);
    }

    public List<ChoNgoi> getDSChoNgoiTheoToa(String maToa) {
        String sql = "from ChoNgoi where toa.maToa = :maToa";

        try {
            return em.createQuery(sql, ChoNgoi.class)
                    .setParameter("maToa", maToa)
                    .getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public List<ChoNgoi> getChoNgoiDaDat(LichTrinh lichTrinh) {
        String sql = "Select ctlt.choNgoi from ChiTietLichTrinh ctlt where ctlt.lichTrinh = :lichTrinh and trangThai = false";

        try {
            return em.createQuery(sql, ChoNgoi.class)
                    .setParameter("lichTrinh", lichTrinh)
                    .getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public boolean create(ChoNgoi choNgoi) {
        return executeTransaction(() -> em.persist(choNgoi));
    }

    public boolean update(ChoNgoi choNgoi) {
        return executeTransaction(() -> em.merge(choNgoi));
    }

    public boolean delete(String maChoNgoi) {
        return executeTransaction(() -> {
            ChoNgoi choNgoi = getChoNgoiTheoMa(maChoNgoi);
            em.remove(choNgoi);
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
