package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import java.io.Serializable;
import java.util.Objects;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class LoaiToa implements Serializable {
    @Id
    @Column(name = "ma_loai_toa", nullable = false, length = 5)
    private String maLoaiToa;

    @Nationalized
    @Column(name = "ten_loai_toa", nullable = false, length = 30)
    private String tenLoaiToa;

    public LoaiToa(String maLoaiToa) {
        this.maLoaiToa = maLoaiToa;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        LoaiToa loaiToa = (LoaiToa) o;
        return Objects.equals(maLoaiToa, loaiToa.maLoaiToa);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(maLoaiToa);
    }
}