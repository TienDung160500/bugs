package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;

/**
 * A ChiTietKichBan.
 */
@Entity
@Table(name = "chi_tiet_kich_ban")
public class ChiTietKichBan implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "ma_kich_ban")
    private String maKichBan;

    @Column(name = "hang_mkb")
    private Integer hangMkb;

    @Column(name = "thong_so")
    private String thongSo;

    @Column(name = "min_value")
    private Float minValue;

    @Column(name = "max_value")
    private Float maxValue;

    @Column(name = "trungbinh")
    private Float trungbinh;

    @Column(name = "don_vi")
    private String donVi;

    @Column(name = "phan_loai")
    private String phanLoai;

    @ManyToOne
    @JsonIgnoreProperties(value = { "chiTietKichBans" }, allowSetters = true)
    private KichBan kichBan;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public ChiTietKichBan id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaKichBan() {
        return this.maKichBan;
    }

    public ChiTietKichBan maKichBan(String maKichBan) {
        this.setMaKichBan(maKichBan);
        return this;
    }

    public void setMaKichBan(String maKichBan) {
        this.maKichBan = maKichBan;
    }

    public Integer getHangMkb() {
        return this.hangMkb;
    }

    public ChiTietKichBan hangMkb(Integer hangMkb) {
        this.setHangMkb(hangMkb);
        return this;
    }

    public void setHangMkb(Integer hangMkb) {
        this.hangMkb = hangMkb;
    }

    public String getThongSo() {
        return this.thongSo;
    }

    public ChiTietKichBan thongSo(String thongSo) {
        this.setThongSo(thongSo);
        return this;
    }

    public void setThongSo(String thongSo) {
        this.thongSo = thongSo;
    }

    public Float getMinValue() {
        return this.minValue;
    }

    public ChiTietKichBan minValue(Float minValue) {
        this.setMinValue(minValue);
        return this;
    }

    public void setMinValue(Float minValue) {
        this.minValue = minValue;
    }

    public Float getMaxValue() {
        return this.maxValue;
    }

    public ChiTietKichBan maxValue(Float maxValue) {
        this.setMaxValue(maxValue);
        return this;
    }

    public void setMaxValue(Float maxValue) {
        this.maxValue = maxValue;
    }

    public Float getTrungbinh() {
        return this.trungbinh;
    }

    public ChiTietKichBan trungbinh(Float trungbinh) {
        this.setTrungbinh(trungbinh);
        return this;
    }

    public void setTrungbinh(Float trungbinh) {
        this.trungbinh = trungbinh;
    }

    public String getDonVi() {
        return this.donVi;
    }

    public ChiTietKichBan donVi(String donVi) {
        this.setDonVi(donVi);
        return this;
    }

    public void setDonVi(String donVi) {
        this.donVi = donVi;
    }

    public String getPhanLoai() {
        return this.phanLoai;
    }

    public ChiTietKichBan phanLoai(String phanLoai) {
        this.setPhanLoai(phanLoai);
        return this;
    }

    public void setPhanLoai(String phanLoai) {
        this.phanLoai = phanLoai;
    }

    public KichBan getKichBan() {
        return this.kichBan;
    }

    public void setKichBan(KichBan kichBan) {
        this.kichBan = kichBan;
    }

    public ChiTietKichBan kichBan(KichBan kichBan) {
        this.setKichBan(kichBan);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ChiTietKichBan)) {
            return false;
        }
        return id != null && id.equals(((ChiTietKichBan) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ChiTietKichBan{" +
            "id=" + getId() +
            ", maKichBan='" + getMaKichBan() + "'" +
            ", hangMkb=" + getHangMkb() +
            ", thongSo='" + getThongSo() + "'" +
            ", minValue=" + getMinValue() +
            ", maxValue=" + getMaxValue() +
            ", trungbinh=" + getTrungbinh() +
            ", donVi='" + getDonVi() + "'" +
            ", phanLoai='" + getPhanLoai() + "'" +
            "}";
    }
}
