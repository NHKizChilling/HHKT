package service;

import entity.KhuyenMai;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.List;

public interface KhuyenMaiService extends Remote {
    List<KhuyenMai> getAllKM() throws RemoteException;

    List<KhuyenMai> getKMHienCo() throws RemoteException;

    KhuyenMai getKMTheoMa(String maKM) throws RemoteException;

    KhuyenMai getKMGiamCaoNhat() throws RemoteException;

    List<KhuyenMai> getKMTheoNgay(LocalDate ngay) throws RemoteException;

    boolean themKhuyenMai(KhuyenMai km) throws RemoteException;

    boolean suaKhuyenMai(KhuyenMai km) throws RemoteException;

    boolean xoaKhuyenMai(String maKM) throws RemoteException;

    boolean kichHoatKhuyenMai() throws RemoteException;

    boolean khoaKhuyenMai() throws RemoteException;

    boolean capNhatTrangThaiKM(String maKM, boolean trangThai) throws RemoteException;
} 