package implementation;

import dao.LoaiTau_DAO;
import entity.LoaiTau;
import service.LoaiTauService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class LoaiTauServiceImpl extends UnicastRemoteObject implements LoaiTauService {
    private LoaiTau_DAO loaiTau_DAO;

    public LoaiTauServiceImpl(LoaiTau_DAO loaiTau_DAO) throws RemoteException {
        this.loaiTau_DAO = loaiTau_DAO;
    }

    @Override
    public List<LoaiTau> getAllLoaiTau() throws RemoteException {
        return loaiTau_DAO.getAllLoaiTau();
    }

    @Override
    public boolean create(LoaiTau loaiTau) throws RemoteException {
        return loaiTau_DAO.create(loaiTau);
    }

    @Override
    public boolean update(LoaiTau loaiTau) throws RemoteException {
        return loaiTau_DAO.update(loaiTau);
    }

    @Override
    public boolean delete(String maLoaiTau) throws RemoteException {
        return loaiTau_DAO.delete(maLoaiTau);
    }

    @Override
    public LoaiTau getLoaiTauTheoMa(String maLoaiTau) throws RemoteException {
        return loaiTau_DAO.getLoaiTauTheoMa(maLoaiTau);
    }
} 