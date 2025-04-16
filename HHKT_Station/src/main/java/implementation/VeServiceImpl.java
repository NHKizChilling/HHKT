package implementation;

import dao.Ve_DAO;
import entity.Ve;
import service.VeService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class VeServiceImpl extends UnicastRemoteObject implements VeService {
    private Ve_DAO ve_DAO;

    public VeServiceImpl(Ve_DAO ve_DAO) throws RemoteException {
        this.ve_DAO = ve_DAO;
    }

    @Override
    public List<Ve> getAllVe() throws RemoteException {
        return ve_DAO.getAllVe();
    }

    @Override
    public Ve getVeTheoID(String maVe) throws RemoteException {
        return ve_DAO.getVeTheoID(maVe);
    }

    public Ve getLaiVe() throws RemoteException {
        return ve_DAO.getLaiVe();
    }

    @Override
    public boolean updateTinhTrangVe(String maVe, String tinhTrangVe) throws RemoteException {
        return ve_DAO.updateTinhTrangVe(maVe, tinhTrangVe);
    }

    @Override
    public List<Ve> getDSVeTheoMaKH(String maKH) throws RemoteException {
        return ve_DAO.getDSVeTheoMaKH(maKH);
    }

    public List<Ve> getVeTheoTinhTrang(String tinhTrangVe) throws RemoteException {
        return ve_DAO.getVeTheoTinhTrang(tinhTrangVe);
    }

    @Override
    public boolean create(Ve ve) throws RemoteException {
        return ve_DAO.create(ve);
    }

    @Override
    public boolean update(Ve ve) throws RemoteException {
        return ve_DAO.update(ve);
    }
} 