package com.mycompany.myapp.web.rest.response;

public class NhomThietBiResponse {
    private String loaiThietBi;
    private String maThietBi;

    public NhomThietBiResponse(String loaiThietBi, String nhomThietBi) {
        this.loaiThietBi = loaiThietBi;
        this.maThietBi = nhomThietBi;
    }

    public NhomThietBiResponse() {
    }

    public String getLoaiThietBi() {
        return loaiThietBi;
    }

    public void setLoaiThietBi(String loaiThietBi) {
        this.loaiThietBi = loaiThietBi;
    }

    public String getMaThietBi() {
        return maThietBi;
    }

    public void setMaThietBi(String nhomThietBi) {
        this.maThietBi = nhomThietBi;
    }
}
