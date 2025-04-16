package dao;

import entity.KhuyenMai;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import util.JPAUtil;

import java.time.LocalDate;
import java.util.List;

public class KhuyenMai_DAO {
    private final EntityManager em;

    public KhuyenMai_DAO() {
        this.em = JPAUtil.getEntityManager();
    }

    public List<KhuyenMai> getAllKM() {
        return em.createQuery("from KhuyenMai", KhuyenMai.class).getResultList();
    }

    public List<KhuyenMai> getKMHienCo() {
        return em.createQuery("from KhuyenMai where trangThai = true", KhuyenMai.class).getResultList();
    }

    public KhuyenMai getKMTheoMa(String maKM) {

        return em.createQuery("from KhuyenMai km where km.maKM = :maKM", KhuyenMai.class)
                .setParameter("maKM", maKM)
                .getSingleResult();
    }

    public KhuyenMai getKMGiamCaoNhat() {

        return em.createQuery("from KhuyenMai where mucKM = (Select max(mucKM) from KhuyenMai)", KhuyenMai.class)
                .getSingleResult();
    }

    public List<KhuyenMai> getKMTheoNgay(LocalDate ngay) {
        return em.createQuery("from KhuyenMai where ngayApDung <= :ngay and ngayHetHan >= :ngay", KhuyenMai.class)
                .setParameter("ngay", ngay)
                .getResultList();
    }

    public boolean themKhuyenMai(KhuyenMai km) {
        return executeTransaction(() -> em.persist(km));
    }

    public boolean suaKhuyenMai(KhuyenMai km) {
        return executeTransaction(() -> em.merge(km));
    }

    public boolean xoaKhuyenMai(String maKM) {
        return executeTransaction(() -> em.remove(getKMTheoMa(maKM)));
    }

    public boolean kichHoatKhuyenMai() {
        return executeTransaction(() -> {
            String sql = "update KhuyenMai set trangThai = true where ngayApDung <= :ngayHienTai and ngayHetHan >= :ngayHienTai";
            em.createQuery(sql)
                    .setParameter("ngayHienTai", LocalDate.now())
                    .executeUpdate();
        });
    }

    public boolean khoaKhuyenMai() {
        return executeTransaction(() -> {
            String sql = "from KhuyenMai where ngayHetHan < :ngayHienTai and trangThai = true";
            em.createQuery(sql, KhuyenMai.class)
                    .setParameter("ngayHienTai", LocalDate.now())
                    .getResultList();
        });
    }

    public boolean capNhatTrangThaiKM(String maKM, boolean trangThai) {
        return executeTransaction(() -> {
            String sql = "UPDATE KhuyenMai SET trangThai = :trangThai WHERE maKM = :maKM";
            em.createQuery(sql)
                    .setParameter("trangThai", trangThai)
                    .setParameter("maKM", maKM)
                    .executeUpdate();
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