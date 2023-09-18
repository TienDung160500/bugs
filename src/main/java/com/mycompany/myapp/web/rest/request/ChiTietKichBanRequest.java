package com.mycompany.myapp.web.rest.request;




public class ChiTietKichBanRequest {
    private Long id;

    private Long idKichBan;

    private String maKichBan;

    private Integer rows;

    private String thongSo;

    private Float minValue;

    private Float maxValue;

    private Float trungbinh;

    private String donVi;

    private String phanLoai;

    public ChiTietKichBanRequest() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdKichBan() {
        return idKichBan;
    }

    public void setIdKichBan(Long idKichBan) {
        this.idKichBan = idKichBan;
    }

    public String getMaKichBan() {
        return maKichBan;
    }

    public void setMaKichBan(String maKichBan) {
        this.maKichBan = maKichBan;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
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

    public Float getTrungbinh() {
        return trungbinh;
    }

    public void setTrungbinh(Float trungbinh) {
        this.trungbinh = trungbinh;
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
