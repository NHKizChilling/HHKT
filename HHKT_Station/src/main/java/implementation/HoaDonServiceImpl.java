package implementation;

import dao.HoaDon_DAO;
import entity.HoaDon;
import service.HoaDonService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDate;
import java.util.List;

public class HoaDonServiceImpl extends UnicastRemoteObject implements HoaDonService {
    private HoaDon_DAO hoaDon_DAO;

    public HoaDonServiceImpl(HoaDon_DAO hoaDon_DAO) throws RemoteException {
        this.hoaDon_DAO = hoaDon_DAO;
    }

    @Override
    public List<HoaDon> getAllHoaDon() throws RemoteException {
        return hoaDon_DAO.getAllHoaDon();
    }

    @Override
    public HoaDon getHoaDonTheoMa(String maHoaDon) throws RemoteException {
        return hoaDon_DAO.getHoaDonTheoMa(maHoaDon);
    }

    @Override
    public List<HoaDon> getHoaDonTheoNV(String maNV, LocalDate ngayLap) throws RemoteException {
        return hoaDon_DAO.getHoaDonTheoNV(maNV, ngayLap);
    }

    @Override
    public List<HoaDon> getHoaDonTheoKH(String maKH) throws RemoteException {
        return hoaDon_DAO.getHoaDonTheoKH(maKH);
    }

    @Override
    public List<HoaDon> getHoaDonTheoNV(String maNV) throws RemoteException {
        return hoaDon_DAO.getHoaDonTheoNV(maNV);
    }

    @Override
    public HoaDon getHoaDonVuaTao() throws RemoteException {
        return hoaDon_DAO.getHoaDonVuaTao();
    }

    @Override
    public boolean create(HoaDon hoaDon) throws RemoteException {
        return hoaDon_DAO.create(hoaDon);
    }

    @Override
    public boolean createTempInvoice(HoaDon hoaDon) throws RemoteException {
        return hoaDon_DAO.createTempInvoice(hoaDon);
    }

    @Override
    public List<HoaDon> getDSHDLuuTam() throws RemoteException {
        return hoaDon_DAO.getDSHDLuuTam();
    }

    @Override
    public boolean update(HoaDon hoaDon) throws RemoteException {
        return hoaDon_DAO.update(hoaDon);
    }

    @Override
    public boolean delete(HoaDon hoaDon) throws RemoteException {
        return hoaDon_DAO.delete(hoaDon);
    }

    @Override
    public List<HoaDon> getDSHDTheoNam(String nam) throws RemoteException {
        return hoaDon_DAO.getDSHDTheoNam(nam);
    }

    @Override
    public List<HoaDon> getDSHDTheoThang(int thang, int nam) throws RemoteException {
        return hoaDon_DAO.getDSHDTheoThang(thang, nam);
    }

    @Override
    public List<HoaDon> getDSHDTheoNgay(LocalDate ngayLap) throws RemoteException {
        return hoaDon_DAO.getDSHDTheoNgay(ngayLap);
    }
}