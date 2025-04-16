package service;

import entity.Ga;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface GaService extends Remote {
    List<Ga> getAllGa() throws RemoteException;

    Ga getGaTheoMaGa(String maGa) throws RemoteException;

    Ga getGaTheoTenGa(String tenGa) throws RemoteException;

    double KhoangCach(String maGa) throws RemoteException;

    boolean create(Ga ga) throws RemoteException;

    boolean update(Ga ga) throws RemoteException;

    boolean delete(String maGa) throws RemoteException;
} 