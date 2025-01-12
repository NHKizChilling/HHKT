package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class LoaiVe {
    @Id
    @Column(name = "ma_loai_ve", nullable = false, length = 10)
    private String maLoaiVe;

    @Nationalized
    @Column(name = "ten_loai_ve", nullable = false, length = 50)
    private String tenLoaiVe;

    @Column(name = "muc_giam_gia", nullable = false)
    private float mucGiamGia;

    public LoaiVe(String maLoaiVe) {
        this.maLoaiVe = maLoaiVe;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        LoaiVe loaiVe = (LoaiVe) o;
        return Objects.equals(maLoaiVe, loaiVe.maLoaiVe);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(maLoaiVe);
    }
}