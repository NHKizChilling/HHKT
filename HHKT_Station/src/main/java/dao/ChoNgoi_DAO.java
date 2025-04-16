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

        return em.createQuery("from ChoNgoi ", ChoNgoi.class).getResultList();
    }

    public ChoNgoi getChoNgoiTheoToa(String maToa, int sttCho) {
        String sql = "from ChoNgoi where toa.maToa = :maToa and sttCho = :stt";
        return em.createQuery(sql, ChoNgoi.class)
                .setParameter("maToa", maToa)
                .setParameter("stt", sttCho)
                .getSingleResult();
    }

    public ChoNgoi getChoNgoiTheoMa(String maCho) {
        String sql = "from ChoNgoi where maCho = :maCho";
        return em.createQuery(sql, ChoNgoi.class)
                .setParameter("maCho", maCho)
                .getSingleResult();
    }

    public List<ChoNgoi> getDSChoNgoiTheoToa(String maToa) {
        String sql = "from ChoNgoi where toa.maToa = :maToa";
        return em.createQuery(sql, ChoNgoi.class)
                .setParameter("maToa", maToa)
                .getResultList();
    }

    public List<ChoNgoi> getChoNgoiDaDat(LichTrinh lichTrinh) {
        String sql = "Select ctlt.choNgoi from ChiTietLichTrinh ctlt where ctlt.lichTrinh = :lichTrinh and trangThai = false";
        return em.createQuery(sql, ChoNgoi.class)
                .setParameter("lichTrinh", lichTrinh)
                .getResultList();
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
            e.printStackTrace();
            return false;
        }
    }
}
