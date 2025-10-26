package com.example.seatsystem.common.dto;

public class AssignRequest {
    private String empId;       // 🔹 字串
    private String floorSeatSeq; // 或 seatId，如果你前端傳這個

    public String getEmpId() { return empId; }
    public void setEmpId(String empId) { this.empId = empId; }

    public String getFloorSeatSeq() { return floorSeatSeq; }
    public void setFloorSeatSeq(String floorSeatSeq) { this.floorSeatSeq = floorSeatSeq; }
}