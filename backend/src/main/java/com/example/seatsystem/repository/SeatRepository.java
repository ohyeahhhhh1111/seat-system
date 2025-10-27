package com.example.seatsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.seatsystem.model.Seat;

/**
 * SeatRepository
 *
 * - 提供seatingchart資料表的 CRUD 操作。
 * - 定義了額外的自訂查詢與資料庫儲存程序 (Stored Procedure) 呼叫。
 */
@Repository // Spring Data 的資料存取層 (DAO)
public interface SeatRepository extends JpaRepository<Seat, String> {
    List<Seat> findByFloorNo(Integer floorNo);

    // 呼叫儲存程序：指派座位
    @Modifying // 表示這是「修改型」操作（非查詢）
    @Transactional
    @Query(value = "CALL sp_assign_seat(:empId, :seatSeq)", nativeQuery = true)
    void assignSeat(@Param("empId") String empId, @Param("seatSeq") String seatSeq);

    // 呼叫儲存程序：清除座位
    @Modifying
    @Transactional
    @Query(value = "CALL sp_clear_seat(:seatSeq)", nativeQuery = true)
    void clearSeat(@Param("seatSeq") String seatSeq);
}

