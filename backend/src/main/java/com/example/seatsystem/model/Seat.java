package com.example.seatsystem.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

/**
 * Seat 實體類別
 *
 * 對應資料庫中的 seatingchart 資料表，
 * 用於表示每一個座位的靜態資訊（樓層、座位編號等），
 * 並包含一些運算用的暫時屬性（例如 occupied、empId），
 * 這些屬性不會儲存在資料庫中。
 */

@Entity
@Table(name = "seatingchart")
public class Seat {

    @Id
    @Column(name = "FLOOR_SEAT_SEQ")
    private String floorSeatSeq;

    @Column(name = "FLOOR_NO")
    private Integer floorNo;

    @Column(name = "SEAT_NO")
    private Integer seatNo;

    /** 是否已被佔用（非資料庫欄位，動態計算得出） */
    @Transient
    private boolean occupied;

    @Transient
    private String empId;

    // Getter / Setter
    public String getFloorSeatSeq() { return floorSeatSeq; }
    public void setFloorSeatSeq(String floorSeatSeq) { this.floorSeatSeq = floorSeatSeq; }

    public Integer getFloorNo() { return floorNo; }
    public void setFloorNo(Integer floorNo) { this.floorNo = floorNo; }

    public Integer getSeatNo() { return seatNo; }
    public void setSeatNo(Integer seatNo) { this.seatNo = seatNo; }

    
    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {  
        this.empId = empId;
    }
}
