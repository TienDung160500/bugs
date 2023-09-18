package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.ChiTietKichBan;
import com.mycompany.myapp.domain.ChiTietSanXuat;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data SQL repository for the ChiTietKichBan entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ChiTietKichBanRepository extends JpaRepository<ChiTietKichBan, Long> {
    //☺Xem danh sach thong so kich ban
    public List<ChiTietKichBan> findAllByMaKichBan(String maKichBan);
    public List<ChiTietKichBan> findAllByKichBanId(Long id);

    @Query(value = "select * from chi_tiet_kich_ban ChiTietKichBan where kich_ban_id = ?1",
    nativeQuery = true)
    public List<ChiTietKichBan> getByKichBanId(Long kichBanId);

    //? xem danh sach thong so kịch bản theo mã kịch bản
    @Query(value = "select * from chi_tiet_kich_ban ChiTietKichBan where ma_kich_ban = ?1",
        nativeQuery = true)
    public List<ChiTietKichBan> getByMaKichBan(String maKichBan);
    @Modifying
    @Query(value = "update chi_tiet_kich_ban ChiTietKichBan set kich_ban_id = ?1 where ma_kich_ban = ?2",
        nativeQuery = true)
    public void updateIdKichBan (Long id, String maThietBi);
}
