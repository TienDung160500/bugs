package com.mycompany.myapp.web.rest.response;



public class ChiTietSanXuatResponse {
    private Long id;

    private Integer idSanXuatHangNgay;

    private String maKichBan;

    private Integer rows;

    private String thongSo;

    private Float minValue;

    private Float maxValue;

    private Float trungbinh;

    private String donVi;

    public ChiTietSanXuatResponse() {
    }

    public ChiTietSanXuatResponse(Long id, Integer idSanXuatHangNgay, String maKichBan, Integer rows, String thongSo, Float minValue, Float maxValue, Float trungbinh, String donVi) {
        this.id = id;
        this.idSanXuatHangNgay = idSanXuatHangNgay;
        this.maKichBan = maKichBan;
        this.rows = rows;
        this.thongSo = thongSo;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.trungbinh = trungbinh;
        this.donVi = donVi;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdSanXuatHangNgay() {
        return idSanXuatHangNgay;
    }

    public void setIdSanXuatHangNgay(Integer idSanXuatHangNgay) {
        this.idSanXuatHangNgay = idSanXuatHangNgay;
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
}
