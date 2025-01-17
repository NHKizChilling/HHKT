package datafaker;

import entity.*;
import jakarta.persistence.Column;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import net.datafaker.Faker;
import org.hibernate.tool.schema.spi.CommandAcceptanceException;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Random;

public class DataFakerTest {

    public static void main(String[] args) {
        DataFakerTest dataFaker = new DataFakerTest();
        Faker faker = new Faker();
        dataFaker.createNhanVien(faker);
        dataFaker.createTaiKhoan(faker);
        dataFaker.createKhachHang(faker);
        dataFaker.createCaLamViec(faker);
        dataFaker.createGa(faker);
        dataFaker.createLoaiTau(faker);
        dataFaker.createChuyenTau(faker);
        dataFaker.createLoaiToa(faker);
        dataFaker.createToa(faker);
        dataFaker.createChoNgoi(faker);
        dataFaker.createLichTrinh(faker);
        dataFaker.createChiTietLichTrinh(faker);
        dataFaker.createKhuyenMai(faker);
        dataFaker.createHoaDon(faker);
        dataFaker.createLoaiVe(faker);
        dataFaker.createVe(faker);
        dataFaker.createChiTietHoaDon(faker);

    }

    EntityManager em = Persistence.createEntityManagerFactory(
            "HHKT Station").createEntityManager();
    EntityTransaction tr = em.getTransaction();
    Faker faker = new Faker();
    Random random = new Random();

