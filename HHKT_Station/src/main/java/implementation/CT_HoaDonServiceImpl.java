package implementation;

import dao.CT_HoaDon_DAO;
import entity.ChiTietHoaDon;
import service.CT_HoaDonService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class CT_HoaDonServiceImpl extends UnicastRemoteObject implements CT_HoaDonService {
    private CT_HoaDon_DAO ct_hoaDon_DAO;

    public CT_HoaDonServiceImpl(CT_HoaDon_DAO ct_hoaDon_DAO) throws RemoteException {
        this.ct_hoaDon_DAO = ct_hoaDon_DAO;
    }

    @Override
    public List<ChiTietHoaDon> getAllCT_HoaDon() throws RemoteException {
        return ct_hoaDon_DAO.getAllCT_HoaDon();
    }

    @Override
    public ChiTietHoaDon getCT_HoaDon(String maHD, String maVe) throws RemoteException {
        return ct_hoaDon_DAO.getCT_HoaDon(maHD, maVe);
    }

    @Override
    public ChiTietHoaDon getCT_HoaDonTheoMaVe(String maVe) throws RemoteException {
        return ct_hoaDon_DAO.getCT_HoaDonTheoMaVe(maVe);
    }

    @Override
    public List<ChiTietHoaDon> getCT_HoaDon(String maHD) throws RemoteException {
        return ct_hoaDon_DAO.getCT_HoaDon(maHD);
    }

    @Override
    public boolean create(ChiTietHoaDon cthd) throws RemoteException {
        return ct_hoaDon_DAO.create(cthd);
    }

    @Override
    public boolean update(ChiTietHoaDon cthd) throws RemoteException {
        return ct_hoaDon_DAO.update(cthd);
    }

    @Override
    public boolean delete(ChiTietHoaDon cthd) throws RemoteException {
        return ct_hoaDon_DAO.delete(cthd);
    }
} 