package com.mycompany.myapp.service;


import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.repository.*;
import com.mycompany.myapp.web.rest.request.*;
import com.mycompany.myapp.web.rest.response.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

// ! chưa làm được
// ? Chưa test với front-end
// * Ngăn cách
// ☺ đã Test với front-end
@Service
public class UserServices {
    private final Logger log = LoggerFactory.getLogger(UserServices.class);

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private QuanLyThongSoRepository quanLyThongSoRepository;
    @Autowired
    private ThietBiRepository thietBiRepository;
    @Autowired
    private ThongSoMayRepository thongSoMayRepository;

    @Autowired
    private KichBanRepository kichBanRepository;
    @Autowired
    private ChiTietKichBanRepository chiTietKichBanRepository;
    @Autowired
    private SanXuatHangNgayRepository sanXuatHangNgayRepository;
    @Autowired
    private ChiTietSanXuatRepository chiTietSanXuatRepository;
    @Autowired
    private NhomThietBiRepository nhomThietBiRepository;
    @Autowired
    private DonViRepository donViRepository;
    //☺ Template login - Chức năng xác thực tài khoản
//    public ResponseMessage loginAuth(UserPostRequest request) {
//        UserEntity entity = userRepository.getByUserName(request.getUserName());
//        boolean result = entity.getPassword().equals(request.getPassword());
//        log.info("entity: " + entity);
//        System.out.println("request: " + request);
//        if (!result) {
//            log.info("failed");
//            return new ResponseMessage("Tài khoản hoặc mật khẩu bị sai");
//        } else {
//            log.info("success");
//            // gán thời gian cho last_login trong DB
//            entity.setLastLogin(request.getLastLogin());
//            userRepository.save(entity);
//            return new ResponseMessage("Đăng nhập thành công");
//        }
//    }
    //------------------------------------------- * --------------------------------------------------------------------

    //-----------------------                  Template Quản lý thông số                       -------- ----------------

    //☺ Hàm set giá trị cho từng thuộc tính
    private static QuanLyThongSoResponse getQuanLyThongSoResponse(QuanLyThongSo entity) {
        QuanLyThongSoResponse response = new QuanLyThongSoResponse();
        response.setId(entity.getId());
        response.setMaThongSo(entity.getMaThongSo());
        response.setTenThongSo(entity.getTenThongSo());
        response.setMoTa(entity.getMoTa());
        response.setNgayTao(entity.getNgayTao());
        response.setTimeUpdate(entity.getNgayUpdate());
        response.setUpdateBy(entity.getUpdateBy());
        response.setStatus(entity.getStatus());
        return response;
    }

    //☺ Hiển thị danh sách thông số
    public List<QuanLyThongSoResponse> danhSachThongSo() {
        System.out.println("success !");
        List<QuanLyThongSo> entityList = quanLyThongSoRepository.findAll();
        List<QuanLyThongSoResponse> responseList = new ArrayList<>();
        for (QuanLyThongSo entity : entityList) {
            QuanLyThongSoResponse response = getQuanLyThongSoResponse(entity);
            responseList.add(response);
        }
        return responseList;
    }

    //☺ Xoá 1 thông số theo mã thông số
    public void delByThongSo(String maThongSo) {
        List<QuanLyThongSo> entity = quanLyThongSoRepository.findAllByMaThongSo(maThongSo);
        log.info("entity: " + entity);
        if (entity.isEmpty()) {
            log.info("Không tìm thấy thông số");
        } else {
            quanLyThongSoRepository.deleteAll(entity);
            log.info("Xoá thành công !");
        }
    }

