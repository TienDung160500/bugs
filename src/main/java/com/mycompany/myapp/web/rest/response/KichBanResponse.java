package com.mycompany.myapp.web.rest.response;

import com.mycompany.myapp.domain.ChiTietKichBan;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

public class KichBanResponse {
    private Long id;

    private Long kichBanId;
    private String maKichBan;

    private String maThietBi;

    private String loaiThietBi;

    private String dayChuyen;

    private String maSanPham;

    private String versionSanPham;

    private ZonedDateTime ngayTao;

    private ZonedDateTime timeUpdate;

    private String updateBy;

    private String trangThai;
    private List<ChiTietKichBan> chiTietKichBans;

    public KichBanResponse() {
    }

    public Long getKichBanId() {
        return kichBanId;
    }

    public void setKichBanId(Long kichBanId) {
        this.kichBanId = kichBanId;
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

    public String getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public String getVersionSanPham() {
        return versionSanPham;
    }

    public void setVersionSanPham(String versionSanPham) {
        this.versionSanPham = versionSanPham;
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

    public KichBanResponse(Long id, String maKichBan, String maThietBi, String loaiThietBi, String dayChuyen, String maSanPham, String versionSanPham, ZonedDateTime ngayTao, ZonedDateTime timeUpdate, String updateBy, String trangThai, List<ChiTietKichBan> chiTietKichBans) {
        this.id = id;
        this.maKichBan = maKichBan;
        this.maThietBi = maThietBi;
        this.loaiThietBi = loaiThietBi;
        this.dayChuyen = dayChuyen;
        this.maSanPham = maSanPham;
        this.versionSanPham = versionSanPham;
        this.ngayTao = ngayTao;
        this.timeUpdate = timeUpdate;
        this.updateBy = updateBy;
        this.trangThai = trangThai;
        this.chiTietKichBans = chiTietKichBans;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public List<ChiTietKichBan> getChiTietKichBans() {
        return chiTietKichBans;
    }

    public void setChiTietKichBans(List<ChiTietKichBan> chiTietKichBans) {
        this.chiTietKichBans = chiTietKichBans;
    }
}
