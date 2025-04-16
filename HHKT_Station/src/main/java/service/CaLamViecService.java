package service;

import entity.CaLamViec;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.List;

public interface CaLamViecService extends Remote {
    List<CaLamViec> getAllCaLamViec() throws RemoteException;

    CaLamViec getCaLamViec(String maNV, LocalDateTime gioMoCa) throws RemoteException;

    boolean create(CaLamViec caLamViec) throws RemoteException;

    boolean update(CaLamViec caLamViec) throws RemoteException;

    boolean delete(String maNV, LocalDateTime gioMoCa) throws RemoteException;
} 