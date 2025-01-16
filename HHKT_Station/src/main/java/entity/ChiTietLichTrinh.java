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

    public static final double GIA_CO_BAN = 500;

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

    public void tinhGiaCho() {
        double giaCho = 0;
        String loaiToa = choNgoi.getToa().getLoaiToa().getMaLoaiToa();
        double khoangCach = lichTrinh.getGaDen().getKhoangCach();

        switch (loaiToa) {
            case "NC" -> {
                if (khoangCach <= 100) {
                    giaCho = GIA_CO_BAN * khoangCach * 1.1;
                } else if (khoangCach <= 250) {
                    giaCho = GIA_CO_BAN * khoangCach * 1.25;
                } else if (khoangCach <= 1000) {
                    giaCho = GIA_CO_BAN * khoangCach * 1.5;
                } else {
                    giaCho = GIA_CO_BAN * khoangCach * 2;
                }
            }
            case "NM" -> {
                if (khoangCach <= 100) {
                    giaCho = GIA_CO_BAN * khoangCach * 1.1 * 1.1;
                } else if (khoangCach <= 250) {
                    giaCho = GIA_CO_BAN * khoangCach * 1.25 * 1.1;
                } else if (khoangCach <= 1000) {
                    giaCho = GIA_CO_BAN * khoangCach * 1.5 * 1.1;
                } else {
                    giaCho = GIA_CO_BAN * khoangCach * 2 * 1.1;
                }
            }
            case "GNK6" -> {
                if (khoangCach <= 100) {
                    giaCho = GIA_CO_BAN * khoangCach * 1.1 * 1.25;
                } else if (khoangCach <= 250) {
                    giaCho = GIA_CO_BAN * khoangCach * 1.25 * 1.25;
                } else if (khoangCach <= 1000) {
                    giaCho = GIA_CO_BAN * khoangCach * 1.5 * 1.25;
                } else {
                    giaCho = GIA_CO_BAN * khoangCach * 2 * 1.25;
                }
            }
            case "GNK4" -> {
                if (khoangCach <= 100) {
                    giaCho = GIA_CO_BAN * khoangCach * 1.1 * 1.5;
                } else if (khoangCach <= 250) {
                    giaCho = GIA_CO_BAN * khoangCach * 1.25 * 1.5;
                } else if (khoangCach <= 1000) {
                    giaCho = GIA_CO_BAN * khoangCach * 1.5 * 1.5;
                } else {
                    giaCho = GIA_CO_BAN * khoangCach * 2 * 1.5;
                }
            }
            case "TVIP" -> {
                if (khoangCach <= 100) {
                    giaCho = GIA_CO_BAN * khoangCach * 1.1 * 2;
                } else if (khoangCach <= 250) {
                    giaCho = GIA_CO_BAN * khoangCach * 1.25 * 2;
                } else if (khoangCach <= 1000) {
                    giaCho = GIA_CO_BAN * khoangCach * 1.5 * 2;
                } else {
                    giaCho = GIA_CO_BAN * khoangCach * 2 * 2;
                }
            }
        }
        this.giaCho = giaCho;
    }

    @Override
    public int hashCode() {
        return Objects.hash(choNgoi, lichTrinh);
    }
}