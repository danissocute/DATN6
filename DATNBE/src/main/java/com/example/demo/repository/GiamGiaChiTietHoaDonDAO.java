package com.example.demo.repository;


import com.example.demo.entity.GiamGiaChiTietHoaDon;
import com.example.demo.entity.HoaDon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface GiamGiaChiTietHoaDonDAO extends JpaRepository<GiamGiaChiTietHoaDon, UUID> {

    // Truy vấn để lấy danh sách các hóa đơn đã áp mã chương trình giảm giá tương ứng
    @Query("SELECT ct.hd FROM GiamGiaChiTietHoaDon ct WHERE ct.gghd.id = :GiamGiaHoaDonId")
    List<HoaDon> findHoaDonByChuongTrinhGiamGia(@Param("GiamGiaHoaDonId") UUID GiamGiaHoaDonId);

    // Truy vấn để xóa giảm giá chi tiết hóa đơn bằng cách cung cấp cả hai ID khóa
    @Modifying
    @Query("DELETE FROM GiamGiaChiTietHoaDon ct WHERE ct.hd.id = :hoaDonId AND ct.gghd.id = :giamGiaHoaDonId")
    void deleteByHoaDonIdAndGiamGiaHoaDonId(@Param("hoaDonId") UUID hoaDonId, @Param("giamGiaHoaDonId") UUID giamGiaHoaDonId);
}