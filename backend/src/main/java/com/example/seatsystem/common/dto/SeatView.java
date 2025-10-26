package com.example.seatsystem.common.dto;

public class SeatView {

    private String floorSeatSeq;
    private Integer floorNo;
    private Integer seatNo;
    private boolean occupied;
    private String empId;

    // 🔹 建構子（供 SeatQueryService 使用）
    public SeatView(String floorSeatSeq, Integer floorNo, Integer seatNo, boolean occupied, String empId) {
        this.floorSeatSeq = floorSeatSeq;
        this.floorNo = floorNo;
        this.seatNo = seatNo;
        this.occupied = occupied;
        this.empId = empId;
    }

    // 🔹 Getter & Setter
    public String getFloorSeatSeq() {
        return floorSeatSeq;
    }

    public void setFloorSeatSeq(String floorSeatSeq) {
        this.floorSeatSeq = floorSeatSeq;
    }

    public Integer getFloorNo() {
        return floorNo;
    }

    public void setFloorNo(Integer floorNo) {
        this.floorNo = floorNo;
    }

    public Integer getSeatNo() {
        return seatNo;
    }

    public void setSeatNo(Integer seatNo) {
        this.seatNo = seatNo;
    }

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
