package service;

import entity.LoaiTau;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface LoaiTauService extends Remote {
    List<LoaiTau> getAllLoaiTau() throws RemoteException;

    boolean create(LoaiTau loaiTau) throws RemoteException;

    boolean update(LoaiTau loaiTau) throws RemoteException;

    boolean delete(String maLoaiTau) throws RemoteException;

    LoaiTau getLoaiTauTheoMa(String maLoaiTau) throws RemoteException;
} 