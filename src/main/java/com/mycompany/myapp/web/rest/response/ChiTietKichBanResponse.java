package com.mycompany.myapp.web.rest.response;



public class ChiTietKichBanResponse {
    private Long id;

    private String maKichBan;

    private Integer hangMkb;

    private String thongSo;

    private Float minValue;

    private Float maxValue;

    private Float trungbinh;

    private String donVi;

    private String phanLoai;

    public ChiTietKichBanResponse() {
    }

    public ChiTietKichBanResponse(Long id, String maKichBan, Integer hangMkb, String thongSo, Float minValue, Float maxValue, Float trungbinh, String donVi, String phanLoai) {
        this.id = id;
        this.maKichBan = maKichBan;
        this.hangMkb = hangMkb;
        this.thongSo = thongSo;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.trungbinh = trungbinh;
        this.donVi = donVi;
        this.phanLoai = phanLoai;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaKichBan() {
        return maKichBan;
    }

    public void setMaKichBan(String maKichBan) {
        this.maKichBan = maKichBan;
    }

    public Integer getHangMkb() {
        return hangMkb;
    }

    public void setHangMkb(Integer hangMkb) {
        this.hangMkb = hangMkb;
    }

    public Float getTrungbinh() {
        return trungbinh;
    }

    public void setTrungbinh(Float trungbinh) {
        this.trungbinh = trungbinh;
    }

    public String getThongSo() {
        return thongSo;
    }

    public void setThongSo(String thongSo) {
        this.thongSo = thongSo;
    }

    public Float getMinValue() {
        return minValue;
    }

    public void setMinValue(Float minValue) {
        this.minValue = minValue;
    }

    public Float getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(Float maxValue) {
        this.maxValue = maxValue;
    }


    public String getDonVi() {
        return donVi;
    }

    public void setDonVi(String donVi) {
        this.donVi = donVi;
    }

    public String getPhanLoai() {
        return phanLoai;
    }

    public void setPhanLoai(String phanLoai) {
        this.phanLoai = phanLoai;
    }
}
