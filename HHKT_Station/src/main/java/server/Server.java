package server;

import java.rmi.registry.LocateRegistry;

import javax.naming.Context;
import javax.naming.InitialContext;

import dao.*;
import implementation.*;
import service.*;

public class Server {
    public static void main(String[] args) throws Exception {
        Context context = new InitialContext();
        LocateRegistry.createRegistry(7701);

        // Khởi tạo các service
        CaLamViecService caLamViecService = new CaLamViecServiceImpl(new CaLamViec_DAO());
        TaiKhoanService taiKhoanService = new TaiKhoanServiceImpl(new TaiKhoan_DAO());
        HoaDonService hoaDonService = new HoaDonServiceImpl(new HoaDon_DAO());
        NhanVienService nhanVienService = new NhanVienServiceImpl(new NhanVien_DAO());
        GaService gaService = new GaServiceImpl(new Ga_DAO());
        KhachHangService khachHangService = new KhachHangServiceImpl(new KhachHang_DAO());
        KhuyenMaiService khuyenMaiService = new KhuyenMaiServiceImpl(new KhuyenMai_DAO());
        LichTrinhService lichTrinhService = new LichTrinhServiceImpl(new LichTrinh_DAO());
        LoaiTauService loaiTauService = new LoaiTauServiceImpl(new LoaiTau_DAO());
        LoaiToaService loaiToaService = new LoaiToaServiceImpl(new LoaiToa_DAO());
        LoaiVeService loaiVeService = new LoaiVeServiceImpl(new LoaiVe_DAO());
        ToaService toaService = new ToaServiceImpl(new Toa_DAO());
        VeService veService = new VeServiceImpl(new Ve_DAO());
        ChoNgoiService choNgoiService = new ChoNgoiServiceImpl(new ChoNgoi_DAO());
        ChuyenTauService chuyenTauService = new ChuyenTauServiceImpl(new ChuyenTau_DAO());
        CT_LichTrinhService ctLichTrinhService = new CT_LichTrinhServiceImpl(new CT_LichTrinh_DAO());
        CT_HoaDonService ctHoaDonService = new CT_HoaDonServiceImpl(new CT_HoaDon_DAO());

        // Bind các service vào registry
        context.bind("rmi://localhost:7701/taiKhoanService", taiKhoanService);
        context.bind("rmi://MSI:7701/caLamViecService", caLamViecService);
        context.bind("rmi://MSI:7701/hoaDonService", hoaDonService);
        context.bind("rmi://MSI:7701/nhanVienService", nhanVienService);
        context.bind("rmi://MSI:7701/gaService", gaService);
        context.bind("rmi://MSI:7701/khachHangService", khachHangService);
        context.bind("rmi://MSI:7701/khuyenMaiService", khuyenMaiService);
        context.bind("rmi://MSI:7701/lichTrinhService", lichTrinhService);
        context.bind("rmi://MSI:7701/loaiTauService", loaiTauService);
        context.bind("rmi://MSI:7701/loaiToaService", loaiToaService);
        context.bind("rmi://MSI:7701/loaiVeService", loaiVeService);
        context.bind("rmi://MSI:7701/toaService", toaService);
        context.bind("rmi://MSI:7701/veService", veService);
        context.bind("rmi://MSI:7701/choNgoiService", choNgoiService);
        context.bind("rmi://MSI:7701/chuyenTauService", chuyenTauService);
        context.bind("rmi://MSI:7701/ctLichTrinhService", ctLichTrinhService);
        context.bind("rmi://MSI:7701/ctHoaDonService", ctHoaDonService);

        System.out.println("Server is running on port 7701");
    }
}