    //☺ them moi thong so
    public String postThongSo(List<QuanLyThongSoRequest> requests) {
        for (QuanLyThongSoRequest request : requests) {
            QuanLyThongSo entity = new QuanLyThongSo();
            entity.setMaThongSo(request.getMaThongSo());
            entity.setTenThongSo(request.getTenThongSo());
            entity.setMoTa(request.getMoTa());
            entity.setNgayTao(request.getNgayTao());
            entity.setNgayUpdate(request.getTimeUpdate());
            entity.setUpdateBy(request.getUpdateBy());
            entity.setStatus("deactivate");
            quanLyThongSoRepository.save(entity);
        }
        log.info("Them moi thanh cong");
        return "Thêm mới thành công";
    }
    //☺ xem chi tiet thong so
    public List<QuanLyThongSoResponse> getChiTietThongSo(String maThongSo){
        List<QuanLyThongSo> entities = quanLyThongSoRepository.findAllByMaThongSo(maThongSo);
        List<QuanLyThongSoResponse> responseList = new ArrayList<>();
        for (QuanLyThongSo entity : entities){
            QuanLyThongSoResponse response = getQuanLyThongSoResponse(entity);
            responseList.add(response);
        }
        return responseList;
    }
    //☺ cap nhat thong so
    public String putThongSo(QuanLyThongSoRequest request, String maThongSo) {
        QuanLyThongSo entity = quanLyThongSoRepository.getByMaThongSo(maThongSo);
        entity.setMaThongSo(request.getMaThongSo());
        entity.setTenThongSo(request.getTenThongSo());
        entity.setMoTa(request.getMoTa());
        entity.setNgayUpdate(request.getTimeUpdate());
        entity.setUpdateBy(request.getUpdateBy());
        entity.setStatus(request.getStatus());
        quanLyThongSoRepository.save(entity);
        log.info("Cap nhat thanh cong !");
        return "Cap nhat thanh cong !";
    }

    //☺ su kien tim kiem
    public List<QuanLyThongSoResponse> timKiemThongSo(QuanLyThongSoRequest request) {
        var entities = quanLyThongSoRepository.timKiemThongSo(
                request.getMaThongSo(), request.getTenThongSo(), request.getNgayTao(), request.getTimeUpdate(),
                request.getUpdateBy(), request.getStatus());
        List<QuanLyThongSoResponse> responseList = new ArrayList<>();
        for (QuanLyThongSo entity : entities) {
            QuanLyThongSoResponse response = getQuanLyThongSoResponse(entity);
            log.info("response:   ", response);
            responseList.add(response);
        }
        return responseList;
    }

    //---------------------------------------------- * -----------------------------------------------------------------
    //--------------------------------------------          Thiết bị            ----------------------------------------
    //☺ Hàm set giá trị cho từng thuộc tính
    private static ThietBiResponse getThietBiResponse(ThietBi entity) {
        ThietBiResponse response = new ThietBiResponse();
        response.setId(entity.getId());
        response.setMaThietBi(entity.getMaThietBi());
        response.setLoaiThietBi(entity.getLoaiThietBi());
        response.setDayChuyen(entity.getDayChuyen());
        response.setStatus(entity.getStatus());
        response.setNgayTao(entity.getNgayTao());
        response.setTimeUpdate(entity.getTimeUpdate());
        response.setUpdateBy(entity.getUpdateBy());
        return response;
    }

    //☺ Hiển thị danh sách thiết bị
    public List<ThietBiResponse> danhSachThietBi() {
        List<ThietBi> entities = thietBiRepository.findAll();
        List<ThietBiResponse> responseList = new ArrayList<>();
        for (ThietBi entity : entities) {
            ThietBiResponse response = getThietBiResponse(entity);
            responseList.add(response);
        }
        return responseList;
    }

    //☺ Tìm kiếm
    public List<ThietBiResponse> timKiemThietBi(ThietBiRequest request) {
        List<ThietBi> entities = thietBiRepository.timKiemThietBi(request.getMaThietBi(), request.getLoaiThietBi(),
                request.getDayChuyen(), request.getNgayTao(), request.getTimeUpdate(), request.getUpdateBy(), request.getStatus());
        List<ThietBiResponse> responseList = new ArrayList<>();
        log.info("request: " + request);
        for (ThietBi entity : entities) {
            ThietBiResponse response = getThietBiResponse(entity);
            log.info("tim kiem thiet bi: ", response);
            responseList.add(response);
        }
        return responseList;
    }
    //☺ del thiết bị -> xoá luôn cả thông số thiết bị
    public void delThongSoMay(Long id){
        ThietBi entities = thietBiRepository.findById(id).orElse(null);
        if(entities == null){
            log.info("khong tim thay thiet bi");
        }else {
            List<ThongSoMay> entityList = thongSoMayRepository.findAllByThietBiId(id);
            thongSoMayRepository.deleteAll(entityList);
            thietBiRepository.delete(entities);
            log.info("xoa thanh cong");
        }
    }

