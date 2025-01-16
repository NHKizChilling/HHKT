package dao;

import entity.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.ArrayList;

public class CT_LichTrinh_DAO {
    private final EntityManager em;
    private final EntityTransaction transaction;

    public CT_LichTrinh_DAO(EntityManager em) {
        this.em = em;
        this.transaction = em.getTransaction();
    }

    public ArrayList<ChiTietLichTrinh> getAllChiTietLichTrinh() {
        String sql = "Select * from ChiTietLichTrinh";
        return (ArrayList<ChiTietLichTrinh>) em.createNativeQuery(sql, ChiTietLichTrinh.class).getResultList();
    }

    public boolean create(ChiTietLichTrinh ctlt) {
        return executeTransaction(() -> em.persist(ctlt));
    }

    public boolean update(ChiTietLichTrinh ctlt) {
        return executeTransaction(() -> em.merge(ctlt));
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

    public boolean updateCTLT(ChiTietLichTrinh ctlt, boolean trangThai) {
        String sql = "Update ChiTietLichTrinh set trang_thai = ? where ma_cho = ? and ma_lich_trinh = ?";
        try {
            em.createNativeQuery(sql, ChiTietLichTrinh.class)
                    .setParameter(1, trangThai).setParameter(2, ctlt.getChoNgoi().getMaCho())
                    .setParameter(3, ctlt.getLichTrinh().getMaLichTrinh()).executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean delete(String maLT, String maCN) {
        return executeTransaction(() -> {
            ChiTietLichTrinh ctlt = getCTLTTheoCN(maLT, maCN);
            em.remove(ctlt);
        });
    }

    public ArrayList<ChiTietLichTrinh> getCtltTheoTrangThai(boolean trangThai) {
        String sql = "Select * from ChiTietLichTrinh where trang_thai = ?";
        return (ArrayList<ChiTietLichTrinh>) em.createNativeQuery(sql, ChiTietLichTrinh.class).setParameter(1, trangThai).getResultList();
    }

    public ChiTietLichTrinh getCTLTTheoCN(String maLichTrinh, String maChoNgoi) {
        String sql = "Select * from ChiTietLichTrinh where ma_lich_trinh = ? and ma_cho = ?";
        return (ChiTietLichTrinh) em.createNativeQuery(sql, ChiTietLichTrinh.class).setParameter(1, maLichTrinh).setParameter(2, maChoNgoi).getSingleResult();
    }

    public boolean getTrangThaiCN(String maLichTrinh, String maCho) {
        String sql = "Select trang_thai from ChiTietLichTrinh where ma_lich_trinh = ? and ma_cho = ?";
        boolean trangThai = false;
        try {
            trangThai = (boolean) em.createNativeQuery(sql, ChiTietLichTrinh.class).setParameter(1, maLichTrinh).setParameter(2, maCho).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return trangThai;
    }

    public void addChiTietLichTrinh(String maLichTrinh) {
        String sql = """
                INSERT INTO ChiTietLichTrinh (ma_cho, ma_lich_trinh, trang_thai, gia_cho)
                SELECT\s
                    cn.ma_cho,\s
                    lt.ma_lich_trinh,\s
                    lt.trang_thai,
                    CASE\s
                        WHEN t.ma_loai_toa = 'NC' THEN\s
                            CASE\s
                                WHEN gaDen.khoang_cach = 0 THEN (
                \t\t\t\t\tCASE\s
                \t\t\t\t\t\tWHEN (SELECT khoang_cach FROM Ga WHERE ma_ga = lt.ma_ga_di) <= 100 THEN 500 * (SELECT khoang_cach FROM Ga WHERE ma_ga = lt.ma_ga_di) * 1.1
                \t\t\t\t\t\tWHEN (SELECT khoang_cach FROM Ga WHERE ma_ga = lt.ma_ga_di) <= 250 THEN 500 * (SELECT khoang_cach FROM Ga WHERE ma_ga = lt.ma_ga_di) * 1.25
                \t\t\t\t\t\tWHEN (SELECT khoang_cach FROM Ga WHERE ma_ga = lt.ma_ga_di) <= 1000 THEN 500 * (SELECT khoang_cach FROM Ga WHERE ma_ga = lt.ma_ga_di) * 1.5
                \t\t\t\t\t\tELSE 500 * (SELECT khoang_cach FROM Ga WHERE ma_ga = lt.ma_ga_di) * 2
                \t\t\t\t\tEND)
                                WHEN gaDen.khoang_cach <= 100 THEN 500 * gaDen.khoang_cach * 1.1
                                WHEN gaDen.khoang_cach <= 250 THEN 500 * gaDen.khoang_cach * 1.25
                                WHEN gaDen.khoang_cach <= 1000 THEN 500 * gaDen.khoang_cach * 1.5
                                ELSE 500 * gaDen.khoang_cach * 2
                            END
                        WHEN t.ma_loai_toa = 'NM' THEN\s
                            CASE\s
                                WHEN gaDen.khoang_cach = 0 THEN (
                \t\t\t\t\tCASE\s
                \t\t\t\t\t\tWHEN (SELECT khoang_cach FROM Ga WHERE ma_ga = lt.ma_ga_di) <= 100 THEN 500 * (SELECT khoang_cach FROM Ga WHERE ma_ga = lt.ma_ga_di) * 1.1 * 1.1
                \t\t\t\t\t\tWHEN (SELECT khoang_cach FROM Ga WHERE ma_ga = lt.ma_ga_di) <= 250 THEN 500 * (SELECT khoang_cach FROM Ga WHERE ma_ga = lt.ma_ga_di) * 1.25 * 1.1
                \t\t\t\t\t\tWHEN (SELECT khoang_cach FROM Ga WHERE ma_ga = lt.ma_ga_di) <= 1000 THEN 500 * (SELECT khoang_cach FROM Ga WHERE ma_ga = lt.ma_ga_di) * 1.5 * 1.1
                \t\t\t\t\t\tELSE 500 * (SELECT khoang_cach FROM Ga WHERE ma_ga = lt.ma_ga_di) * 2 * 1.1
                \t\t\t\t\tEND)
                                WHEN gaDen.khoang_cach <= 100 THEN 500 * gaDen.khoang_cach * 1.1 * 1.1
                                WHEN gaDen.khoang_cach <= 250 THEN 500 * gaDen.khoang_cach * 1.25 * 1.1
                                WHEN gaDen.khoang_cach <= 1000 THEN 500 * gaDen.khoang_cach * 1.5 * 1.1
                                ELSE 500 * gaDen.khoang_cach * 2 * 1.1
                            END
                        WHEN t.ma_loai_toa = 'GNK6' THEN\s
                            CASE\s
                                WHEN gaDen.khoang_cach = 0 THEN (
                \t\t\t\t\tCASE\s
                \t\t\t\t\t\tWHEN (SELECT khoang_cach FROM Ga WHERE ma_ga = lt.ma_ga_di) <= 100 THEN 500 * (SELECT khoang_cach FROM Ga WHERE ma_ga = lt.ma_ga_di) * 1.1 * 1.25
                \t\t\t\t\t\tWHEN (SELECT khoang_cach FROM Ga WHERE ma_ga = lt.ma_ga_di) <= 250 THEN 500 * (SELECT khoang_cach FROM Ga WHERE ma_ga = lt.ma_ga_di) * 1.25 * 1.25
                \t\t\t\t\t\tWHEN (SELECT khoang_cach FROM Ga WHERE ma_ga = lt.ma_ga_di) <= 1000 THEN 500 * (SELECT khoang_cach FROM Ga WHERE ma_ga = lt.ma_ga_di) * 1.5 * 1.25
                \t\t\t\t\t\tELSE 500 * (SELECT khoang_cach FROM Ga WHERE ma_ga = lt.ma_ga_di) * 2 * 1.25
                \t\t\t\t\tEND)
                                WHEN gaDen.khoang_cach <= 100 THEN 500 * gaDen.khoang_cach * 1.1 * 1.25
                                WHEN gaDen.khoang_cach <= 250 THEN 500 * gaDen.khoang_cach * 1.25 * 1.25
                                WHEN gaDen.khoang_cach <= 1000 THEN 500 * gaDen.khoang_cach * 1.5 * 1.25
                                ELSE 500 * gaDen.khoang_cach * 2 * 1.25
                            END
                        WHEN t.ma_loai_toa = 'GNK4' THEN\s
                            CASE\s
                                WHEN gaDen.khoang_cach = 0 THEN (
                \t\t\t\t\tCASE\s
                \t\t\t\t\t\tWHEN (SELECT khoang_cach FROM Ga WHERE ma_ga = lt.ma_ga_di) <= 100 THEN 500 * (SELECT khoang_cach FROM Ga WHERE ma_ga = lt.ma_ga_di) * 1.1 * 1.5
                \t\t\t\t\t\tWHEN (SELECT khoang_cach FROM Ga WHERE ma_ga = lt.ma_ga_di) <= 250 THEN 500 * (SELECT khoang_cach FROM Ga WHERE ma_ga = lt.ma_ga_di) * 1.25 * 1.5
                \t\t\t\t\t\tWHEN (SELECT khoang_cach FROM Ga WHERE ma_ga = lt.ma_ga_di) <= 1000 THEN 500 * (SELECT khoang_cach FROM Ga WHERE ma_ga = lt.ma_ga_di) * 1.5 * 1.5
                \t\t\t\t\t\tELSE 500 * (SELECT khoang_cach FROM Ga WHERE ma_ga = lt.ma_ga_di) * 2 * 1.5
                \t\t\t\t\tEND)
                                WHEN gaDen.khoang_cach <= 100 THEN 500 * gaDen.khoang_cach * 1.1 * 1.5
                                WHEN gaDen.khoang_cach <= 250 THEN 500 * gaDen.khoang_cach * 1.25 * 1.5
                                WHEN gaDen.khoang_cach <= 1000 THEN 500 * gaDen.khoang_cach * 1.5 * 1.5
                                ELSE 500 * gaDen.khoang_cach * 2 * 1.5
                            END
                        WHEN t.ma_loai_toa = 'TVIP' THEN\s
                            CASE\s
                                WHEN gaDen.khoang_cach = 0 THEN (
                \t\t\t\t\tCASE\s
                \t\t\t\t\t\tWHEN (SELECT khoang_cach FROM Ga WHERE ma_ga = lt.ma_ga_di) <= 100 THEN 500 * (SELECT khoang_cach FROM Ga WHERE ma_ga = lt.ma_ga_di) * 1.1 * 2
                \t\t\t\t\t\tWHEN (SELECT khoang_cach FROM Ga WHERE ma_ga = lt.ma_ga_di) <= 250 THEN 500 * (SELECT khoang_cach FROM Ga WHERE ma_ga = lt.ma_ga_di) * 1.25 * 2
                \t\t\t\t\t\tWHEN (SELECT khoang_cach FROM Ga WHERE ma_ga = lt.ma_ga_di) <= 1000 THEN 500 * (SELECT khoang_cach FROM Ga WHERE ma_ga = lt.ma_ga_di) * 1.5 * 2
                \t\t\t\t\t\tELSE 500 * (SELECT khoang_cach FROM Ga WHERE ma_ga = lt.ma_ga_di) * 2 * 2
                \t\t\t\t\tEND)
                                WHEN gaDen.khoang_cach <= 100 THEN 500 * gaDen.khoang_cach * 1.1 * 2
                                WHEN gaDen.khoang_cach <= 250 THEN 500 * gaDen.khoang_cach * 1.25 * 2
                                WHEN gaDen.khoang_cach <= 1000 THEN 500 * gaDen.khoang_cach * 1.5 * 2
                                ELSE 500 * gaDen.khoang_cach * 2 * 2
                            END
                        ELSE 0
                    END AS GiaCho
                FROM ChoNgoi cn
                JOIN LichTrinh lt ON lt.so_hieu_tau = LEFT(cn.ma_toa, LEN(cn.ma_toa) - CHARINDEX('T', REVERSE(cn.ma_toa))) \s
                JOIN Ga gaDen ON lt.ma_ga_den = gaDen.ma_ga
                JOIN Toa t ON cn.ma_toa = t.ma_toa
                WHERE lt.ma_lich_trinh = ?;""";
        transaction.begin();
        try {
            em.createNativeQuery(sql, ChiTietLichTrinh.class).setParameter(1, maLichTrinh).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
    }

    public ArrayList<ChiTietLichTrinh> getCtltTheoMaLichTrinh(String maLichTrinh) {
        String sql = "Select * from ChiTietLichTrinh where ma_lich_trinh = ?";
        return (ArrayList<ChiTietLichTrinh>) em.createNativeQuery(sql, ChiTietLichTrinh.class).setParameter(1, maLichTrinh).getResultList();
    }
}
