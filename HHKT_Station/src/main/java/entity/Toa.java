package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Toa {
    @Id
    @Column(name = "ma_toa", nullable = false, length = 10)
    private String maToa;

    @Column(name = "stt_toa", columnDefinition = "tinyint not null")
    private int sttToa;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "so_hieu_tau", nullable = false)
    private ChuyenTau soHieuTau;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ma_loai_toa", nullable = false)
    private LoaiToa loaiToa;

    public Toa(String maToa) {
        this.maToa = maToa;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Toa toa = (Toa) o;
        return Objects.equals(maToa, toa.maToa);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(maToa);
    }
}