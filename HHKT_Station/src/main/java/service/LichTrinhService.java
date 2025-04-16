package service;

import entity.LichTrinh;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface LichTrinhService extends Remote {
    List<LichTrinh> getAll() throws RemoteException;

    LichTrinh getLichTrinhTheoID(String maLichTrinh) throws RemoteException;

    List<LichTrinh> getDSLichTrinhTheoTrangThai(boolean trangThai) throws RemoteException;

    List<LichTrinh> traCuuDSLichTrinh(String MaGaDi, String MaGaDen) throws RemoteException;

    List<LichTrinh> traCuuDSLichTrinh(String MaGaDi, String MaGaDen, LocalDate ngayDi) throws RemoteException;

    List<LichTrinh> traCuuDSLichTrinhSauNgayHienTai() throws RemoteException;

    List<LichTrinh> traCuuDSLichTrinhTheoNgay(LocalDate ngayDi) throws RemoteException;

    Long getSoLuongChoConTrong(String maLichTrinh) throws RemoteException;

    boolean updateTrangThaiChuyenTau(String maLichTrinh, boolean trangThai) throws RemoteException;

    boolean updateTrangThaiCT(boolean trangThai) throws RemoteException;

    boolean update(LichTrinh lichTrinh) throws RemoteException;

    boolean updateInfo(LichTrinh lichTrinh) throws RemoteException;

    boolean create(LichTrinh lichTrinh) throws RemoteException;

    LichTrinh getOne(String maLichTrinh) throws RemoteException;
} 