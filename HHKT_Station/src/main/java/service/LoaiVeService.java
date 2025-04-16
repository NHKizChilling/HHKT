package service;

import entity.LoaiVe;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface LoaiVeService extends Remote {
    List<LoaiVe> getAllLoaiVe() throws RemoteException;

    boolean create(LoaiVe loaiVe) throws RemoteException;

    boolean update(LoaiVe loaiVe) throws RemoteException;

    boolean delete(String maLoaiVe) throws RemoteException;

    LoaiVe getLoaiVeTheoTen(String tenLoai) throws RemoteException;

    LoaiVe getLoaiVeTheoMa(String maLoaiVe) throws RemoteException;
} 