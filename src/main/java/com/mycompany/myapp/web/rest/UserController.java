package com.mycompany.myapp.web.rest;



import com.mycompany.myapp.service.UserServices;
import com.mycompany.myapp.web.rest.request.*;
import com.mycompany.myapp.web.rest.response.*;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;


// ! chưa làm được
// ? Chưa test với front-end
// * Ngăn cách
// ☺ đã Test với front-end
@RestController
@RequestMapping("/api")
@Transactional
public class UserController {
    @Autowired
    private UserServices userServices;
    protected Logger logger;

    //☺ Template login - Chức năng xác thực tài khoản
//    @PostMapping("/login")
//    public ResponseMessage getByName(@RequestBody UserPostRequest request) {
//        ResponseMessage result = userServices.loginAuth(request);
//        return result;
//    }
    //------------------------------------------------ * ---------------------------------------------------------------

    //---------------------------         Template Quản lý thông số      -----------------------------------------------

    //☺ Hiển thị danh sách thông số
    @GetMapping("/quan-ly-thong-so")
    public List<QuanLyThongSoResponse> danhSachThongSo() {
        List<QuanLyThongSoResponse> responseList = this.userServices.danhSachThongSo();

        return responseList;
    }

    //☺ Xoá thông số theo mã thông số
    @DeleteMapping("/quan-ly-thong-so/del-by-ma-thong-so/{maThongSo}")
    public void delByMaThongSo(@PathVariable String maThongSo) {
        this.userServices.delByThongSo(maThongSo);
    }
    //☺ Thêm mới thông số
    @PostMapping("/quan-ly-thong-so/them-moi-thong-so")
    public String postThongSo(@RequestBody List<QuanLyThongSoRequest> requests) {
        String result = this.userServices.postThongSo(requests);
        return result;
    }
    //☺ xem chi tiet thong so
    @GetMapping("/quan-ly-thong-so/chi-tiet-thong-so/{maThongSo}")
    public List<QuanLyThongSoResponse> getChiTietThongSo(@PathVariable String maThongSo){
        List<QuanLyThongSoResponse> responseList = this.userServices.getChiTietThongSo(maThongSo);
        return responseList;
    }

    //☺ cập nhật thông số
    @PutMapping("/quan-ly-thong-so/cap-nhat-thong-so/{maThongSo}")
    public String putThongSo(@PathVariable String maThongSo,
                             @RequestBody QuanLyThongSoRequest request) {
        String result = this.userServices.putThongSo(request, maThongSo);
        return result;
    }
    //☺ su kien tim kiem
    @PostMapping("/quan-ly-thong-so/tim-kiem")
    public List<QuanLyThongSoResponse> timKiemThongSo(@RequestBody QuanLyThongSoRequest request){
        List<QuanLyThongSoResponse> responseList = this.userServices.timKiemThongSo(request);
        return responseList;
    }
    //------------------------------------------------ * ---------------------------------------------------------------

    //--------------------------------             Thiết bị               ---------------------------------------------

    //☺ Hiển thị danh  sách thiết bị
    @GetMapping("/thiet-bi")
    public List<ThietBiResponse> danhSachThietBi() {
        List<ThietBiResponse> responseList = this.userServices.danhSachThietBi();
        return responseList;
    }
    //☺ Tìm kiếm
    @PostMapping("/thiet-bis/tim-kiem")
    public List<ThietBiResponse> getThietBiByStatus(@RequestBody ThietBiRequest request) {
        List<ThietBiResponse> responseList = this.userServices.timKiemThietBi(request);
        System.out.println(request.toString());
        return responseList;
    }
    //----------------------- Chức năng thêm mới thiết bị -----------------------------------------------
    //? thêm mới thiết bị vào DB
    @PostMapping("/thiet-bi/them-moi-thiet-bi")
    public String postThietBi (@RequestBody ThietBiRequest request){
        String result = this.userServices.postThietBi(request);
        return result;
    }
    //☺ del thông số thiết bị ->xoá luôn cả thông số thiết bị
    @DeleteMapping("/thiet-bi/del-thiet-bi/{id}")
    public void delThietBi(@PathVariable Long id){
        this.userServices.delThongSoMay(id);
    }

