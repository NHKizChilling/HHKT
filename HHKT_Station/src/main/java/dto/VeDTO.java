package dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * DTO for {@link entity.Ve}
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class VeDTO implements Serializable {
    private String maVe;
    private String maKh; // ID of the customer instead of the full KhachHang object
    private String maCho; // Part of the composite key for ChiTietLichTrinh
    private String maLichTrinh; // Part of the composite key for ChiTietLichTrinh
    private String maLoaiVe; // ID of the ticket type instead of the full LoaiVe object
    private String tenHanhKhach;
    private String soCCCD;
    private LocalDate ngaySinh;
    private String tinhTrangVe;
    private boolean khuHoi;

    // Constructor with only maVe for reference purposes
    public VeDTO(String maVe) {
        this.maVe = maVe;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VeDTO veDTO = (VeDTO) o;
        return Objects.equals(maVe, veDTO.maVe);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maVe);
    }
}