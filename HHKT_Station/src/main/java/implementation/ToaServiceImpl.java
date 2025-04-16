package implementation;

import dao.Toa_DAO;
import entity.Toa;
import service.ToaService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class ToaServiceImpl extends UnicastRemoteObject implements ToaService {
    private Toa_DAO toa_DAO;

    public ToaServiceImpl(Toa_DAO toa_DAO) throws RemoteException {
        this.toa_DAO = toa_DAO;
    }

    @Override
    public List<Toa> getAllToa() throws RemoteException {
        return toa_DAO.getAllToa();
    }

    @Override
    public Toa getToaTheoID(String maToa) throws RemoteException {
        return toa_DAO.getToaTheoID(maToa);
    }

    @Override
    public List<Toa> getAllToaTheoChuyenTau(String soHieuTau) throws RemoteException {
        return toa_DAO.getAllToaTheoChuyenTau(soHieuTau);
    }

    @Override
    public boolean create(Toa toa) throws RemoteException {
        return toa_DAO.create(toa);
    }

    @Override
    public boolean update(Toa toa) throws RemoteException {
        return toa_DAO.update(toa);
    }

    @Override
    public boolean delete(String maToa) throws RemoteException {
        return toa_DAO.delete(maToa);
    }
} 