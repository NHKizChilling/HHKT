package implementation;

import dao.LoaiToa_DAO;
import entity.LoaiToa;
import service.LoaiToaService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class LoaiToaServiceImpl extends UnicastRemoteObject implements LoaiToaService {
    private LoaiToa_DAO loaiToa_DAO;

    public LoaiToaServiceImpl(LoaiToa_DAO loaiToa_DAO) throws RemoteException {
        this.loaiToa_DAO = loaiToa_DAO;
    }

    @Override
    public List<LoaiToa> getAllLoaiToa() throws RemoteException {
        return loaiToa_DAO.getAllLoaiToa();
    }

    @Override
    public boolean create(LoaiToa loaiToa) throws RemoteException {
        return loaiToa_DAO.create(loaiToa);
    }

    @Override
    public boolean update(LoaiToa loaiToa) throws RemoteException {
        return loaiToa_DAO.update(loaiToa);
    }

    @Override
    public boolean delete(String maLoaiToa) throws RemoteException {
        return loaiToa_DAO.delete(maLoaiToa);
    }

    @Override
    public LoaiToa getLoaiToaTheoMa(String maLoaiToa) throws RemoteException {
        return loaiToa_DAO.getLoaiToaTheoMa(maLoaiToa);
    }

    @Override
    public boolean xoaLoaiToaTheoMa(String maLoaiToa) throws RemoteException {
        return loaiToa_DAO.xoaLoaiToaTheoMa(maLoaiToa);
    }
} 