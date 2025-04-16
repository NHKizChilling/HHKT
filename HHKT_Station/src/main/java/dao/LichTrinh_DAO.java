package dao;

import entity.LichTrinh;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import util.JPAUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class LichTrinh_DAO {
    private final EntityManager em;

    public LichTrinh_DAO() {
        this.em = JPAUtil.getEntityManager();
    }

    public List<LichTrinh> getAll() {

        return em.createQuery("from LichTrinh", LichTrinh.class).getResultList();
    }

    public LichTrinh getLichTrinhTheoID(String maLichTrinh) {
        String sql = " from LichTrinh where maLichTrinh = :maLichTrinh";
        return em.createQuery(sql, LichTrinh.class).setParameter("maLichTrinh", maLichTrinh).getSingleResult();
    }

    public List<LichTrinh> getDSLichTrinhTheoTrangThai(boolean trangThai) {
        String sql = "from LichTrinh where trangThai = :trangThai";
        return em.createQuery(sql, LichTrinh.class).setParameter("trangThai", trangThai).getResultList();
    }

    public List<LichTrinh> traCuuDSLichTrinh(String MaGaDi, String MaGaDen) {
        String sql = "from LichTrinh where gaDi.maGa = :maGaDi and gaDen.maGa = :maGaDen";
        return em.createQuery(sql, LichTrinh.class).setParameter("maGaDi", MaGaDi).setParameter("maGaDen", MaGaDen).getResultList();
    }

    public List<LichTrinh> traCuuDSLichTrinh(String MaGaDi, String MaGaDen, LocalDate ngayDi) {
        String sql = "from LichTrinh where gaDi.maGa = :maGaDi and gaDen.maGa = :maGaDen and year(thoiGianKhoiHanh) = :nam and month(thoiGianKhoiHanh) = :thang and day(thoiGianKhoiHanh) = :ngay";
        return em.createQuery(sql, LichTrinh.class).setParameter("maGaDi", MaGaDi).setParameter("maGaDen", MaGaDen).setParameter("nam", ngayDi.getYear()).setParameter("thang", ngayDi.getMonth().getValue()).setParameter("ngay", ngayDi.getDayOfMonth()).getResultList();
    }

    public List<LichTrinh> traCuuDSLichTrinhSauNgayHienTai() {
        String sql = "from LichTrinh where thoiGianKhoiHanh > :thoiGianHienTai";
        return em.createQuery(sql, LichTrinh.class).setParameter("thoiGianHienTai", LocalDateTime.now()).getResultList();
    }

    public List<LichTrinh> traCuuDSLichTrinhTheoNgay(LocalDate ngayDi) {
        String sql = "from LichTrinh where year(thoiGianKhoiHanh) = :nam and month(thoiGianKhoiHanh) = :thang and day(thoiGianKhoiHanh) = :ngay";
        return em.createQuery(sql, LichTrinh.class).setParameter("nam", ngayDi.getYear()).setParameter("thang", ngayDi.getMonth().getValue()).setParameter("ngay", ngayDi.getDayOfMonth()).getResultList();
    }

    public Long getSoLuongChoConTrong(String maLichTrinh) {
        String sql = "select count(*) from ChiTietLichTrinh where lichTrinh.maLichTrinh = :maLichTrinh and trangThai = true";
        return em.createQuery(sql, Long.class).setParameter("maLichTrinh", maLichTrinh).getSingleResult();

    }

    public boolean updateTrangThaiChuyenTau(String maLichTrinh, boolean trangThai) {
        return executeTransaction(() -> {
            LichTrinh lichTrinh = em.find(LichTrinh.class, maLichTrinh);
            if (lichTrinh != null) {
                lichTrinh.setTrangThai(trangThai);
                em.merge(lichTrinh);
            }
        });
    }

    public boolean updateTrangThaiCT(boolean trangThai) {
        return executeTransaction(() -> {
            String sql = "UPDATE LichTrinh SET trangThai = :trangThai";
            em.createQuery(sql)
                    .setParameter("trangThai", trangThai)
                    .executeUpdate();
        });
    }

    public boolean update(LichTrinh lichTrinh) {
        return executeTransaction(() -> em.merge(lichTrinh));
    }

    public boolean updateInfo(LichTrinh lichTrinh) {
        return executeTransaction(() -> {
            LichTrinh existingLichTrinh = em.find(LichTrinh.class, lichTrinh.getMaLichTrinh());
            if (existingLichTrinh != null) {
                existingLichTrinh.setThoiGianKhoiHanh(lichTrinh.getThoiGianKhoiHanh());
                existingLichTrinh.setThoiGianDuKienDen(lichTrinh.getThoiGianDuKienDen());
                existingLichTrinh.setGaDi(lichTrinh.getGaDi());
                existingLichTrinh.setGaDen(lichTrinh.getGaDen());
                existingLichTrinh.setTrangThai(lichTrinh.isTrangThai());
                em.merge(existingLichTrinh);
            }
        });
    }

    public boolean create(LichTrinh lichTrinh) {
        return executeTransaction(() -> em.persist(lichTrinh));
    }

    public LichTrinh getOne(String maLichTrinh) {
        return em.find(LichTrinh.class, maLichTrinh);
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
