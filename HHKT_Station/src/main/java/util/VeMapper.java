package util;

import dto.VeDTO;
import entity.*;

import java.util.List;
import java.util.stream.Collectors;

public class VeMapper {

    // Convert from entity to DTO
    public static VeDTO toDTO(Ve ve) {
        if (ve == null) {
            return null;
        }
        return new VeDTO(
                ve.getMaVe(),
                ve.getKhachHang() != null ? ve.getKhachHang().getMaKH() : null,
                ve.getChiTietLichTrinh() != null ? ve.getChiTietLichTrinh().getChoNgoi().getMaCho() : null,
                ve.getChiTietLichTrinh() != null ? ve.getChiTietLichTrinh().getLichTrinh().getMaLichTrinh() : null,
                ve.getLoaiVe() != null ? ve.getLoaiVe().getMaLoaiVe() : null,
                ve.getTenHanhKhach(),
                ve.getSoCCCD(),
                ve.getNgaySinh(),
                ve.getTinhTrangVe(),
                ve.isKhuHoi()
        );
    }

    // Convert from DTO to entity
    public static Ve toEntity(VeDTO veDTO) {
        if (veDTO == null) {
            return null;
        }
        Ve ve = new Ve();
        ve.setMaVe(veDTO.getMaVe());
        // Assuming you have methods to fetch related entities by their IDs
        ChiTietLichTrinh tmp = new ChiTietLichTrinh(new ChoNgoi(veDTO.getMaCho()), new LichTrinh(veDTO.getMaLichTrinh()));
        ve.setChiTietLichTrinh(tmp);
        ve.setKhachHang(new KhachHang(veDTO.getMaKh()));
        ve.setLoaiVe(new LoaiVe(veDTO.getMaLoaiVe()));
        ve.setTenHanhKhach(veDTO.getTenHanhKhach());
        ve.setSoCCCD(veDTO.getSoCCCD());
        ve.setNgaySinh(veDTO.getNgaySinh());
        ve.setTinhTrangVe(veDTO.getTinhTrangVe());
        ve.setKhuHoi(veDTO.isKhuHoi());
        return ve;
    }

    // Convert list of entities to list of DTOs
    public static List<VeDTO> toDTOList(List<Ve> veList) {
        if (veList == null) {
            return null;
        }
        return veList.stream()
                .map(VeMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Convert list of DTOs to list of entities
    public static List<Ve> toEntityList(List<VeDTO> veDTOList) {
        if (veDTOList == null) {
            return null;
        }
        return veDTOList.stream()
                .map(VeMapper::toEntity)
                .collect(Collectors.toList());
    }
}