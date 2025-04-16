package implementation;

import dao.LoaiVe_DAO;
import entity.LoaiVe;
import service.LoaiVeService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class LoaiVeServiceImpl extends UnicastRemoteObject implements LoaiVeService {
    private LoaiVe_DAO loaiVe_DAO;

    public LoaiVeServiceImpl(LoaiVe_DAO loaiVe_DAO) throws RemoteException {
        this.loaiVe_DAO = loaiVe_DAO;
    }

    @Override
    public List<LoaiVe> getAllLoaiVe() throws RemoteException {
        return loaiVe_DAO.getAllLoaiVe();
    }

    @Override
    public boolean create(LoaiVe loaiVe) throws RemoteException {
        return loaiVe_DAO.create(loaiVe);
    }

    @Override
    public boolean update(LoaiVe loaiVe) throws RemoteException {
        return loaiVe_DAO.update(loaiVe);
    }

    @Override
    public boolean delete(String maLoaiVe) throws RemoteException {
        return loaiVe_DAO.delete(maLoaiVe);
    }

    @Override
    public LoaiVe getLoaiVeTheoTen(String tenLoai) throws RemoteException {
        return loaiVe_DAO.getLoaiVeTheoTen(tenLoai);
    }

    @Override
    public LoaiVe getLoaiVeTheoMa(String maLoaiVe) throws RemoteException {
        return loaiVe_DAO.getLoaiVeTheoMa(maLoaiVe);
    }
} 