package implementation;

import dao.KhuyenMai_DAO;
import entity.KhuyenMai;
import service.KhuyenMaiService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDate;
import java.util.List;

public class KhuyenMaiServiceImpl extends UnicastRemoteObject implements KhuyenMaiService {
    private KhuyenMai_DAO khuyenMai_DAO;
    public KhuyenMaiServiceImpl(KhuyenMai_DAO khuyenMai_DAO) throws Exception {
        this.khuyenMai_DAO = khuyenMai_DAO;
    }

    @Override
    public List<KhuyenMai> getAllKM() throws RemoteException {
        return khuyenMai_DAO.getAllKM();
    }

    @Override
    public List<KhuyenMai> getKMHienCo() throws RemoteException {
        return khuyenMai_DAO.getKMHienCo();
    }

    @Override
    public KhuyenMai getKMTheoMa(String maKM) throws RemoteException {
        return khuyenMai_DAO.getKMTheoMa(maKM);
    }

    @Override
    public KhuyenMai getKMGiamCaoNhat() throws RemoteException {
        return khuyenMai_DAO.getKMGiamCaoNhat();
    }

    @Override
    public List<KhuyenMai> getKMTheoNgay(LocalDate ngay) throws RemoteException {
        return khuyenMai_DAO.getKMTheoNgay(ngay);
    }

    @Override
    public boolean themKhuyenMai(KhuyenMai km) throws RemoteException {
        return khuyenMai_DAO.themKhuyenMai(km);
    }

    @Override
    public boolean suaKhuyenMai(KhuyenMai km) throws RemoteException {
        return khuyenMai_DAO.suaKhuyenMai(km);
    }

    @Override
    public boolean xoaKhuyenMai(String maKM) throws RemoteException {
        return khuyenMai_DAO.xoaKhuyenMai(maKM);
    }

    @Override
    public boolean kichHoatKhuyenMai() throws RemoteException {
        return khuyenMai_DAO.kichHoatKhuyenMai();
    }

    @Override
    public boolean khoaKhuyenMai() throws RemoteException {
        return khuyenMai_DAO.khoaKhuyenMai();
    }

    @Override
    public boolean capNhatTrangThaiKM(String maKM, boolean trangThai) throws RemoteException {
        return khuyenMai_DAO.capNhatTrangThaiKM(maKM, trangThai);
    }
}