    //----------------------- Chức năng thêm mới thiết bị -----------------------------------------------

    //☺ Lấy thông tin loại thiết bị theo mã thiết bị từ table thiết bị
        // ☺ thêm mới thiết bị vào DB
    public String postThietBi(ThietBiRequest request) {
        log.info("them moi thiet bi");
        ThietBi entity = new ThietBi();
        entity.setMaThietBi(request.getMaThietBi());
        entity.setLoaiThietBi(request.getLoaiThietBi());
        entity.setDayChuyen(request.getDayChuyen());
        entity.setNgayTao(request.getNgayTao());
        entity.setTimeUpdate(request.getTimeUpdate());
        entity.setUpdateBy(entity.getUpdateBy());
        entity.setStatus(request.getStatus());
        thietBiRepository.save(entity);
        return "them moi thiet bi thanh cong !";
    }

        //☺ thêm mới thông số thiết b vào DB
    public void postThongSoMay(List<ThongSoMayRequest> requestList) {
        Integer row = 0;
        for (ThongSoMayRequest request : requestList) {
            ThongSoMay entity = new ThongSoMay();
            entity.setMaThietBi(request.getMaThietBi());
            entity.setLoaiThietBi(request.getLoaiThietBi());
            entity.setHangTms(row + 1);
            entity.setThongSo(request.getThongSo());
            entity.setMoTa(request.getMoTa());
            entity.setTrangThai(request.getStatus());
            entity.setPhanLoai(request.getPhanLoai());
            thongSoMayRepository.save(entity);
            thongSoMayRepository.updateIdThietBi(request.getIdThietBi(),request.getMaThietBi());
        }
    }
    //----------------------- Chức năng cập nhật thông số thiết bị -----------------------------------------------
    //☺ xem danh sách thông số thiết bị bằng mã thiết bị
    public List<ThongSoMayResponse> getDanhSachThongSoThietBi(String maThietBi){
        List<ThongSoMay> entities = thongSoMayRepository.findAllByMaThietBi(maThietBi);
        List<ThongSoMayResponse> responseList = new ArrayList<>();
        for (ThongSoMay entity:entities){
            ThongSoMayResponse response = new ThongSoMayResponse();
            response.setId(entity.getId());
            response.setMaThietBi(entity.getMaThietBi());
            response.setLoaiThietBi(entity.getLoaiThietBi());
            response.setThongSo(entity.getThongSo());
            response.setMo_ta(entity.getMoTa());
            response.setPhanLoai(entity.getPhanLoai());
            response.setStatus(entity.getTrangThai());
            responseList.add(response);
        }
        return responseList;
    }
    //☺ xem danh sách thông số thiết bị bằng id
    public List<ThongSoMayResponse> getDanhSachThongSoThietBiById(Long id){
        List<ThongSoMay> entities = thongSoMayRepository.findAllByThietBiId(id);
        List<ThongSoMayResponse> responseList = new ArrayList<>();
        for (ThongSoMay entity:entities){
            ThongSoMayResponse response = new ThongSoMayResponse();
            response.setId(entity.getId());
            response.setMaThietBi(entity.getMaThietBi());
            response.setLoaiThietBi(entity.getLoaiThietBi());
            response.setThongSo(entity.getThongSo());
            response.setMo_ta(entity.getMoTa());
            response.setPhanLoai(entity.getPhanLoai());
            response.setStatus(entity.getTrangThai());
            responseList.add(response);
        }
        return responseList;
    }
    //☺ xem danh sách thông số thiết bị bằng loại thiết bị
    public List<ThongSoMayResponse> getDanhSachThongSoThietBiByLoaiThietBi(String loaiThietBi){
        List<ThongSoMay> entities = thongSoMayRepository.findAllByLoaiThietBi(loaiThietBi);
        List<ThongSoMayResponse> responseList = new ArrayList<>();
        for (ThongSoMay entity:entities){
            ThongSoMayResponse response = new ThongSoMayResponse();
            response.setId(entity.getId());
            response.setMaThietBi(entity.getMaThietBi());
            response.setLoaiThietBi(entity.getLoaiThietBi());
            response.setThongSo(entity.getThongSo());
            response.setMo_ta(entity.getMoTa());
            response.setPhanLoai(entity.getPhanLoai());
            response.setStatus(entity.getTrangThai());
            responseList.add(response);
        }
        return responseList;
    }
    //☺ del thông số thiết bị
    public void delByIdThongSoThietBi(Long idThongSoThietBi) {
        ThongSoMay entity = thongSoMayRepository.findById(idThongSoThietBi).orElse(null);
        if (entity == null) {
            String result = "khong tim thay thong so";
            log.info(result);
        } else {
            thongSoMayRepository.delete(entity);
            String result = "xoa thong so may thanh cong";
            log.info(result);
        }
    }
    //? cập nhật thông số máy trong khi xem danh sách thông số máy
    public void putThongSoMay(List<ThongSoMayRequest> requestList){
        // tìm kiếm thông tin thông số theo id_thong_so_thiet_bi
        for (ThongSoMayRequest request: requestList) {
            ThongSoMay entity = thongSoMayRepository.findById(request.getId()).orElse(null);
            //cập nhật thông số đã có
            if (entity != null) {
                entity.setThongSo(request.getThongSo());
                entity.setMoTa(request.getMoTa());
                entity.setPhanLoai(request.getPhanLoai());
                entity.setTrangThai(request.getStatus());
                thongSoMayRepository.save(entity);
            }else { // Thêm mới thông số chưa có
                ThongSoMay entity1 = new ThongSoMay();
                entity1.setThongSo(request.getThongSo());
                entity1.setMoTa(request.getMoTa());
                entity1.setPhanLoai(request.getPhanLoai());
                entity1.setTrangThai(request.getStatus());
                thongSoMayRepository.save(entity1);
                thongSoMayRepository.updateIdThietBi(request.getIdThietBi(), request.getMaThietBi());
            }
        }
    }
    //☺ xem chi tiết thông số thiet bi
    public  ThietBiResponse getAllById (Long id){
        ThietBi entity = thietBiRepository.getAllById(id);
        ThietBiResponse response = getThietBiResponse(entity);
        response.setThongSoMays(entity.getThongSoMays());
        log.info("entity: "+ entity.getThongSoMays());
        return response;
    }
    //------------------------------------------------ * ---------------------------------------------------------------

