package implementation;

import dao.NhanVien_DAO;
import entity.NhanVien;
import service.NhanVienService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class NhanVienServiceImpl extends UnicastRemoteObject implements NhanVienService {
    private NhanVien_DAO nhanVien_DAO;

    public NhanVienServiceImpl(NhanVien_DAO nhanVien_DAO) throws RemoteException {
        this.nhanVien_DAO = nhanVien_DAO;
    }

    @Override
    public List<NhanVien> getAll() throws RemoteException {
        return nhanVien_DAO.getAll();
    }

    @Override
    public NhanVien getNhanVien(String maNV) throws RemoteException {
        return nhanVien_DAO.getNhanVien(maNV);
    }

    @Override
    public boolean create(NhanVien nhanVien) throws RemoteException {
        return nhanVien_DAO.create(nhanVien);
    }

    @Override
    public boolean updateInfo(NhanVien nhanVien) throws RemoteException {
        return nhanVien_DAO.updateInfo(nhanVien);
    }

    @Override
    public boolean delete(String maNV) throws RemoteException {
        return nhanVien_DAO.delete(maNV);
    }

    @Override
    public boolean updateTinhTrangCV(String maNV, String tinhTrangCV) throws RemoteException {
        return nhanVien_DAO.updateTinhTrangCV(maNV, tinhTrangCV);
    }

    @Override
    public List<NhanVien> getDSQuanLy() throws RemoteException {
        return nhanVien_DAO.getDSQuanLy();
    }

    @Override
    public List<NhanVien> getDSNhanVien() throws RemoteException {
        return nhanVien_DAO.getDSNhanVien();
    }

    @Override
    public NhanVien getNhanVienTheoTen(String tenNV) throws RemoteException {
        return nhanVien_DAO.getNhanVienTheoTen(tenNV);
    }

    @Override
    public NhanVien getNhanVienTheoSDT(String sdt) throws RemoteException {
        return nhanVien_DAO.getNhanVienTheoSDT(sdt);
    }
} 