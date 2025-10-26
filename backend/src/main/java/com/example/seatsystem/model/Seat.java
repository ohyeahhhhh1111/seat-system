package com.example.seatsystem.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

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

    public void setEmpId(String empId) {  // ← 這個方法之前可能缺失
        this.empId = empId;
    }
}
