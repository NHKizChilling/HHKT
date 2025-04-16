package service;

import entity.LoaiToa;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface LoaiToaService extends Remote {
    List<LoaiToa> getAllLoaiToa() throws RemoteException;

    boolean create(LoaiToa loaiToa) throws RemoteException;

    boolean update(LoaiToa loaiToa) throws RemoteException;

    boolean delete(String maLoaiToa) throws RemoteException;

    LoaiToa getLoaiToaTheoMa(String maLoaiToa) throws RemoteException;

    boolean xoaLoaiToaTheoMa(String maLoaiToa) throws RemoteException;
} 