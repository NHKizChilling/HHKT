package implementation;

import dao.ChuyenTau_DAO;
import entity.ChuyenTau;
import service.ChuyenTauService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class ChuyenTauServiceImpl extends UnicastRemoteObject implements ChuyenTauService {
    private ChuyenTau_DAO chuyenTau_DAO;

    public ChuyenTauServiceImpl(ChuyenTau_DAO chuyenTau_DAO) throws RemoteException {
        this.chuyenTau_DAO = chuyenTau_DAO;
    }

    @Override
    public List<ChuyenTau> getAll() throws RemoteException {
        return chuyenTau_DAO.getAll();
    }

    @Override
    public ChuyenTau getChuyenTauTheoID(String soHieuTau) throws RemoteException {
        return chuyenTau_DAO.getChuyenTauTheoID(soHieuTau);
    }

    @Override
    public boolean create(ChuyenTau chuyenTau) throws RemoteException {
        return chuyenTau_DAO.create(chuyenTau);
    }

    @Override
    public boolean update(ChuyenTau chuyenTau) throws RemoteException {
        return chuyenTau_DAO.update(chuyenTau);
    }
} 