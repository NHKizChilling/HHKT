package implementation;

import dao.Ve_DAO;
import dto.VeDTO;
import entity.Ve;
import service.VeService;
import util.VeMapper;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class VeServiceImpl extends UnicastRemoteObject implements VeService {
    private Ve_DAO ve_DAO;

    public VeServiceImpl(Ve_DAO ve_DAO) throws RemoteException {
        this.ve_DAO = ve_DAO;
    }

    @Override
    public List<VeDTO> getAllVe() throws RemoteException {
        return VeMapper.toDTOList(ve_DAO.getAllVe());
    }

    @Override
    public VeDTO getVeTheoID(String maVe) throws RemoteException {
        return VeMapper.toDTO(ve_DAO.getVeTheoID(maVe));
    }

    @Override
    public String getAutoGenerateID() throws RemoteException {
        return ve_DAO.getAutoGenerateID();
    }

    public VeDTO getLaiVe() throws RemoteException {
        return VeMapper.toDTO(ve_DAO.getLaiVe());
    }

    @Override
    public boolean updateTinhTrangVe(String maVe, String tinhTrangVe) throws RemoteException {
        return ve_DAO.updateTinhTrangVe(maVe, tinhTrangVe);
    }

    @Override
    public List<VeDTO> getDSVeTheoMaKH(String maKH) throws RemoteException {
        return VeMapper.toDTOList(ve_DAO.getDSVeTheoMaKH(maKH));
    }

    public List<VeDTO> getVeTheoTinhTrang(String tinhTrangVe) throws RemoteException {
        return VeMapper.toDTOList(ve_DAO.getVeTheoTinhTrang(tinhTrangVe));
    }

    @Override
    public boolean create(VeDTO ve) throws RemoteException {
        return ve_DAO.create(VeMapper.toEntity(ve));
    }

    @Override
    public boolean update(VeDTO ve) throws RemoteException {
        return ve_DAO.update(VeMapper.toEntity(ve));
    }
} 