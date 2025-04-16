package service;

import entity.ChiTietLichTrinh;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface CT_LichTrinhService extends Remote {
    List<ChiTietLichTrinh> getAllChiTietLichTrinh() throws RemoteException;

    boolean create(ChiTietLichTrinh ctlt) throws RemoteException;

    boolean update(ChiTietLichTrinh ctlt) throws RemoteException;

    boolean updateCTLT(ChiTietLichTrinh ctlt, boolean trangThai) throws RemoteException;

    boolean delete(String maLT, String maCN) throws RemoteException;

    List<ChiTietLichTrinh> getCtltTheoTrangThai(boolean trangThai) throws RemoteException;

    ChiTietLichTrinh getCTLTTheoCN(String maLichTrinh, String maChoNgoi) throws RemoteException;

    boolean getTrangThaiCN(String maLichTrinh, String maCho) throws RemoteException;

    void addChiTietLichTrinh(String maLichTrinh) throws RemoteException;

    List<ChiTietLichTrinh> getCtltTheoMaLichTrinh(String maLichTrinh) throws RemoteException;
} 