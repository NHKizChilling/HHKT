package dao;

import entity.HoaDon;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import util.JPAUtil;

import java.time.LocalDate;
import java.util.List;

public class HoaDon_DAO {
    private final EntityManager em;

    public HoaDon_DAO() {
        this.em = JPAUtil.getEntityManager();
    }

    public List<HoaDon> getAllHoaDon() {

        return em.createQuery("from HoaDon ", HoaDon.class).getResultList();
    }

    public HoaDon getHoaDonTheoMa(String maHoaDon) {
        return em.find(HoaDon.class, maHoaDon);
    }

    public List<HoaDon> getHoaDonTheoNV(String maNV, LocalDate ngayLap) {
        String sql = "from HoaDon where nhanVien.maNV = :maNV and YEAR(ngayLapHoaDon) = :nam and MONTH(ngayLapHoaDon) = :thang and DAY(ngayLapHoaDon) = :ngay";
        return em.createQuery(sql, HoaDon.class)
                .setParameter("maNV", maNV)
                .setParameter("nam", ngayLap.getYear())
                .setParameter("thang", ngayLap.getMonthValue())
                .setParameter("ngay", ngayLap.getDayOfMonth())
                .getResultList();
    }

    public List<HoaDon> getHoaDonTheoKH(String maKH) {
        String sql = "from HoaDon where khachHang.maKH = :maKH";
        return em.createQuery(sql, HoaDon.class).setParameter("maKH", maKH).getResultList();
    }

    public List<HoaDon> getHoaDonTheoNV(String maNV) {
        String sql = "from HoaDon where nhanVien.maNV = :maNV";
        return em.createQuery(sql, HoaDon.class).setParameter("maNV", maNV).getResultList();
    }

    public HoaDon getHoaDonVuaTao() {
        String sql = "from HoaDon where trangThai = false and tongTien = 0";
        return em.createQuery(sql, HoaDon.class).getSingleResult();
    }

    public boolean create(HoaDon hoaDon) {
        String sql = "INSERT INTO HoaDon(ma_nv, ma_kh, ngay_lap_hoa_don, tong_tien, tong_giam_gia, ma_km, trang_thai) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?)";
        try {
            em.getTransaction().begin();

            em.createNativeQuery(sql)
                    .setParameter(1, hoaDon.getNhanVien().getMaNV())
                    .setParameter(2, hoaDon.getKhachHang().getMaKH())
                    .setParameter(3, hoaDon.getNgayLapHoaDon())
                    .setParameter(4, hoaDon.getTongTien())
                    .setParameter(5, hoaDon.getTongGiamGia())
                    .setParameter(6, hoaDon.getKhuyenMai().getMaKM())
                    .setParameter(7, hoaDon.isTrangThai())
                    .executeUpdate();

            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            return false;
        }
        return true;
    }

    public boolean createTempInvoice(HoaDon hoaDon) {
        String sql = "INSERT INTO HoaDon(ma_nv, ma_kh, ngay_lap_hoa_don, tong_tien, tong_giam_gia, trang_thai) " +
                "VALUES(?, ?, ?, 0, 0, ?)";
        try {
            em.getTransaction().begin();

            em.createNativeQuery(sql)
                    .setParameter(1, hoaDon.getNhanVien().getMaNV())
                    .setParameter(2, hoaDon.getKhachHang().getMaKH())
                    .setParameter(3, hoaDon.getNgayLapHoaDon())
                    .setParameter(4, hoaDon.isTrangThai())
                    .executeUpdate();

            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            return false;
        }
        return true;
    }


    public List<HoaDon> getDSHDLuuTam() {
        String sql = "from HoaDon where trangThai = false and tongTien != 0";
        return em.createQuery(sql, HoaDon.class).getResultList();
    }

    public boolean update(HoaDon hoaDon) {
        return executeTransaction(() -> em.merge(hoaDon));
    }

    public boolean delete(HoaDon hoaDon) {
        HoaDon hd = em.find(HoaDon.class, hoaDon.getMaHD());
        return executeTransaction(() -> em.remove(hd));
    }

    public List<HoaDon> getDSHDTheoNam(String nam) {
        String sql = "from HoaDon where YEAR(ngayLapHoaDon) = :nam";
        return em.createQuery(sql, HoaDon.class).setParameter("nam", nam).getResultList();
    }

    public List<HoaDon> getDSHDTheoThang(int thang, int nam) {
        String sql = "from HoaDon where YEAR(ngayLapHoaDon) = :nam and MONTH(ngayLapHoaDon) = :thang";
        return em.createQuery(sql, HoaDon.class)
                .setParameter("nam", nam)
                .setParameter("thang", thang)
                .getResultList();
    }

    public List<HoaDon> getDSHDTheoNgay(LocalDate ngayLap) {
        String sql = "from HoaDon where YEAR(ngayLapHoaDon) = :nam and MONTH(ngayLapHoaDon) = :thang and DAY(ngayLapHoaDon) = :ngay";
        return em.createQuery(sql, HoaDon.class)
                .setParameter("nam", ngayLap.getYear())
                .setParameter("thang", ngayLap.getMonthValue())
                .setParameter("ngay", ngayLap.getDayOfMonth())
                .getResultList();
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
