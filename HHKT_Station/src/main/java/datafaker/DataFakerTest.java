package datafaker;

import entity.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import net.datafaker.Faker;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class DataFakerTest {

    EntityManager em = createEntityManager();
    EntityTransaction tr = em.getTransaction();
    Random random = new Random();

    public static void main(String[] args) {
        DataFakerTest dataFaker = new DataFakerTest();
        Faker faker = new Faker();
        dataFaker.createTaiKhoan(faker);
        dataFaker.createKhachHang(faker);
//        dataFaker.createCaLamViec(faker);
        dataFaker.createGa(faker);
        dataFaker.createLoaiTau(faker);
        dataFaker.createChuyenTau(faker);
        dataFaker.createLoaiToa(faker);
        dataFaker.createToa(faker);
        dataFaker.createChoNgoi(faker);
        dataFaker.createLichTrinh(faker);
        dataFaker.createKhuyenMai(faker);
        dataFaker.createLoaiVe(faker);
        dataFaker.createVe(faker);
    }

    private EntityManager createEntityManager() {
        return Persistence.createEntityManagerFactory("HHKT Station").createEntityManager();
    }

    public String getCurrentDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
        return sdf.format(new Date());
    }

    public List<NhanVien> createNhanVien(Faker faker) {
        List<NhanVien> nhanViens = new ArrayList<>();
        try {
            if (!tr.isActive()) {
                tr.begin();
            }

            for (int i = 0; i < 10; i++) {
                NhanVien nhanVien = new NhanVien();
                nhanVien.setMaNV("NV" + String.format("%07d", i));
                nhanVien.setTenNV(faker.name().fullName());
                nhanVien.setSoCCCD(faker.number().digits(12));
                nhanVien.setNgaySinh(LocalDate.now().minusYears(20).minusDays(random.nextInt(365 * 30)));
                nhanVien.setGioiTinh(random.nextBoolean());
                nhanVien.setSdt(String.format("%010d", random.nextInt(1000000000)));
                nhanVien.setEmail(faker.internet().emailAddress());
                nhanVien.setChucVu(random.nextBoolean() ? "Nhân Viên" : "Quản Lý");
                nhanVien.setTinhTrangCv(faker.options().option("Đã nghỉ", "Đang làm", "Tạm nghỉ"));
                nhanViens.add(nhanVien);
            }

            if (tr.isActive()) {
                tr.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (tr.isActive()) {
                tr.rollback();
            }
        }
        return nhanViens;
    }


//    public NhanVien createNhanVien(Faker faker) {
//
//        try {
//            tr.begin();
//            for (int i = 0; i < 10; i++) {
//
//                NhanVien nhanVien = new NhanVien();
//                nhanVien.setMaNV("NV" + String.format("%09d", i));
//                nhanVien.setTenNV(faker.name().fullName());
//                nhanVien.setSoCCCD(faker.number().digits(12));
//                nhanVien.setNgaySinh(LocalDate.now().minusYears(20).minusDays(random.nextInt(365 * 30)));
//                nhanVien.setGioiTinh(random.nextBoolean());
//                nhanVien.setSdt(String.format("%010d", random.nextInt(1000000000)));
//                nhanVien.setEmail(faker.internet().emailAddress());
//                nhanVien.setChucVu(random.nextBoolean() ? "Nhân Viên" : "Quản Lý");
//                nhanVien.setTinhTrangCv(faker.options().option("Đã nghỉ", "Đang làm", "Tạm nghỉ"));
//
//                em.persist(nhanVien);
//            }
//            tr.commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//        return null;
//    }

    public List<TaiKhoan> createTaiKhoan(Faker faker) {
        List<TaiKhoan> taiKhoans = new ArrayList<>();
        try {
            if (!tr.isActive()) {
                tr.begin();
            }

            List<NhanVien> nhanViens = (List<NhanVien>) createNhanVien(faker);
            if (nhanViens != null && !nhanViens.isEmpty()) {
                for (NhanVien nhanVien : nhanViens) {
                    em.persist(nhanVien);

                    TaiKhoan taiKhoan = new TaiKhoan();
                    taiKhoan.setNhanVien(nhanVien);
                    taiKhoan.setMatKhau(faker.internet().password());
                    taiKhoan.setTrangThaiTK(faker.options().option("Đang hoạt động", "Đã ngưng hoạt động", "Tạm ngưng hoạt động"));

                    em.persist(taiKhoan);
                    taiKhoans.add(taiKhoan);
                }
            }
            if (tr.isActive()) {
                tr.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (tr.isActive()) {
                tr.rollback();
            }
        }
        return taiKhoans;
    }

    public KhachHang createKhachHang(Faker faker) {
        try {
            tr.begin();
            for (int i = 0; i < 10; i++) {
                KhachHang khachHang = new KhachHang();
                khachHang.setMaKH("KH" + String.format("%07d", i));
                khachHang.setTenKH(faker.name().fullName());
                khachHang.setSoCCCD(faker.number().digits(12));
                khachHang.setSdt(String.format("%010d", random.nextInt(1000000000)));
                khachHang.setEmail(faker.internet().emailAddress());
                em.persist(khachHang);
            }
            tr.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (tr.isActive()) {
                tr.rollback();
            }
        }
        return null;
    }

//    public CaLamViec createCaLamViec(Faker faker) {
//        try {
//            tr.begin();
//            for (int i = 0; i < 10; i++) {
//                CaLamViec caLamViec = new CaLamViec();
//                caLamViec.setNhanVien(createNhanVien(faker));
//                LocalDateTime gioMoCa = LocalDateTime.now().minusHours(random.nextInt(720)); // Random giờ trong vòng 30 ngày qua
//                caLamViec.setGioMoCa(gioMoCa);
//                caLamViec.setGioKetCa(gioMoCa.plusHours(faker.number().numberBetween(1, 12)));
//                caLamViec.setTienDauCa(faker.number().randomDouble(2, 100, 1000));
//                caLamViec.setTienKetCa(faker.number().randomDouble(2, 100, 1000));
//                caLamViec.setGhiChu(faker.lorem().sentence());
//                caLamViec.setTrangThaiCa(faker.bool().bool());
//                em.persist(caLamViec);
//            }
//            tr.commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//        return null;
//    }

    public Ga createGa(Faker faker) {
        try {
            tr.begin();
            for (int i = 0; i < 10; i++) {
                Ga ga = new Ga();
                ga.setMaGa(faker.number().digits(10));
                ga.setTenGa(faker.address().city());
                ga.setViTri(faker.address().city());
                ga.setKhoangCach((short) faker.number().numberBetween(1, 666));
                em.persist(ga);
            }
            tr.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (tr.isActive()) {
                tr.rollback();
            }
        }
        return null;
    }

    public LoaiTau createLoaiTau(Faker faker) {
        try {
            if (!tr.isActive()) {
                tr.begin();
            }
            tr.begin();
            for (int i = 0; i < 10; i++) {
                LoaiTau loaiTau = new LoaiTau();
                loaiTau.setMaLoaiTau(faker.number().digits(5));
                loaiTau.setTenLoaiTau(faker.lorem().characters(30));
                em.persist(loaiTau);
            }

            if (tr.isActive()) {
                tr.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (tr.isActive()) {
                tr.rollback();
            }
        }
        return null;
    }

    public ChuyenTau createChuyenTau(Faker faker) {
        try {

            if (!tr.isActive()) {
                tr.begin();
            }

            for (int i = 0; i < 10; i++) {
                ChuyenTau chuyenTau = new ChuyenTau();
                chuyenTau.setSoHieuTau(faker.number().digits(5));
                chuyenTau.setLoaiTau(createLoaiTau(faker));
                em.persist(chuyenTau);
            }

            if (tr.isActive()) {
                tr.commit();
            }

        } catch (Exception e) {
            e.printStackTrace();
            if (tr.isActive()) {
                tr.rollback();
            }
        }
        return null;
    }

    public LoaiToa createLoaiToa(Faker faker) {
        try {

            if (!tr.isActive()) {
                tr.begin();
            }

            for (int i = 0; i < 10; i++) {
                LoaiToa loaiToa = new LoaiToa();
                loaiToa.setMaLoaiToa(faker.options().option("NC", "NM", "GNK6", "GNK4", "TVIP"));
                loaiToa.setTenLoaiToa(faker.lorem().word());
                em.persist(loaiToa);
            }

            if (tr.isActive()) {
                tr.commit();
            }

        } catch (Exception e) {
            e.printStackTrace();
            if (tr.isActive()) {
                tr.rollback();
            }
        }
        return null;
    }

    public Toa createToa(Faker faker) {
        try {
            tr.begin();
            for (int i = 0; i < 10; i++) {
                Toa toa = new Toa();
                toa.setMaToa(faker.number().digits(10));
                toa.setSttToa((short) faker.number().numberBetween(1, 12));
                toa.setSoHieuTau(createChuyenTau(faker));
                toa.setLoaiToa(createLoaiToa(faker));
                em.persist(toa);
            }
            tr.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (tr.isActive()) {
                tr.rollback();
            }
        }
        return null;
    }

    public ChoNgoi createChoNgoi(Faker faker) {
        try {

            if (!tr.isActive()) {
                tr.begin();
            }

            for (int i = 0; i < 10; i++) {
                ChoNgoi choNgoi = new ChoNgoi();
                choNgoi.setMaCho(faker.number().digits(15));
                choNgoi.setToa(createToa(faker));
                choNgoi.setSttCho((short) faker.number().numberBetween(1, 48));
                choNgoi.setTang((short) faker.number().numberBetween(1, 3));
                choNgoi.setKhoang((short) faker.number().numberBetween(1, 7));
                em.persist(choNgoi);
            }

            if (tr.isActive()) {
                tr.commit();
            }

        } catch (Exception e) {
            e.printStackTrace();
            if (tr.isActive()) {
                tr.rollback();
            }
        }
        return null;
    }

    public LichTrinh createLichTrinh(Faker faker) {
        try {
            tr.begin();
            for (int i = 0; i < 10; i++) {
                LichTrinh lichTrinh = new LichTrinh();
                lichTrinh.setMaLichTrinh(faker.number().digits(20));
                lichTrinh.setSoHieuTau(createChuyenTau(faker));
                lichTrinh.setGaDi(createGa(faker));
                lichTrinh.setGaDen(createGa(faker));
                lichTrinh.setThoiGianKhoiHanh(LocalDateTime.now().plusDays(random.nextInt(30)));
                lichTrinh.setThoiGianDuKienDen(lichTrinh.getThoiGianKhoiHanh().plusDays(random.nextInt(30)));
                lichTrinh.setTrangThai(faker.bool().bool());
                em.persist(lichTrinh);
            }

            if (tr.isActive()) {
                tr.commit();
            }

        } catch (Exception e) {
            e.printStackTrace();
            if (tr.isActive()) {
                tr.rollback();
            }
        }
        return null;
    }

    public KhuyenMai createKhuyenMai(Faker faker) {
        try {
            tr.begin();
            for (int i = 0; i < 10; i++) {
                KhuyenMai khuyenMai = new KhuyenMai();
                khuyenMai.setMaKM(faker.number().digits(11));
                khuyenMai.setMoTa(faker.lorem().fixedString(50));
                khuyenMai.setNgayApDung(LocalDate.now().plusDays(random.nextInt(30)));
                khuyenMai.setNgayHetHan(khuyenMai.getNgayApDung().plusDays(random.nextInt(30)));
                khuyenMai.setMucKM((float) faker.number().randomDouble(2, 0, 1));
                khuyenMai.setTrangThai(faker.bool().bool());
                em.persist(khuyenMai);
            }
            tr.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (tr.isActive()) {
                tr.rollback();
            }
        }
        return null;
    }

    public LoaiVe createLoaiVe(Faker faker) {
        try {
            tr.begin();
            for (int i = 0; i < 10; i++) {
                LoaiVe loaiVe = new LoaiVe();
                loaiVe.setMaLoaiVe(faker.options().option("VNL", "VTE", "VNCT", "VHSSV"));
                loaiVe.setTenLoaiVe(faker.lorem().characters(50));
                loaiVe.setMucGiamGia((float) faker.number().randomDouble(2, 0, 1));
                em.persist(loaiVe);
            }
            tr.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (tr.isActive()) {
                tr.rollback();
            }
        }
        return null;
    }

    public Ve createVe(Faker faker) {
        try {
            tr.begin();
            for (int i = 0; i < 10; i++) {
                Ve ve = new Ve();
                ve.setMaVe("VE" + getCurrentDate() + String.format("%06d", i));
                KhachHang khachHang = createKhachHang(faker);
                ve.setKhachHang(khachHang);
                ve.setNgaySinh(LocalDate.now().minusYears(20).minusDays(random.nextInt(365 * 30)));
                ve.setTenKH(khachHang.getTenKH());
                ve.setSoCCCD(khachHang.getSoCCCD());
                ve.setChiTietLichTrinh(null); //set this later
                ve.setLoaiVe(createLoaiVe(faker));
                ve.setTinhTrangVe(faker.options().option("DaBan", "DaDoi", "DaHuy"));
                ve.setKhuHoi(faker.bool().bool());
                em.persist(ve);
            }
            if (tr.isActive()) {
                tr.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (tr.isActive()) {
                tr.rollback();
            }
        }
        return null;
    }
}