    public String getCurrentDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
        return sdf.format(new Date());
    }

    private static int sequenceNumber = 1;

    public synchronized int getNextSequence() {
        return sequenceNumber++;
    }

    public NhanVien createNhanVien(Faker faker) {
        try {
            tr.begin();
            for (int i = 0; i < 10; i++) {

                NhanVien nhanVien = new NhanVien();
                nhanVien.setMaNV("NV" + String.format("%09d", getNextSequence()));
                nhanVien.setTenNV(faker.name().fullName());
                nhanVien.setSoCCCD(faker.number().digits(12));
                nhanVien.setNgaySinh(LocalDate.now().minusYears(20).minusDays(random.nextInt(365 * 30)));
                nhanVien.setGioiTinh(random.nextBoolean());
                nhanVien.setSdt(String.format("%010d", random.nextInt(1000000000)));
                nhanVien.setEmail(faker.internet().emailAddress());
                nhanVien.setChucVu(random.nextBoolean() ? "Nhân Viên" : "Quản Lý");
                nhanVien.setTinhTrangCv(faker.options().option("Đã nghỉ", "Đang làm", "Tạm nghỉ"));

                em.persist(nhanVien);
            }
            tr.commit();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public TaiKhoan createTaiKhoan(Faker faker) {
        try {
            tr.begin();
            for (int i = 0; i < 10; i++) {
                TaiKhoan taiKhoan = new TaiKhoan();
                taiKhoan.setNhanVien(createNhanVien(faker));
                taiKhoan.setMatKhau(faker.internet().password());
                taiKhoan.setTrangThaiTK(faker.lorem().word());
                em.persist(taiKhoan);
            }
            tr.commit();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public KhachHang createKhachHang(Faker faker) {
        try {
            tr.begin();
            for (int i = 0; i < 10; i++) {
                KhachHang khachHang = new KhachHang();
                khachHang.setMaKH("KH" + String.format("%09d", getNextSequence()));
                khachHang.setTenKH(faker.name().fullName());
                khachHang.setSoCCCD(faker.number().digits(12));
                khachHang.setSdt(String.format("%010d", random.nextInt(1000000000)));
                khachHang.setEmail(faker.internet().emailAddress());
                em.persist(khachHang);
            }
            tr.commit();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public CaLamViec createCaLamViec(Faker faker) {
        try {
            tr.begin();
            for (int i = 0; i < 10; i++) {
                CaLamViec caLamViec = new CaLamViec();
                caLamViec.setNhanVien(createNhanVien(faker));
                LocalDateTime gioMoCa = LocalDateTime.now().minusHours(random.nextInt(720)); // Random giờ trong vòng 30 ngày qua
                caLamViec.setGioMoCa(gioMoCa);
                caLamViec.setGioKetCa(gioMoCa.plusHours(faker.number().numberBetween(1, 12)));
                caLamViec.setTienDauCa(faker.number().randomDouble(2, 100, 1000));
                caLamViec.setTienKetCa(faker.number().randomDouble(2, 100, 1000));
                caLamViec.setGhiChu(faker.lorem().sentence());
                caLamViec.setTrangThaiCa(faker.bool().bool());
                em.persist(caLamViec);
            }
            tr.commit();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public Ga createGa(Faker faker) {
        try {
            tr.begin();
            for (int i = 0; i < 10; i++) {
                Ga ga = new Ga();
                ga.setMaGa(faker.number().digits(11));
                ga.setTenGa(faker.lorem().word());
                ga.setViTri(faker.lorem().word());
                ga.setKhoangCach((short) faker.number().numberBetween(1, 100));
                em.persist(ga);
            }
            tr.commit();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public LoaiTau createLoaiTau(Faker faker) {
        try {
            tr.begin();
            for (int i = 0; i < 10; i++) {
                LoaiTau loaiTau = new LoaiTau();
                loaiTau.setMaLoaiTau(faker.number().digits(11));
                loaiTau.setTenLoaiTau(faker.lorem().word());
                em.persist(loaiTau);
            }
            tr.commit();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public ChuyenTau createChuyenTau(Faker faker) {
        try {
            tr.begin();
            for (int i = 0; i < 10; i++) {
                ChuyenTau chuyenTau = new ChuyenTau();
                chuyenTau.setSoHieuTau(faker.number().digits(11));
                chuyenTau.setLoaiTau(createLoaiTau(faker));
                em.persist(chuyenTau);
            }
            tr.commit();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public LoaiToa createLoaiToa(Faker faker) {
        try {
            tr.begin();
            for (int i = 0; i < 10; i++) {
                LoaiToa loaiToa = new LoaiToa();
                loaiToa.setMaLoaiToa(faker.number().digits(11));
                loaiToa.setTenLoaiToa(faker.lorem().word());
                em.persist(loaiToa);
            }
            tr.commit();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public Toa createToa(Faker faker) {
        try {
            tr.begin();
            for (int i = 0; i < 10; i++) {
                Toa toa = new Toa();
                toa.setMaToa(faker.number().digits(11));
                toa.setSttToa((short) faker.number().numberBetween(1, 10));
                toa.setSoHieuTau(createChuyenTau(faker));
                toa.setLoaiToa(createLoaiToa(faker));
                em.persist(toa);
            }
            tr.commit();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public ChoNgoi createChoNgoi(Faker faker) {
        try {
            tr.begin();
            for (int i = 0; i < 10; i++) {
                ChoNgoi choNgoi = new ChoNgoi();
                choNgoi.setMaCho(faker.number().digits(11));
                choNgoi.setToa(createToa(faker));
                choNgoi.setSttCho((short) faker.number().numberBetween(1, 100));
                choNgoi.setTang((short) faker.number().numberBetween(1, 10));
                choNgoi.setKhoang((short) faker.number().numberBetween(1, 10));
                em.persist(choNgoi);
            }
            tr.commit();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public LichTrinh createLichTrinh(Faker faker) {
        try {
            tr.begin();
            for (int i = 0; i < 10; i++) {
                LichTrinh lichTrinh = new LichTrinh();
                lichTrinh.setMaLichTrinh(faker.number().digits(11));
                lichTrinh.setSoHieuTau(createChuyenTau(faker));
                lichTrinh.setGaDi(createGa(faker));
                lichTrinh.setGaDen(createGa(faker));
                lichTrinh.setThoiGianKhoiHanh(LocalDateTime.now().minusDays(random.nextInt(30)));
                lichTrinh.setThoiGianDuKienDen(LocalDateTime.now().minusDays(random.nextInt(30)));
                lichTrinh.setTrangThai(faker.bool().bool());
                em.persist(lichTrinh);
            }
            tr.commit();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public ChiTietLichTrinh createChiTietLichTrinh(Faker faker) {
        try {
            tr.begin();
            for (int i = 0; i < 10; i++) {
                ChiTietLichTrinh chiTietLichTrinh = new ChiTietLichTrinh();
                chiTietLichTrinh.setLichTrinh(createLichTrinh(faker));
                em.persist(chiTietLichTrinh);
            }
            tr.commit();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public KhuyenMai createKhuyenMai(Faker faker) {
        try {
            tr.begin();
            for (int i = 0; i < 10; i++) {
                KhuyenMai khuyenMai = new KhuyenMai();
                khuyenMai.setMaKM(faker.number().digits(11));
                khuyenMai.setMoTa(faker.lorem().sentence());
                khuyenMai.setNgayApDung(LocalDate.now().minusDays(random.nextInt(30)));
                khuyenMai.setNgayHetHan(LocalDate.now().minusDays(random.nextInt(30)));
                khuyenMai.setMucKM((float) faker.number().randomDouble(2, 0, 1));
                khuyenMai.setTrangThai(faker.bool().bool());
                em.persist(khuyenMai);
            }
            tr.commit();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public HoaDon createHoaDon(Faker faker) {
        try {
            tr.begin();
            for (int i = 0; i < 10; i++) {
                HoaDon hoaDon = new HoaDon();
                hoaDon.setMaHD("HD" + getCurrentDate() + String.format("%06d", getNextSequence()));
                hoaDon.setNhanVien(createNhanVien(faker));
                hoaDon.setKhachHang(createKhachHang(faker));
                hoaDon.setNgayLapHoaDon(LocalDateTime.now().minusDays(random.nextInt(30)));
                hoaDon.setKhuyenMai(createKhuyenMai(faker));
                hoaDon.setTongGiamGia(faker.number().randomDouble(2, 10000, 100000));
                hoaDon.setTongTien(faker.number().randomDouble(2, 100000, 1000000));
                hoaDon.setTrangThai(faker.bool().bool());
                em.persist(hoaDon);
            }
            tr.commit();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public LoaiVe createLoaiVe(Faker faker) {
        try {
            tr.begin();
            for (int i = 0; i < 10; i++) {
                LoaiVe loaiVe = new LoaiVe();
                loaiVe.setMaLoaiVe(faker.number().digits(11));
                loaiVe.setTenLoaiVe(faker.lorem().word());
                loaiVe.setMucGiamGia((float) faker.number().randomDouble(2, 0, 1));
                em.persist(loaiVe);
            }
            tr.commit();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public Ve createVe(Faker faker) {
        try {
            tr.begin();
            for (int i = 0; i < 10; i++) {
                Ve ve = new Ve();
                ve.setMaVe("VE" + getCurrentDate() + String.format("%06d", getNextSequence()));
                ve.setKhachHang(createKhachHang(faker));
                ve.setChiTietLichTrinh(createChiTietLichTrinh(faker));
                ve.setLoaiVe(createLoaiVe(faker));
                em.persist(ve);
            }
            tr.commit();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public ChiTietHoaDon createChiTietHoaDon(Faker faker) {
        try {
            tr.begin();
            for (int i = 0; i < 10; i++) {
                ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon();
                chiTietHoaDon.setHoaDon(createHoaDon(faker));
                chiTietHoaDon.setVe(createVe(faker));
                chiTietHoaDon.setGiaVe((faker.number().randomDouble(2, 10000, 100000)));
                chiTietHoaDon.setGiaGiam((faker.number().randomDouble(2, 1000, 10000)));
                em.persist(chiTietHoaDon);
            }
            tr.commit();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }
}
