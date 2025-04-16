package implementation;

import dao.ChoNgoi_DAO;
import entity.ChoNgoi;
import entity.LichTrinh;
import service.ChoNgoiService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class ChoNgoiServiceImpl extends UnicastRemoteObject implements ChoNgoiService {
    private ChoNgoi_DAO choNgoi_DAO;

    public ChoNgoiServiceImpl(ChoNgoi_DAO choNgoi_DAO) throws RemoteException {
        this.choNgoi_DAO = choNgoi_DAO;
    }

    @Override
    public List<ChoNgoi> getAllChoNgoi() throws RemoteException {
        return choNgoi_DAO.getAllChoNgoi();
    }

    @Override
    public ChoNgoi getChoNgoiTheoToa(String maToa, int sttCho) throws RemoteException {
        return choNgoi_DAO.getChoNgoiTheoToa(maToa, sttCho);
    }

    @Override
    public ChoNgoi getChoNgoiTheoMa(String maCho) throws RemoteException {
        return choNgoi_DAO.getChoNgoiTheoMa(maCho);
    }

    @Override
    public List<ChoNgoi> getDSChoNgoiTheoToa(String maToa) throws RemoteException {
        return choNgoi_DAO.getDSChoNgoiTheoToa(maToa);
    }

    @Override
    public List<ChoNgoi> getChoNgoiDaDat(LichTrinh lichTrinh) throws RemoteException {
        return choNgoi_DAO.getChoNgoiDaDat(lichTrinh);
    }

    @Override
    public boolean create(ChoNgoi choNgoi) throws RemoteException {
        return choNgoi_DAO.create(choNgoi);
    }

    @Override
    public boolean update(ChoNgoi choNgoi) throws RemoteException {
        return choNgoi_DAO.update(choNgoi);
    }

    @Override
    public boolean delete(String maChoNgoi) throws RemoteException {
        return choNgoi_DAO.delete(maChoNgoi);
    }
} 