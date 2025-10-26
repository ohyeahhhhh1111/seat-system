package com.example.seatsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.seatsystem.model.Seat;

@Repository
public interface SeatRepository extends JpaRepository<Seat, String> {
    List<Seat> findByFloorNo(Integer floorNo);

    // 呼叫儲存程序：指派座位
    @Modifying
    @Transactional
    @Query(value = "CALL sp_assign_seat(:empId, :seatSeq)", nativeQuery = true)
    void assignSeat(@Param("empId") String empId, @Param("seatSeq") String seatSeq);

    // 呼叫儲存程序：清除座位
    @Modifying
    @Transactional
    @Query(value = "CALL sp_clear_seat(:seatSeq)", nativeQuery = true)
    void clearSeat(@Param("seatSeq") String seatSeq);
}

