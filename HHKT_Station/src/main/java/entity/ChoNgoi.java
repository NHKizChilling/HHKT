package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ChoNgoi {
    @Id
    @Column(name = "ma_cho", nullable = false, length = 15)
    private String maCho;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ma_toa", nullable = false)
    private entity.Toa toa;

    @Column(name = "stt_cho", columnDefinition = "tinyint not null")
    private int sttCho;

    @ColumnDefault("1")
    @Column(name = "tang", columnDefinition = "tinyint")
    private int tang;

    @ColumnDefault("1")
    @Column(name = "khoang", columnDefinition = "tinyint")
    private int khoang;

    public ChoNgoi(String maCho) {
        this.maCho = maCho;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ChoNgoi choNgoi = (ChoNgoi) o;
        return Objects.equals(maCho, choNgoi.maCho);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(maCho);
    }
}