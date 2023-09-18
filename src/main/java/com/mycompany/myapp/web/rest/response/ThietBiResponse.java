package com.mycompany.myapp.web.rest.response;


import com.mycompany.myapp.domain.ThongSoMay;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;


public class ThietBiResponse {
    private Long id;
    private String maThietBi;
    private String loaiThietBi;
    private String dayChuyen;
    private ZonedDateTime ngayTao;
    private ZonedDateTime timeUpdate;
    private String updateBy;
    private String status;
    private List<ThongSoMay> thongSoMays;

    public ThietBiResponse() {
    }

    public ThietBiResponse(Long id, String maThietBi, String loaiThietBi, String dayChuyen, ZonedDateTime ngayTao, ZonedDateTime timeUpdate, String updateBy, String status, List<ThongSoMay> thongSoMays) {
        this.id = id;
        this.maThietBi = maThietBi;
        this.loaiThietBi = loaiThietBi;
        this.dayChuyen = dayChuyen;
        this.ngayTao = ngayTao;
        this.timeUpdate = timeUpdate;
        this.updateBy = updateBy;
        this.status = status;
        this.thongSoMays = thongSoMays;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getDayChuyen() {
        return dayChuyen;
    }

    public void setDayChuyen(String dayChuyen) {
        this.dayChuyen = dayChuyen;
    }

    public ZonedDateTime getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(ZonedDateTime ngayTao) {
        this.ngayTao = ngayTao;
    }

    public ZonedDateTime getTimeUpdate() {
        return timeUpdate;
    }

    public void setTimeUpdate(ZonedDateTime timeUpdate) {
        this.timeUpdate = timeUpdate;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ThongSoMay> getThongSoMays() {
        return thongSoMays;
    }

    public void setThongSoMays(List<ThongSoMay> thongSoMays) {
        this.thongSoMays = thongSoMays;
    }
}
