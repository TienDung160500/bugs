package com.mycompany.myapp.web.rest.request;



public class ThongSoMayRequest {
    private Long id;
    private Long idThietBi;

    private String maThietBi;
    private String loaiThietBi;
    private Integer rows;
    private String thongSo;
    private String moTa;
    private String status;
    private String phanLoai;

    public ThongSoMayRequest() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdThietBi() {
        return idThietBi;
    }

    public void setIdThietBi(Long idThietBi) {
        this.idThietBi = idThietBi;
    }


    public String getMaThietBi() {
        return maThietBi;
    }

    public void setMaThietBi(String maThietBi) {
        this.maThietBi = maThietBi;
    }

    public String getLoaiThietBi() {
        return loaiThietBi;
    }

    public void setLoaiThietBi(String loaiThietBi) {
        this.loaiThietBi = loaiThietBi;
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

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPhanLoai() {
        return phanLoai;
    }

    public void setPhanLoai(String phanLoai) {
        this.phanLoai = phanLoai;
    }
}
