package dao;

import entity.NhanVien;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.ArrayList;

public class NhanVien_DAO {
    private final EntityManager em;
    private final EntityTransaction transaction;

    public NhanVien_DAO(EntityManager em) {
        this.em = em;
        this.transaction = em.getTransaction();
    }

    public ArrayList<NhanVien> getAll() {
        String sql = "Select * from NhanVien";
        return (ArrayList<NhanVien>) em.createNativeQuery(sql, NhanVien.class).getResultList();
    }

    public boolean create(NhanVien nv) {
        return executeTransaction(() -> em.persist(nv));
    }

    public boolean updateTinhTrangCV(String maNV, String tinhTrangCV) {
        return executeTransaction(() -> {
            NhanVien nv = getNhanVien(maNV);
            nv.setTinhTrangCv(tinhTrangCV);
            em.merge(nv);
        });
    }

    public ArrayList<NhanVien> getDSQuanLy() {
        String sql = "Select * from NhanVien where chuc_vu = 'Quản lý'";
        return (ArrayList<NhanVien>) em.createNativeQuery(sql, NhanVien.class).getResultList();
    }

    public ArrayList<NhanVien> getDSNhanVien() {
        String sql = "Select * from NhanVien where chuc_vu = 'Nhân viên'";
        return (ArrayList<NhanVien>) em.createNativeQuery(sql, NhanVien.class).getResultList();
    }

    public boolean updateInfo(NhanVien nv) {
        return executeTransaction(() -> em.merge(nv));
    }

    public NhanVien getNhanVien(String maNV) {
        return em.find(NhanVien.class, maNV);
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

    public NhanVien getNhanVienTheoTen(String tenNV) {
        String sql = "Select * from NhanVien where ten_nv = ?";
        return (NhanVien) em.createNativeQuery(sql, NhanVien.class).setParameter(1, tenNV).getSingleResult();
    }

    public NhanVien getNhanVienTheoSDT(String sdt) {
        String sql = "Select * from NhanVien where sdt = ?";
        return (NhanVien) em.createNativeQuery(sql, NhanVien.class).setParameter(1, sdt).getSingleResult();
    }
}
