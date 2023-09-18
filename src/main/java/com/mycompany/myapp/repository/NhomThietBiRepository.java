package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.NhomThietBi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NhomThietBiRepository extends JpaRepository<NhomThietBi, Long> {
    public List<NhomThietBi> findAllByLoaiThietBi(String loaiThietBi);
}
