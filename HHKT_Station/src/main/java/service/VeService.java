package service;

import dto.VeDTO;
import entity.Ve;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface VeService extends Remote {
    List<VeDTO> getAllVe() throws RemoteException;

    VeDTO getVeTheoID(String maVe) throws RemoteException;

    String getAutoGenerateID() throws RemoteException;

    VeDTO getLaiVe() throws RemoteException;

    boolean updateTinhTrangVe(String maVe, String tinhTrangVe) throws RemoteException;

    List<VeDTO> getDSVeTheoMaKH(String maKH) throws RemoteException;

    List<VeDTO> getVeTheoTinhTrang(String tinhTrangVe) throws RemoteException;

    boolean create(VeDTO ve) throws RemoteException;

    boolean update(VeDTO ve) throws RemoteException;
} 