    //---------------------------------------              Kịch bản                ------------------------------------

    //☺ Hàm set giá trị cho từng thuộc tính
    private static KichBanResponse getKichBanResponse(KichBan entity) {
        KichBanResponse response = new KichBanResponse();
        response.setId(entity.getId());
        response.setMaKichBan(entity.getMaKichBan());
        response.setMaThietBi(entity.getMaThietBi());
        response.setLoaiThietBi(entity.getLoaiThietBi());
        response.setDayChuyen(entity.getDayChuyen());
        response.setMaSanPham(entity.getMaSanPham());
        response.setVersionSanPham(entity.getVersionSanPham());
        response.setNgayTao(entity.getNgayTao());
        response.setTimeUpdate(entity.getTimeUpdate());
        response.setUpdateBy(entity.getUpdateBy());
        response.setTrangThai(entity.getTrangThai());
        return response;
    }

    //☺ Hien thi danh sach kich ban
    public List<KichBanResponse> getDanhSachKichBan() {
        List<KichBan> entities = kichBanRepository.findAll();
        List<KichBanResponse> responseList = new ArrayList<>();
        for (KichBan entity : entities) {
            KichBanResponse response = getKichBanResponse(entity);
            responseList.add(response);
        }
        return responseList;
    }

    //☺ Tim kiem kich ban
    public List<KichBanResponse> timKiemKichBan(KichBanRequest request) {
        List<KichBan> entities = kichBanRepository.timKiemKichBan(request.getMaKichBan(), request.getMaThietBi(),
                request.getLoaiThietBi(), request.getDayChuyen(), request.getMaSanPham(), request.getVersionSanPham(),
                request.getNgayTao(), request.getTimeUpdate(), request.getUpdateBy(), request.getTrangThai());
        log.info("" + request);
        List<KichBanResponse> responseList = new ArrayList<>();
        for (KichBan entity : entities) {
            System.out.println("thanh cong !: "+entities);
            KichBanResponse response = getKichBanResponse(entity);
            responseList.add(response);
        }
        return responseList;
    }
    //? Them moi kich ban
            //? B1: Thêm mới kịch bản
    public String postKichBan(KichBanRequest request){
        log.info("Them moi kich ban");
        KichBan entity = new KichBan();
        entity.setMaKichBan(request.getMaKichBan());
        entity.setMaThietBi(request.getMaThietBi());
        entity.setLoaiThietBi(request.getLoaiThietBi());
        entity.setDayChuyen(request.getDayChuyen());
        entity.setMaSanPham(request.getMaSanPham());
        entity.setVersionSanPham(request.getVersionSanPham());
        entity.setNgayTao(request.getNgayTao());
        entity.setTimeUpdate(request.getTimeUpdate());
        entity.setUpdateBy(request.getUpdateBy());
        entity.setTrangThai(request.getTrangThai());
        kichBanRepository.save(entity);
        return "Them moi kich ban thanh cong";
    }
        //? B2: Thêm mới thông tin thông số kịch bản
public void postChiTietKichBan(List<ChiTietKichBanRequest> requests){
        for (ChiTietKichBanRequest request:requests){
            ChiTietKichBan entity = new ChiTietKichBan();
            entity.setMaKichBan(request.getMaKichBan());
            entity.setHangMkb(request.getRows());
            entity.setThongSo(request.getThongSo());
            entity.setMinValue(request.getMinValue());
            entity.setMaxValue(request.getMaxValue());
            entity.setTrungbinh(request.getTrungbinh());
            entity.setDonVi(request.getDonVi());
            entity.setPhanLoai(request.getPhanLoai());
            chiTietKichBanRepository.save(entity);
            chiTietKichBanRepository.updateIdKichBan(request.getIdKichBan(),request.getMaKichBan());
        }
}
    //☺ xem danh sach thong so kich ban theo id kịch bản
    public List<ChiTietKichBanResponse> getAllByIdKichBan(Long kichBanId){
        List<ChiTietKichBan> entities = chiTietKichBanRepository.findAllByKichBanId(kichBanId);
        List<ChiTietKichBanResponse> responseList = new ArrayList<>();
        log.info("xem danh sach kich ban");
        for (ChiTietKichBan entity:entities){
            ChiTietKichBanResponse response = new ChiTietKichBanResponse();
            response.setId(entity.getId());
            response.setMaKichBan(entity.getMaKichBan());
            response.setHangMkb(entity.getHangMkb());
            response.setThongSo(entity.getThongSo());
            response.setMinValue(entity.getMinValue());
            response.setMaxValue(entity.getMaxValue());
            response.setTrungbinh(entity.getTrungbinh());
            response.setDonVi(entity.getDonVi());
            response.setPhanLoai(entity.getPhanLoai());
            responseList.add(response);
        }
        return responseList;
    }
    //☺ xem danh sach thong so kich ban theo mã kịch bản
    public List<ChiTietKichBanResponse> getAllByMaKichBan(String maKichBan){
        List<ChiTietKichBan> entities = chiTietKichBanRepository.getByMaKichBan(maKichBan);
        List<ChiTietKichBanResponse> responseList = new ArrayList<>();
        log.info("xem danh sach kich ban");
        for (ChiTietKichBan entity:entities){
            ChiTietKichBanResponse response = new ChiTietKichBanResponse();
            response.setMaKichBan(entity.getMaKichBan());
            response.setHangMkb(entity.getHangMkb());
            response.setThongSo(entity.getThongSo());
            response.setMinValue(entity.getMinValue());
            response.setMaxValue(entity.getMaxValue());
            response.setTrungbinh(entity.getTrungbinh());
            response.setDonVi(entity.getDonVi());
            response.setPhanLoai(entity.getPhanLoai());
            responseList.add(response);
        }
        return responseList;
    }
    //? cap nhat thong so kich ban
    public void putChiTietKichBan(List<ChiTietKichBanRequest> requestList){
        for (ChiTietKichBanRequest request:requestList) {
            ChiTietKichBan entity = chiTietKichBanRepository.findById(request.getId()).orElse(null);
            // cap nhat thong so da co
            if (entity != null) {
                entity.setThongSo(request.getThongSo());
                entity.setMinValue(request.getMinValue());
                entity.setMaxValue(request.getMaxValue());
                entity.setTrungbinh(request.getTrungbinh());
                entity.setDonVi(request.getDonVi());
                chiTietKichBanRepository.save(entity);
            }else { // them moi thong so chua co
                ChiTietKichBan entity1 = new ChiTietKichBan();
                entity1.setMaKichBan(request.getMaKichBan());
                entity1.setThongSo(request.getThongSo());
                entity1.setMinValue(request.getMinValue());
                entity1.setMaxValue(request.getMaxValue());
                entity1.setTrungbinh(request.getTrungbinh());
                entity1.setDonVi(request.getDonVi());
                chiTietKichBanRepository.save(entity1);
                chiTietKichBanRepository.updateIdKichBan(request.getIdKichBan(),request.getMaKichBan());
            }
        }
    }
    // ☺ xoa kich ban
    public void delKichBan(Long id){
        KichBan entities = kichBanRepository.findById(id).orElse(null);
        if (entities == null){
            log.info("khong tim thay kich ban");
        }else {
            // tim kiem thong tin chi tiet kich ban
            List<ChiTietKichBan> entityList = chiTietKichBanRepository.findAllByKichBanId(id);
            chiTietKichBanRepository.deleteAll(entityList);
            kichBanRepository.delete(entities);
            log.info("xoa kich ban thanh cong");
        }
    }
    //☺ xoa thong so trong kich ban
    public void delByIdChiTietKichBan(Long idChiTietKichBan){
        ChiTietKichBan entities = chiTietKichBanRepository.findById(idChiTietKichBan).orElse(null);
        if (entities == null){
            log.info("khong tim thay thong so");
        }else {
            chiTietKichBanRepository.delete(entities);
            log.info("xoa thong so kich ban thanh cong");
        }
    }
    //☺ xem chi tiet kich ban
    public KichBanResponse chiTietKichBan(Long idKichBan){
        KichBan entity = kichBanRepository.getAllById(idKichBan);
        KichBanResponse response = getKichBanResponse(entity);
        response.setChiTietKichBans(entity.getChiTietKichBans());
        log.info("sucess !");
        return  response;
    }
    //☺ xem chi tiet kich ban
    public KichBanResponse chiTietKichBanByMaKichban(String maKichBan){
        KichBan entity = kichBanRepository.findAllByMaKichBan(maKichBan);
        KichBanResponse response = getKichBanResponse(entity);
        response.setChiTietKichBans(entity.getChiTietKichBans());
        log.info("sucess !");
        return  response;
    }

