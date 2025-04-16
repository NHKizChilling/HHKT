package implementation;

import dao.LichTrinh_DAO;
import entity.LichTrinh;
import service.LichTrinhService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDate;
import java.util.List;

public class LichTrinhServiceImpl extends UnicastRemoteObject implements LichTrinhService {
    private LichTrinh_DAO lichTrinh_DAO;

    public LichTrinhServiceImpl(LichTrinh_DAO lichTrinh_DAO) throws RemoteException {
        this.lichTrinh_DAO = lichTrinh_DAO;
    }

    @Override
    public List<LichTrinh> getAll() throws RemoteException {
        return lichTrinh_DAO.getAll();
    }

    @Override
    public LichTrinh getLichTrinhTheoID(String maLichTrinh) throws RemoteException {
        return lichTrinh_DAO.getLichTrinhTheoID(maLichTrinh);
    }

    @Override
    public List<LichTrinh> getDSLichTrinhTheoTrangThai(boolean trangThai) throws RemoteException {
        return lichTrinh_DAO.getDSLichTrinhTheoTrangThai(trangThai);
    }

    @Override
    public List<LichTrinh> traCuuDSLichTrinh(String MaGaDi, String MaGaDen) throws RemoteException {
        return lichTrinh_DAO.traCuuDSLichTrinh(MaGaDi, MaGaDen);
    }

    @Override
    public List<LichTrinh> traCuuDSLichTrinh(String MaGaDi, String MaGaDen, LocalDate ngayDi) throws RemoteException {
        return lichTrinh_DAO.traCuuDSLichTrinh(MaGaDi, MaGaDen, ngayDi);
    }

    @Override
    public List<LichTrinh> traCuuDSLichTrinhSauNgayHienTai() throws RemoteException {
        return lichTrinh_DAO.traCuuDSLichTrinhSauNgayHienTai();
    }

    @Override
    public List<LichTrinh> traCuuDSLichTrinhTheoNgay(LocalDate ngayDi) throws RemoteException {
        return lichTrinh_DAO.traCuuDSLichTrinhTheoNgay(ngayDi);
    }

    @Override
    public Long getSoLuongChoConTrong(String maLichTrinh) throws RemoteException {
        return lichTrinh_DAO.getSoLuongChoConTrong(maLichTrinh);
    }

    @Override
    public boolean updateTrangThaiChuyenTau(String maLichTrinh, boolean trangThai) throws RemoteException {
        return lichTrinh_DAO.updateTrangThaiChuyenTau(maLichTrinh, trangThai);
    }

    @Override
    public boolean updateTrangThaiCT(boolean trangThai) throws RemoteException {
        return lichTrinh_DAO.updateTrangThaiCT(trangThai);
    }

    @Override
    public boolean update(LichTrinh lichTrinh) throws RemoteException {
        return lichTrinh_DAO.update(lichTrinh);
    }

    @Override
    public boolean updateInfo(LichTrinh lichTrinh) throws RemoteException {
        return lichTrinh_DAO.updateInfo(lichTrinh);
    }

    @Override
    public boolean create(LichTrinh lichTrinh) throws RemoteException {
        return lichTrinh_DAO.create(lichTrinh);
    }

    @Override
    public LichTrinh getOne(String maLichTrinh) throws RemoteException {
        return lichTrinh_DAO.getOne(maLichTrinh);
    }
} 