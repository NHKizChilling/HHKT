package service;

import entity.Toa;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ToaService extends Remote {
    List<Toa> getAllToa() throws RemoteException;

    Toa getToaTheoID(String maToa) throws RemoteException;

    List<Toa> getAllToaTheoChuyenTau(String soHieuTau) throws RemoteException;

    boolean create(Toa toa) throws RemoteException;

    boolean update(Toa toa) throws RemoteException;

    boolean delete(String maToa) throws RemoteException;
} 