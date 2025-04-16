package service;

import entity.ChiTietHoaDon;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface CT_HoaDonService extends Remote {
    List<ChiTietHoaDon> getAllCT_HoaDon() throws RemoteException;

    ChiTietHoaDon getCT_HoaDon(String maHD, String maVe) throws RemoteException;

    ChiTietHoaDon getCT_HoaDonTheoMaVe(String maVe) throws RemoteException;

    List<ChiTietHoaDon> getCT_HoaDon(String maHD) throws RemoteException;

    boolean create(ChiTietHoaDon cthd) throws RemoteException;

    boolean update(ChiTietHoaDon cthd) throws RemoteException;

    boolean delete(ChiTietHoaDon cthd) throws RemoteException;
} 