package implementation;

import dao.CaLamViec_DAO;
import entity.CaLamViec;
import service.CaLamViecService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDateTime;
import java.util.List;

public class CaLamViecServiceImpl extends UnicastRemoteObject implements CaLamViecService {
    private CaLamViec_DAO caLamViec_DAO;

    public CaLamViecServiceImpl(CaLamViec_DAO caLamViec_DAO) throws RemoteException {
        this.caLamViec_DAO = caLamViec_DAO;
    }

    @Override
    public List<CaLamViec> getAllCaLamViec() throws RemoteException {
        return caLamViec_DAO.getAllCaLamViec();
    }

    @Override
    public CaLamViec getCaLamViec(String maNV, LocalDateTime gioMoCa) throws RemoteException {
        return caLamViec_DAO.getCaLamViec(maNV, gioMoCa);
    }

    @Override
    public CaLamViec getCaLamViecMoiNhatCuaNhanVien(String maNV) throws RemoteException {
        return caLamViec_DAO.getCaLamViecMoiNhatCuaNhanVien(maNV);
    }

    @Override
    public boolean create(CaLamViec caLamViec) throws RemoteException {
        return caLamViec_DAO.create(caLamViec);
    }

    @Override
    public boolean update(CaLamViec caLamViec) throws RemoteException {
        return caLamViec_DAO.update(caLamViec);
    }

    @Override
    public boolean delete(String maNV, LocalDateTime gioMoCa) throws RemoteException {
        return caLamViec_DAO.delete(maNV, gioMoCa);
    }
} 