    //☺ thêm mới thông số thiết bị vào DB
    @PostMapping("/thiet-bi/them-moi-thong-so-thiet-bi")
    public void postThongSoMay(@RequestBody List<ThongSoMayRequest> requestList){
        this.userServices.postThongSoMay(requestList);

    }
    //☺ xem danh sách thông số thiết bị bằng loại thiết bị
    @GetMapping("/thiet-bi/danh-sach-thong-so-thiet-bi/{loaiThietBi}")
    public List<ThongSoMayResponse> getDanhSachThongSoMay(@PathVariable String loaiThietBi){
        List<ThongSoMayResponse> responseList = this.userServices.getDanhSachThongSoThietBiByLoaiThietBi(loaiThietBi);
        return responseList;
    }
    //☺ xem danh sách thông số thiết bị bằng id thiet bi
    @GetMapping("/thiet-bi/thong-so-thiet-bi/thiet-bi-id/{thietBiId}")
    public List<ThongSoMayResponse> getDanhSachThongSoMayById(@PathVariable Long thietBiId){
        List<ThongSoMayResponse> responseList = this.userServices.getDanhSachThongSoThietBiById(thietBiId);
        return responseList;
    }
//      //☺ xem danh sách thông số thiết bị bằng mã thiết bị
//    @GetMapping("/thiet-bi/danh-sach-thong-so-thiet-bi/{maThietBi}")
//    public List<ThongSoMayResponse> getDanhSachThongSoMay(@PathVariable String maThietBi){
//        List<ThongSoMayResponse> responseList = this.userServices.getDanhSachThongSoThietBi(maThietBi);
//        return responseList;
//    }
    //☺ del thông số thiết bị
    @DeleteMapping("/thiet-bi/del-thong-so-may/{idThongSoThietBi}")
    public void delByIdThongSoThietBi(@PathVariable Long idThongSoThietBi){
        this.userServices.delByIdThongSoThietBi(idThongSoThietBi);
    }
    //☺Cập nhật thông số máy
    @PutMapping("/thiet-bi/cap-nhat")
    public void putThongSoMay(@RequestBody List<ThongSoMayRequest> requestList){
        this.userServices.putThongSoMay(requestList);
    }
    //☺xem chi tiết thông số thiet bi
    @GetMapping("/thiet-bi/chi-tiet-thiet-bi/{id}")
    public ThietBiResponse getAllByMaThongSo(@PathVariable Long id){
        ThietBiResponse responseList = this.userServices.getAllById(id);
        return responseList;
    }
    //------------------------------------------------ * ---------------------------------------------------------------

    //---------------------------------------              Kich ban                ------------------------------------

    //☺ Hien thi danh sach kich ban
    @GetMapping("/kich-ban")
    public List<KichBanResponse> getAllKichBan(){
        List<KichBanResponse> responseList = this.userServices.getDanhSachKichBan();
        return responseList;
    }
    //☺ Tim kiem kich ban
    @PostMapping("/kich-bans/tim-kiem")
    public List<KichBanResponse> timKiemKichBan (@RequestBody KichBanRequest request){
        List<KichBanResponse> responseList = this.userServices.timKiemKichBan(request);
        return responseList;
    }
    //☺ Them moi kich ban
    @PostMapping("/kich-ban/them-moi-kich-ban")
    public String postKichBan(@RequestBody KichBanRequest request){
        String result = this.userServices.postKichBan(request);
        return result;
    }
    //☺Them moi thong tin thong so kich ban
    @PostMapping("/kich-ban/them-moi-thong-so-kich-ban")
    public void postChiTietKichBan(@RequestBody List<ChiTietKichBanRequest> requestList){
        this.userServices.postChiTietKichBan(requestList);
    }
    //☺Xem danh sach thong so kich ban theo id kịch bản
    @GetMapping("/kich-ban/thong-so-kich-ban/{kichBanId}")
    public List<ChiTietKichBanResponse> getAllByIdKichBan(@PathVariable Long kichBanId){
        List<ChiTietKichBanResponse> responseList = this.userServices.getAllByIdKichBan(kichBanId);
        return responseList;
    }
    //☺Xem danh sach thong so kich ban theo mã kịch bản
    @GetMapping("/kich-ban/thong-so-kich-ban/ma-kich-ban/{maKichBan}")
    public List<ChiTietKichBanResponse> getAllByMaKichBan(@PathVariable String maKichBan){
        List<ChiTietKichBanResponse> responseList = this.userServices.getAllByMaKichBan(maKichBan);
        return responseList;
    }
    //☺ cap nhat thong so kich ban
    @PutMapping("/kich-ban/cap-nhat-thong-so-kich-ban")
    public void putChiTietKichBan(@RequestBody List<ChiTietKichBanRequest> requestList){
        this.userServices.putChiTietKichBan(requestList);

    }
    //☺ xoa kich ban
    @DeleteMapping("/kich-ban/del-kich-ban/{id}")
    public void delKichBan (@PathVariable Long id){
        this.userServices.delKichBan(id);
    }
    //☺ xoa thong so trong kich ban
    @DeleteMapping("/kich-ban/del-thong-so-kich-ban/{idChiTietKichBan}")
    public  void delByIdChiTietKichBan(@PathVariable Long idChiTietKichBan){
        this.userServices.delByIdChiTietKichBan(idChiTietKichBan);
    }
    //☺  xem chi tiet kich ban
    @GetMapping("/kich-ban/chi-tiet-kich-ban/{id}")
    public KichBanResponse chiTietKichBan(@PathVariable Long id){
        KichBanResponse responseList = this.userServices.chiTietKichBan(id);
        return responseList;
    }
    //☺xem chi tiết kich ban theo ma kich ban
    @GetMapping("/kich-ban/chi-tiet-kich-ban-ma-kich-ban/{maKichban}")
    public KichBanResponse getChiTietByMaKichBan(@PathVariable String maKichban){
        KichBanResponse responseList = this.userServices.chiTietKichBanByMaKichban(maKichban);
        return responseList;
    }
    //-------------------------------------------------- * -------------------------------------------------------------

