package service;

import entity.Ve;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface VeService extends Remote {
    List<Ve> getAllVe() throws RemoteException;

    Ve getVeTheoID(String maVe) throws RemoteException;

    Ve getLaiVe() throws RemoteException;

    boolean updateTinhTrangVe(String maVe, String tinhTrangVe) throws RemoteException;

    List<Ve> getDSVeTheoMaKH(String maKH) throws RemoteException;

    List<Ve> getVeTheoTinhTrang(String tinhTrangVe) throws RemoteException;

    boolean create(Ve ve) throws RemoteException;

    boolean update(Ve ve) throws RemoteException;
} 