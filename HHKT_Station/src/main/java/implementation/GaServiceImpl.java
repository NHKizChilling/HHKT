package implementation;

import dao.Ga_DAO;
import entity.Ga;
import service.GaService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class GaServiceImpl extends UnicastRemoteObject implements GaService {
    private Ga_DAO ga_DAO;

    public GaServiceImpl(Ga_DAO ga_DAO) throws RemoteException {
        this.ga_DAO = ga_DAO;
    }

    @Override
    public List<Ga> getAllGa() throws RemoteException {
        return ga_DAO.getAllGa();
    }

    @Override
    public Ga getGaTheoMaGa(String maGa) throws RemoteException {
        return ga_DAO.getGaTheoMaGa(maGa);
    }

    @Override
    public Ga getGaTheoTenGa(String tenGa) throws RemoteException {
        return ga_DAO.getGaTheoTenGa(tenGa);
    }

    @Override
    public double KhoangCach(String maGa) throws RemoteException {
        return ga_DAO.KhoangCach(maGa);
    }

    @Override
    public boolean create(Ga ga) throws RemoteException {
        return ga_DAO.create(ga);
    }

    @Override
    public boolean update(Ga ga) throws RemoteException {
        return ga_DAO.update(ga);
    }

    @Override
    public boolean delete(String maGa) throws RemoteException {
        return ga_DAO.delete(maGa);
    }
} 