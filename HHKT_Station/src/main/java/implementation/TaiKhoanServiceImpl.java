package implementation;

import dao.TaiKhoan_DAO;
import entity.TaiKhoan;
import service.TaiKhoanService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class TaiKhoanServiceImpl extends UnicastRemoteObject implements TaiKhoanService {
    private TaiKhoan_DAO taiKhoan_DAO;

    public TaiKhoanServiceImpl(TaiKhoan_DAO taiKhoan_DAO) throws RemoteException {
        this.taiKhoan_DAO = taiKhoan_DAO;
    }

    @Override
    public List<TaiKhoan> getAllTaiKhoan() throws RemoteException {
        return taiKhoan_DAO.getAllTaiKhoan();
    }

    @Override
    public boolean create(TaiKhoan tk) throws RemoteException {
        return taiKhoan_DAO.create(tk);
    }

    @Override
    public boolean delete(String maNhanVien) throws RemoteException {
        return taiKhoan_DAO.delete(maNhanVien);
    }

    @Override
    public boolean update(TaiKhoan tk) throws RemoteException {
        return taiKhoan_DAO.update(tk);
    }

    @Override
    public boolean doiMatKhau(String maNv, String matKhauMoi) throws RemoteException {
        return taiKhoan_DAO.doiMatKhau(maNv, matKhauMoi);
    }

    @Override
    public TaiKhoan getTaiKhoanTheoMaNV(String maNhanVien) throws RemoteException {
        return taiKhoan_DAO.getTaiKhoanTheoMaNV(maNhanVien);
    }
}
