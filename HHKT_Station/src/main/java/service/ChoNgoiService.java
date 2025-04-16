package service;

import entity.ChoNgoi;
import entity.LichTrinh;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ChoNgoiService extends Remote {
    List<ChoNgoi> getAllChoNgoi() throws RemoteException;

    ChoNgoi getChoNgoiTheoToa(String maToa, int sttCho) throws RemoteException;

    ChoNgoi getChoNgoiTheoMa(String maCho) throws RemoteException;

    List<ChoNgoi> getDSChoNgoiTheoToa(String maToa) throws RemoteException;

    List<ChoNgoi> getChoNgoiDaDat(LichTrinh lichTrinh) throws RemoteException;

    boolean create(ChoNgoi choNgoi) throws RemoteException;

    boolean update(ChoNgoi choNgoi) throws RemoteException;

    boolean delete(String maChoNgoi) throws RemoteException;
} 