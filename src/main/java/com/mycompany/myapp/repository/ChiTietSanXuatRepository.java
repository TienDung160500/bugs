package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.ChiTietKichBan;
import com.mycompany.myapp.domain.ChiTietSanXuat;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data SQL repository for the ChiTietSanXuat entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ChiTietSanXuatRepository extends JpaRepository<ChiTietSanXuat, Long> {
    //☺ Xem danh sach thong so san xuat hang ngay
    public List<ChiTietSanXuat> findAllByMaKichBan(String maKichban);
    @Modifying
    @Query(value = "update chi_tiet_san_xuat ChiTietSanXuat set san_xuat_hang_ngay_id = ?1 where ma_kich_ban = ?2",
        nativeQuery = true)
    public void updateIdSanXuatHangNgay (Long id, String maThietBi);
    public List<ChiTietSanXuat> findAllBySanXuatHangNgayId(Long id);
}
