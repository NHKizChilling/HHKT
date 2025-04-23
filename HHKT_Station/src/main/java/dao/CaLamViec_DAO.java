package dao;

import entity.CaLamViec;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import util.JPAUtil;

import java.time.LocalDateTime;
import java.util.List;

public class CaLamViec_DAO {
    private final EntityManager em;

    public CaLamViec_DAO() {
        this.em = JPAUtil.getEntityManager();
    }

    public List<CaLamViec> getAllCaLamViec() {
        String sql = "from CaLamViec";

        try {
            return em.createQuery(sql, CaLamViec.class).getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public CaLamViec getCaLamViec(String maNV, LocalDateTime gioMoCa) {
        String sql = "from CaLamViec where nhanVien.maNV = :manv and gioMoCa = :giomoca";
        try {
            return em.createQuery(sql, CaLamViec.class)
                    .setParameter("manv", maNV)
                    .setParameter("giomoca", gioMoCa)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public CaLamViec getCaLamViecMoiNhatCuaNhanVien(String maNV) {
        String sql = "from CaLamViec where nhanVien.maNV = :manv ORDER BY gioMoCa DESC";
        try {
            return em.createQuery(sql, CaLamViec.class)
                    .setParameter("manv", maNV)
                    .setMaxResults(1)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public boolean create(CaLamViec caLamViec) {
        return executeTransaction(() -> em.persist(caLamViec));
    }

    public boolean update(CaLamViec caLamViec) {
        return executeTransaction(() -> em.merge(caLamViec));
    }

    public boolean delete(String maNV, LocalDateTime gioMoCa) {
        return executeTransaction(() -> {
            CaLamViec caLamViec = getCaLamViec(maNV, gioMoCa);
            em.remove(caLamViec);
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