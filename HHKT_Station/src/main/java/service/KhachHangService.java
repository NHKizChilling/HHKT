package service;

import entity.KhachHang;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface KhachHangService extends Remote {
    List<KhachHang> getAllKhachHang() throws RemoteException;

    String getAutoGeneratedId() throws RemoteException;

    boolean create(KhachHang khachHang) throws RemoteException;

    boolean update(KhachHang kh) throws RemoteException;

    boolean updateSoSDT(String maKH, String sdt) throws RemoteException;

    boolean delete(String maKH) throws RemoteException;

    KhachHang getKhachHangTheoMaKH(String maKH) throws RemoteException;

    KhachHang getKHTheoCCCD(String soCCCD) throws RemoteException;

    KhachHang getKhachHangTheoSDT(String sdt) throws RemoteException;
} 