package com.mycompany.myapp.web.rest.response;


public class ThongSoMayResponse {
    private Long id;
    private Long idThietBi;
    private String maThietBi;
    private String loaiThietBi;
    private Long rows;
    private String thongSo;
    private String mo_ta;
    private String status;
    private String phanLoai;

    public ThongSoMayResponse() {
    }

    public ThongSoMayResponse(Long id, Long idThietBi, String maThietBi, String loaiThietBi, Long rows, String thongSo, String mo_ta, String status, String phanLoai) {
        this.id = id;
        this.idThietBi = idThietBi;
        this.maThietBi = maThietBi;
        this.loaiThietBi = loaiThietBi;
        this.rows = rows;
        this.thongSo = thongSo;
        this.mo_ta = mo_ta;
        this.status = status;
        this.phanLoai = phanLoai;
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

    public Long getRows() {
        return rows;
    }

    public void setRows(Long rows) {
        this.rows = rows;
    }

    public String getThongSo() {
        return thongSo;
    }

    public void setThongSo(String thongSo) {
        this.thongSo = thongSo;
    }

    public String getMo_ta() {
        return mo_ta;
    }

    public void setMo_ta(String mo_ta) {
        this.mo_ta = mo_ta;
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
