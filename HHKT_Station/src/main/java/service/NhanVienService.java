package service;

import entity.NhanVien;
import jakarta.persistence.EntityTransaction;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface NhanVienService extends Remote {
    List<NhanVien> getAll() throws RemoteException;

    NhanVien getNhanVien(String maNV) throws RemoteException;

    boolean create(NhanVien nhanVien) throws RemoteException;

    boolean updateInfo(NhanVien nhanVien) throws RemoteException;

    boolean delete(String maNV) throws RemoteException;

    boolean updateTinhTrangCV(String maNV, String tinhTrangCV) throws  RemoteException;

    List<NhanVien> getDSQuanLy() throws RemoteException;

    List<NhanVien> getDSNhanVien() throws RemoteException;

    NhanVien getNhanVienTheoTen(String tenNV) throws RemoteException;

    NhanVien getNhanVienTheoSDT(String sdt) throws RemoteException;
} 