package dao;

import entity.TaiKhoan;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import util.JPAUtil;

import java.util.List;

public class TaiKhoan_DAO {
    private final EntityManager em;

    public TaiKhoan_DAO() {
        this.em = JPAUtil.getEntityManager();
    }

    public List<TaiKhoan> getAllTaiKhoan() {

        return em.createQuery("from TaiKhoan ", TaiKhoan.class).getResultList();
    }

    public boolean create(TaiKhoan tk) {
        return executeTransaction(() -> em.persist(tk));
    }

    public boolean delete(String maNhanVien) {
        return executeTransaction(() -> em.remove(getTaiKhoanTheoMaNV(maNhanVien)));
    }

    public boolean update(TaiKhoan tk) {
        return executeTransaction(() -> em.merge(tk));
    }

    public boolean doiMatKhau(String maNv, String matKhauMoi) {
        return executeTransaction(() -> {
            TaiKhoan tk = em.createQuery("FROM TaiKhoan tk WHERE tk.nhanVien.maNV = :maNV", TaiKhoan.class)
                    .setParameter("maNV", maNv)
                    .getSingleResult();
            if (tk != null) {
                tk.setMatKhau(matKhauMoi);
                em.merge(tk);
            }
        });
    }

    public TaiKhoan getTaiKhoanTheoMaNV(String maNhanVien) {
        return em.createQuery("FROM TaiKhoan tk WHERE tk.nhanVien.maNV = :maNV", TaiKhoan.class)
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
