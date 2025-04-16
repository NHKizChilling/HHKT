package service;

import entity.TaiKhoan;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface TaiKhoanService extends Remote {
    List<TaiKhoan> getAllTaiKhoan() throws RemoteException;

    boolean create(TaiKhoan tk) throws RemoteException;

    boolean delete(String maNhanVien) throws RemoteException;

    boolean update(TaiKhoan tk) throws RemoteException;

    boolean doiMatKhau(String maNv, String matKhauMoi) throws RemoteException;

    TaiKhoan getTaiKhoanTheoMaNV(String maNhanVien) throws RemoteException;
}
