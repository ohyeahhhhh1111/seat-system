package com.example.seatsystem.common.dto;

/**
 * 員工座位分配請求物件 (AssignRequest)
 *
 * 這個 DTO 用於前端傳送「指派員工座位」的請求時，
 * 後端 Controller 會用它來接收 JSON 內容。
 */

public class AssignRequest {
    private String empId; 
    private String floorSeatSeq;

    public String getEmpId() { return empId; }
    public void setEmpId(String empId) { this.empId = empId; }

    public String getFloorSeatSeq() { return floorSeatSeq; }
    public void setFloorSeatSeq(String floorSeatSeq) { this.floorSeatSeq = floorSeatSeq; }
}