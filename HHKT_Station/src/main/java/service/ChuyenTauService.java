package service;

import entity.ChuyenTau;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ChuyenTauService extends Remote {
    List<ChuyenTau> getAll() throws RemoteException;

    ChuyenTau getChuyenTauTheoID(String soHieuTau) throws RemoteException;

    boolean create(ChuyenTau chuyenTau) throws RemoteException;

    boolean update(ChuyenTau chuyenTau) throws RemoteException;
} 