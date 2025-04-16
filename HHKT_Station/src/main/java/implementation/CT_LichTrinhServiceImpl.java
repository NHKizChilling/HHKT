package implementation;

import dao.CT_LichTrinh_DAO;
import entity.ChiTietLichTrinh;
import service.CT_LichTrinhService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class CT_LichTrinhServiceImpl extends UnicastRemoteObject implements CT_LichTrinhService {
    private CT_LichTrinh_DAO ct_lichTrinh_DAO;

    public CT_LichTrinhServiceImpl(CT_LichTrinh_DAO ct_lichTrinh_DAO) throws RemoteException {
        this.ct_lichTrinh_DAO = ct_lichTrinh_DAO;
    }

    @Override
    public List<ChiTietLichTrinh> getAllChiTietLichTrinh() throws RemoteException {
        return ct_lichTrinh_DAO.getAllChiTietLichTrinh();
    }

    @Override
    public boolean create(ChiTietLichTrinh ctlt) throws RemoteException {
        return ct_lichTrinh_DAO.create(ctlt);
    }

    @Override
    public boolean update(ChiTietLichTrinh ctlt) throws RemoteException {
        return ct_lichTrinh_DAO.update(ctlt);
    }

    @Override
    public boolean updateCTLT(ChiTietLichTrinh ctlt, boolean trangThai) throws RemoteException {
        return ct_lichTrinh_DAO.updateCTLT(ctlt, trangThai);
    }

    @Override
    public boolean delete(String maLT, String maCN) throws RemoteException {
        return ct_lichTrinh_DAO.delete(maLT, maCN);
    }

    @Override
    public List<ChiTietLichTrinh> getCtltTheoTrangThai(boolean trangThai) throws RemoteException {
        return ct_lichTrinh_DAO.getCtltTheoTrangThai(trangThai);
    }

    @Override
    public ChiTietLichTrinh getCTLTTheoCN(String maLichTrinh, String maChoNgoi) throws RemoteException {
        return ct_lichTrinh_DAO.getCTLTTheoCN(maLichTrinh, maChoNgoi);
    }

    @Override
    public boolean getTrangThaiCN(String maLichTrinh, String maCho) throws RemoteException {
        return ct_lichTrinh_DAO.getTrangThaiCN(maLichTrinh, maCho);
    }

    @Override
    public void addChiTietLichTrinh(String maLichTrinh) throws RemoteException {
        ct_lichTrinh_DAO.addChiTietLichTrinh(maLichTrinh);
    }

    @Override
    public List<ChiTietLichTrinh> getCtltTheoMaLichTrinh(String maLichTrinh) throws RemoteException {
        return ct_lichTrinh_DAO.getCtltTheoMaLichTrinh(maLichTrinh);
    }
} 