    //----------------------------------------- * ----------------------------------------------------------------------
    //---------------------------                    San xuat hang ngay          ---------------------------------------
    // ☺ Ham set gia tri cho tung thuoc tinh
    public SanXuatHangNgayResponse getSanXuatHangNgayResponse(SanXuatHangNgay entity) {
        SanXuatHangNgayResponse response = new SanXuatHangNgayResponse();
        response.setId(entity.getId());
        response.setMaKichBan(entity.getMaKichBan());
        response.setMaThietBi(entity.getMaThietBi());
        response.setLoaiThietBi(entity.getLoaiThietBi());
        response.setDayChuyen(entity.getDayChuyen());
        response.setMaSanPham(entity.getMaSanPham());
        response.setVersionSanPham(entity.getVersionSanPham());
        response.setNgayTao(entity.getNgayTao());
        response.setTimeUpdate(entity.getTimeUpdate());
        response.setTrangThai(entity.getTrangThai());
        return response;
    }

    // ? Hien thi danh sach san xuat hang ngay
    public List<SanXuatHangNgayResponse> getAllSanXuatHangNgay() {
        List<SanXuatHangNgay> entities = sanXuatHangNgayRepository.findAll();
        List<SanXuatHangNgayResponse> responseList = new ArrayList<>();
        for (SanXuatHangNgay entity : entities) {
            SanXuatHangNgayResponse response = getSanXuatHangNgayResponse(entity);
            responseList.add(response);
        }
        return responseList;
    }

