package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ChiTietLichTrinh {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ma_cho", nullable = false)
    private entity.ChoNgoi choNgoi;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ma_lich_trinh", nullable = false)
    private entity.LichTrinh lichTrinh;

    @ColumnDefault("1")
    @Column(name = "trang_thai", nullable = false)
    private boolean trangThai = false;

    @Column(name = "gia_cho", nullable = false, columnDefinition = "money")
    private double giaCho;

    public ChiTietLichTrinh(ChoNgoi choNgoi, LichTrinh lichTrinh) {
        this.choNgoi = choNgoi;
        this.lichTrinh = lichTrinh;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ChiTietLichTrinh that = (ChiTietLichTrinh) o;
        return Objects.equals(choNgoi, that.choNgoi) && Objects.equals(lichTrinh, that.lichTrinh);
    }

    @Override
    public int hashCode() {
        return Objects.hash(choNgoi, lichTrinh);
    }
}