    //---------------------------                San xuat hang ngay              ---------------------------------------

    // ☺ Hien thi danh sach san xuat hang ngay
    @GetMapping("/san-xuat-hang-ngay")
    public List<SanXuatHangNgayResponse> getAllSanXuatHangNgay (){
        List<SanXuatHangNgayResponse> responseList = this.userServices.getAllSanXuatHangNgay();
        return responseList;
    }
    //☺ Tim kiem noi dung san xuat hang ngay (ok)
    @PostMapping("/san-xuat-hang-ngay/tim-kiem")
    public List<SanXuatHangNgayResponse> timKiemSanXuatHangNgay (@RequestBody SanXuatHangNgayRequest request){
        List<SanXuatHangNgayResponse> responseList = this.userServices.timKiemSanxuatHangNgay(request);
        return responseList;
    }
    // ☺ them moi kich ban san xuat
    @PostMapping("/san-xuat-hang-ngay/them-moi-kich-ban")
    public String postSanXuatHangNgay(@RequestBody SanXuatHangNgayRequest request){
        String result = this.userServices.postSanXuatHangNgay(request);
        return  result;
    }
    //☺Xem danh sach thong so san xuat hang ngay
    @GetMapping("/san-xuat-hang-ngay/chi-tiet-san-xuat/{id}")
    public List<ChiTietSanXuatResponse> getAllsByIdKichBan(@PathVariable Long id){
        List<ChiTietSanXuatResponse> responseList = this.userServices.getAllsById(id);
        return responseList;
    }
    // ☺Chinh sua noi dung san xuat hang ngay (1)
    @PutMapping("/san-xuat-hang-ngay/cap-nhat")
    public void putChiTietSanXuat(@RequestBody List<ChiTietSanXuatRequest> requestList){
        this.userServices.putChiTietSanXuat(requestList);
    }
    // ?(1)xoa thong so trong noi dung san xuat hang ngay
    @DeleteMapping("/san-xuat-hang-ngay/del-thong-so/{idChiTietSanXuat}")
    public void delByIdChiTietSanXuat(@PathVariable Long idChiTietSanXuat){
        this.userServices.delByIdChiTietSanXuat(idChiTietSanXuat);
    }
    // ☺xem chi tiet noi dung 1 kich ban san xuat hang ngay
    @GetMapping("/san-xuat-hang-ngay/chi-tiet/{id}")
    public SanXuatHangNgayResponse chiTietSanXuat(@PathVariable Long id){
        SanXuatHangNgayResponse response = this.userServices.chiTietSanXuat(id);
        return response;
    }
    //☺Them moi thong tin thong so kich ban
    @PostMapping("/san-xuat-hang-ngay/them-moi-thong-so-san-xuat")
    public void postChiTietSanXuat(@RequestBody List<ChiTietSanXuatRequest> requestList){
        this.userServices.postChiTietSanXuat(requestList);
    }
    // ?(1)xoa thong so trong noi dung san xuat hang ngay
    //☺ xoa kich ban
    @DeleteMapping("/san-xuat-hang-ngay/del-kich-ban/{id}")
    public void delSanXuatHangNgay (@PathVariable Long id){
        this.userServices.delSanXuatHangNgay(id);
    }
    //--------------------------- *  ----------------------------------------------------------------
    //------------------------------------------------ Loại thiết bị ---------------------------------------------------
    @GetMapping("/nhom-thiet-bi")
    public List<NhomThietBiResponse> getAllNhomThietBi(){
        List<NhomThietBiResponse> responseList = this.userServices.getAllNhomThietBi();
        return responseList;
    }
    //---------------------------------------------- *  ----------------------------------------------------------------
    //------------------------------------------------ Đơn vị ---------------------------------------------------
    @GetMapping("/don-vi")
    public List<DonViResponse> getAllDonVi(){
        List<DonViResponse> responseList = this.userServices.getAllDonVi();
        return responseList;
    }
}