    //? Tim kiem noi dung san xuat hang ngay
    public List<SanXuatHangNgayResponse> timKiemSanxuatHangNgay(SanXuatHangNgayRequest request) {
        List<SanXuatHangNgay> entities = sanXuatHangNgayRepository.timKiemSanXuatHangNgay(request.getMaKichBan(), request.getMaThietBi(),
                request.getLoaiThietBi(), request.getDayChuyen(), request.getMaSanPham(), request.getVersionSanPham(),
                request.getNgayTao(), request.getTimeUpdate(), request.getTrangThai());
        log.info("" + request);
        List<SanXuatHangNgayResponse> responseList = new ArrayList<>();
        for (SanXuatHangNgay entity : entities) {
            log.info("thanh cong");
            SanXuatHangNgayResponse response = getSanXuatHangNgayResponse(entity);
            responseList.add(response);
        }
        return responseList;
    }
    // ? them moi kich ban san xuat
    public String postSanXuatHangNgay(SanXuatHangNgayRequest request){
        // them moi kich ban san xuat hang ngay
        log.info("Them moi kich ban"+ request);
        SanXuatHangNgay entity = new SanXuatHangNgay();
        entity.setMaKichBan(request.getMaKichBan());
        entity.setMaThietBi(request.getMaThietBi());
        entity.setLoaiThietBi(request.getLoaiThietBi());
        entity.setDayChuyen(request.getDayChuyen());
        entity.setMaSanPham(request.getMaSanPham());
        entity.setVersionSanPham(request.getVersionSanPham());
        entity.setNgayTao(request.getNgayTao());
        entity.setTimeUpdate(request.getTimeUpdate());
        entity.setTrangThai(request.getTrangThai());
        sanXuatHangNgayRepository.save(entity);
        // Note lay danh sach thong so theo ma kich ban tu table chi tiet kich ban
        List<ChiTietKichBan> entities = chiTietKichBanRepository.findAllByMaKichBan(request.getMaKichBan());
        List<ChiTietSanXuat> entityList = new ArrayList<>();
        // Note lưu thông tin thông số sản xuất hàng ngày
        for (ChiTietKichBan entity1: entities){
            ChiTietSanXuat entity2 = new ChiTietSanXuat();
            entity2.setMaKichBan(entity1.getMaKichBan());
            entity2.setHangSxhn(entity1.getHangMkb());
            entity2.setThongSo(entity1.getThongSo());
            entity2.setMinValue(entity1.getMinValue());
            entity2.setMaxValue(entity1.getMaxValue());
            entity2.setTrungbinh(entity1.getTrungbinh());
            entity2.setDonVi(entity1.getDonVi());
            chiTietSanXuatRepository.save(entity2);
            chiTietSanXuatRepository.updateIdSanXuatHangNgay(request.getIdSanXuatHangNgay(),request.getMaKichBan());

        }
        return "Them moi kich ban thanh cong";
    }
    //? B2: Thêm mới thông tin thông số kịch bản sản xuất
    public void postChiTietSanXuat(List<ChiTietSanXuatRequest> requests){
        for (ChiTietSanXuatRequest request:requests){
            ChiTietSanXuat entity = new ChiTietSanXuat();
            entity.setMaKichBan(request.getMaKichBan());
            entity.setHangSxhn(request.getRows());
            entity.setThongSo(request.getThongSo());
            entity.setMinValue(request.getMinValue());
            entity.setMaxValue(request.getMaxValue());
            entity.setTrungbinh(request.getTrungbinh());
            entity.setDonVi(request.getDonVi());
            chiTietSanXuatRepository.save(entity);
            chiTietSanXuatRepository.updateIdSanXuatHangNgay(request.getIdSanXuatHangNgay(),request.getMaKichBan());
        }
    }
    //? xem danh sach thong so san xuat hang ngay theo id san xuat hang ngay
    public List<ChiTietSanXuatResponse> getAllsById(Long id){
        List<ChiTietSanXuat> entities = chiTietSanXuatRepository.findAllBySanXuatHangNgayId(id);
        List<ChiTietSanXuatResponse> responseList = new ArrayList<>();
        log.info("xem danh sach kich ban");
        for (ChiTietSanXuat entity:entities){
            ChiTietSanXuatResponse response = new ChiTietSanXuatResponse();
            response.setId(entity.getId());
            response.setMaKichBan(entity.getMaKichBan());
            response.setRows(entity.getHangSxhn());
            response.setThongSo(entity.getThongSo());
            response.setMinValue(entity.getMinValue());
            response.setMaxValue(entity.getMaxValue());
            response.setTrungbinh(entity.getTrungbinh());
            response.setDonVi(entity.getDonVi());
            responseList.add(response);
        }
        return responseList;
    }
    //? Cap nhat noi dung san xuat hang ngay (1)
    public void putChiTietSanXuat(List<ChiTietSanXuatRequest> requestList){
        for (ChiTietSanXuatRequest request :requestList) {
            ChiTietSanXuat entity = chiTietSanXuatRepository.findById(request.getId()).orElse(null);
            if (entity != null) {
                entity.setThongSo(request.getThongSo());
                entity.setMinValue(request.getMinValue());
                entity.setMaxValue(request.getMaxValue());
                entity.setTrungbinh(request.getTrungbinh());
                entity.setDonVi(request.getDonVi());
                chiTietSanXuatRepository.save(entity);
            }else {
                ChiTietSanXuat entity1 = new ChiTietSanXuat();
                entity1.setMaKichBan(request.getMaKichBan());
                entity1.setThongSo(request.getThongSo());
                entity1.setMinValue(request.getMinValue());
                entity1.setMaxValue(request.getMaxValue());
                entity1.setTrungbinh(request.getTrungbinh());
                entity1.setDonVi(request.getDonVi());
                chiTietSanXuatRepository.save(entity1);
                chiTietSanXuatRepository.updateIdSanXuatHangNgay(request.getId(),request.getMaKichBan());
            }
        }
    }
    // ? (1)xoa thong so trong noi dung san xuat hang ngay
    public void delByIdChiTietSanXuat(Long idChiTietSanXuat){
        ChiTietSanXuat entity = chiTietSanXuatRepository.findById(idChiTietSanXuat).orElse(null);
        if (entity == null){
            log.info("khong tim thay thong so");
        }else {
            chiTietSanXuatRepository.delete(entity);
            log.info("xoa thong so thanh cong");
        }
    }
    //? xem chi tiet noi dung 1 kich ban san xuat hang ngay
    public SanXuatHangNgayResponse chiTietSanXuat (Long maKichBan){
        SanXuatHangNgay entity = sanXuatHangNgayRepository.findById(maKichBan).orElse(null);
        SanXuatHangNgayResponse response = getSanXuatHangNgayResponse(entity);
        response.setChiTietSanXuat((List<ChiTietSanXuat>) entity.getChiTietSanXuats());
        log.info("thanh cong");
        return response;
    }
    // ☺ xoa kich ban trong sasn xuat hang ngay
    public void delSanXuatHangNgay(Long id){
        SanXuatHangNgay entities = sanXuatHangNgayRepository.findById(id).orElse(null);
        if (entities == null){
            log.info("khong tim thay kich ban");
        }else {
            // tim kiem thong tin chi tiet kich ban
            List<ChiTietSanXuat> entityList = chiTietSanXuatRepository.findAllBySanXuatHangNgayId(id);
            chiTietSanXuatRepository.deleteAll(entityList);
            sanXuatHangNgayRepository.delete(entities);
            log.info("xoa kich ban thanh cong");
        }
    }
    //------------------------------------------------------ *  --------------------------------------------------------
    //------------------------------------------------ Loại thiết bị ---------------------------------------------------
    public List<NhomThietBiResponse> getAllNhomThietBi(){
        List<NhomThietBi> nhomThietBis = this.nhomThietBiRepository.findAll();
        List<NhomThietBiResponse> responseList = new ArrayList<>();
        for(NhomThietBi nhomThietBi :nhomThietBis){
            NhomThietBiResponse response = new NhomThietBiResponse();
            response.setLoaiThietBi(nhomThietBi.getLoaiThietBi());
            response.setMaThietBi(nhomThietBi.getMaThietBi());
            responseList.add(response);
        }
        return responseList;
    }
    //------------------------------------------------------ *  --------------------------------------------------------
    //------------------------------------------------ Don vi---------------------------------------------------
    public List<DonViResponse> getAllDonVi (){
        List<DonVi> donVis = this.donViRepository.findAll();
        List<DonViResponse> responseList = new ArrayList<>();
        for (DonVi donVi :donVis){
            DonViResponse response = new DonViResponse();
            response.setDonVi(donVi.getDonVi());
            responseList.add(response);
        }
        return responseList;
    }
}
