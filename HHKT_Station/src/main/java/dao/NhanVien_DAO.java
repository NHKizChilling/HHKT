package dao;

import entity.NhanVien;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import util.JPAUtil;

import java.util.List;

public class NhanVien_DAO {
    private final EntityManager em;

    public NhanVien_DAO() {
        this.em = JPAUtil.getEntityManager();
    }

    public List<NhanVien> getAll() {

        try {
            return em.createQuery("from NhanVien", NhanVien.class).getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public boolean create(NhanVien nv) {
        return executeTransaction(() -> em.persist(nv));
    }

    public boolean delete(String maNV) {
        return executeTransaction(() -> em.remove(getNhanVien(maNV)));
    }

    public boolean updateTinhTrangCV(String maNV, String tinhTrangCV) {
        return executeTransaction(() -> {
            NhanVien nv = getNhanVien(maNV);
            nv.setTinhTrangCv(tinhTrangCV);
            em.merge(nv);
        });
    }

    public List<NhanVien> getDSQuanLy() {
        String sql = "from NhanVien where chucVu = 'Quản lý'";

        try {
            return em.createQuery(sql, NhanVien.class).getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public List<NhanVien> getDSNhanVien() {
        String sql = "from NhanVien where chucVu = 'Nhân viên'";

        try {
            return em.createQuery(sql, NhanVien.class).getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public boolean updateInfo(NhanVien nv) {
        return executeTransaction(() -> em.merge(nv));
    }

    public NhanVien getNhanVien(String maNV) {
        return em.find(NhanVien.class, maNV);
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

    public NhanVien getNhanVienTheoTen(String tenNV) {
        String sql = "from NhanVien where tenNV = :ten";

        try {
            return em.createQuery(sql, NhanVien.class)
                    .setParameter("ten", tenNV)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public NhanVien getNhanVienTheoSDT(String sdt) {
        String sql = "from NhanVien where sdt = :sdt";

        try {
            return em.createQuery(sql, NhanVien.class)
                    .setParameter("sdt", sdt)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}
