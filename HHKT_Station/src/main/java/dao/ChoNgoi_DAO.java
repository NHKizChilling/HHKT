package dao;

import entity.ChiTietLichTrinh;
import entity.ChoNgoi;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.ArrayList;

public class ChoNgoi_DAO {
    private final EntityManager em;
    private final EntityTransaction transaction;

    public ChoNgoi_DAO(EntityManager em) {
        this.em = em;
        this.transaction = em.getTransaction();
    }

    public ArrayList<ChoNgoi> getAllChoNgoi() {
        String sql = "Select * from ChoNgoi";
        return (ArrayList<ChoNgoi>) em.createNativeQuery(sql, ChoNgoi.class).getResultList();
    }

    public ChoNgoi getChoNgoiTheoToa(String maToa, int sttCho) {
        String sql = "Select * from ChoNgoi where ma_toa = ? and stt_cho = ?";
        return (ChoNgoi) em.createNativeQuery(sql, ChoNgoi.class).setParameter(1, maToa).setParameter(2, sttCho).getSingleResult();
    }

    public ChoNgoi getChoNgoiTheoMa(String maCho) {
        String sql = "Select * from ChoNgoi where ma_cho = ?";
        return (ChoNgoi) em.createNativeQuery(sql, ChoNgoi.class).setParameter(1, maCho).getSingleResult();
    }

    public ArrayList<ChoNgoi> getDSChoNgoiTheoToa(String maToa) {
        String sql = "Select * from ChoNgoi where ma_toa = ?";
        return (ArrayList<ChoNgoi>) em.createNativeQuery(sql, ChoNgoi.class).setParameter(1, maToa).getResultList();
    }

    public ArrayList<ChoNgoi> getChoNgoiDaDat(ChiTietLichTrinh chiTietLichTrinh) {
        String sql = "Select * from ChoNgoi a join ChiTietLichTrinh b on a.ma_cho = b.ma_cho where trang_thai = 0";
        return (ArrayList<ChoNgoi>) em.createNativeQuery(sql, ChoNgoi.class).getResultList();
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

    public ArrayList<ChoNgoi> getDsChoNgoiTheoToa(String maToa) {
        String sql = "Select * from ChoNgoi where ma_toa = ?";
        return (ArrayList<ChoNgoi>) em.createNativeQuery(sql, ChoNgoi.class).setParameter(1, maToa).getResultList();
    }
}
