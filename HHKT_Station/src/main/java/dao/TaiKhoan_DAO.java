package dao;

import entity.NhanVien;
import entity.TaiKhoan;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.ArrayList;

public class TaiKhoan_DAO {
    private final EntityManager em;

    public TaiKhoan_DAO(EntityManager em) {
        this.em = em;
    }

    public ArrayList<TaiKhoan> getAllTaiKhoan() {
        String sql = "Select * from TaiKhoan";
        return (ArrayList<TaiKhoan>) em.createNativeQuery(sql, TaiKhoan.class).getResultList();
    }

    public boolean create(TaiKhoan tk) {
        return executeTransaction(() -> em.persist(tk));
    }

//    public boolean delete(String maNhanVien, String trangThaiTK) {
//        return executeTransaction(() -> {
//            TaiKhoan tk = getTaiKhoanTheoMaNV(maNhanVien);
//            tk.setTrangThaiTK(trangThaiTK);
//            em.merge(tk);
//        });
//    }

    public boolean delete(String maNhanVien) {
        return executeTransaction(() -> em.remove(getTaiKhoanTheoMaNV(maNhanVien)));
    }

    public boolean update(TaiKhoan tk) {
        return executeTransaction(() -> em.merge(tk));
    }

    public boolean doiMatKhau(String maNv, String matKhauMoi) {
        return executeTransaction(() -> {
            TaiKhoan tk = em.createQuery("SELECT tk FROM TaiKhoan tk WHERE tk.nhanVien.maNV = :maNV", TaiKhoan.class)
                    .setParameter("maNV", maNv)
                    .getSingleResult();
            tk.setMatKhau(matKhauMoi);
            em.merge(tk);
        });
    }

    public TaiKhoan getTaiKhoanTheoMaNV(String maNhanVien) {
        return em.createQuery("SELECT tk FROM TaiKhoan tk WHERE tk.nhanVien.maNV = :maNV", TaiKhoan.class)
                .setParameter("maNV", maNhanVien)
                .getSingleResult();
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
