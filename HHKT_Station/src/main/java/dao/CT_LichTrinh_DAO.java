package dao;

import entity.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import util.JPAUtil;

import java.util.List;

public class CT_LichTrinh_DAO {
    private final EntityManager em;

    public CT_LichTrinh_DAO() {
        this.em = JPAUtil.getEntityManager();
    }

    public List<ChiTietLichTrinh> getAllChiTietLichTrinh() {

        try {
            return em.createQuery("from ChiTietLichTrinh", ChiTietLichTrinh.class).getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public boolean create(ChiTietLichTrinh ctlt) {
        return executeTransaction(() -> em.persist(ctlt));
    }

    public boolean update(ChiTietLichTrinh ctlt) {
        return executeTransaction(() -> em.merge(ctlt));
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

    public boolean updateCTLT(ChiTietLichTrinh ctlt, boolean trangThai) {

        return executeTransaction(() -> {
            String sql = "Update ChiTietLichTrinh set trang_thai = ? where ma_cho = ? and ma_lich_trinh = ?";
            em.createNativeQuery(sql)
                    .setParameter(1, trangThai)
                    .setParameter(2, ctlt.getChoNgoi().getMaCho())
                    .setParameter(3, ctlt.getLichTrinh().getMaLichTrinh())
                    .executeUpdate();
        });
    }

    public static void main(String[] args) {
        CT_LichTrinh_DAO dao = new CT_LichTrinh_DAO();
        System.out.println(dao.updateCTLT(new ChiTietLichTrinh(new ChoNgoi("SE1T10CN1"), new LichTrinh("LTSE1200525HNSG")), false));
    }

    public boolean delete(String maLT, String maCN) {
        return executeTransaction(() -> {
            ChiTietLichTrinh ctlt = getCTLTTheoCN(maLT, maCN);
            em.remove(ctlt);
        });
    }

    public List<ChiTietLichTrinh> getCtltTheoTrangThai(boolean trangThai) {
        String sql = "from ChiTietLichTrinh where trangThai = :trangThai";

        try {
            return em.createQuery(sql, ChiTietLichTrinh.class)
                    .setParameter("trangThai", trangThai)
                    .getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public ChiTietLichTrinh getCTLTTheoCN(String maLichTrinh, String maChoNgoi) {
        String sql = "from ChiTietLichTrinh where lichTrinh.maLichTrinh = :malt and choNgoi.maCho = :maCho";

        try {
            return em.createQuery(sql, ChiTietLichTrinh.class)
                    .setParameter("malt", maLichTrinh)
                    .setParameter("maCho", maChoNgoi)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public boolean getTrangThaiCN(String maLichTrinh, String maCho) {
        String sql = "Select trangThai from ChiTietLichTrinh where lichTrinh.maLichTrinh = :malt and choNgoi.maCho = :maCho";
        boolean trangThai = false;
        try {
            trangThai = em.createQuery(sql, boolean.class)
                    .setParameter("malt", maLichTrinh)
                    .setParameter("maCho", maCho)
                    .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return trangThai;
    }

    public void addChiTietLichTrinh(String maLichTrinh) {
        EntityTransaction transaction = em.getTransaction();
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

    public List<ChiTietLichTrinh> getCtltTheoMaLichTrinh(String maLichTrinh) {
        String sql = "from ChiTietLichTrinh where lichTrinh.maLichTrinh = :malt";

        try {
            return em.createQuery(sql, ChiTietLichTrinh.class)
                    .setParameter("malt", maLichTrinh)
                    .getResultList();
        } catch (Exception e) {
            return null;
        }
    }
}
