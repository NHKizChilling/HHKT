package service;

import entity.HoaDon;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.List;

public interface HoaDonService extends Remote {
    List<HoaDon> getAllHoaDon() throws RemoteException;

    HoaDon getHoaDonTheoMa(String maHoaDon) throws RemoteException;

    List<HoaDon> getHoaDonTheoNV(String maNV, LocalDate ngayLap) throws RemoteException;

    List<HoaDon> getHoaDonTheoKH(String maKH) throws RemoteException;

    List<HoaDon> getHoaDonTheoNV(String maNV) throws RemoteException;

    HoaDon getHoaDonVuaTao() throws RemoteException;

    boolean create(HoaDon hoaDon) throws RemoteException;

    boolean createTempInvoice(HoaDon hoaDon) throws RemoteException;

    List<HoaDon> getDSHDLuuTam() throws RemoteException;

    boolean update(HoaDon hoaDon) throws RemoteException;

    boolean delete(HoaDon hoaDon) throws RemoteException;

    List<HoaDon> getDSHDTheoNam(String nam) throws RemoteException;

    List<HoaDon> getDSHDTheoThang(int thang, int nam) throws RemoteException;

    List<HoaDon> getDSHDTheoNgay(LocalDate ngayLap) throws RemoteException;
}
