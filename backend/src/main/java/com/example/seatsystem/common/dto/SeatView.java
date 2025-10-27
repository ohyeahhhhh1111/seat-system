package com.example.seatsystem.common.dto;

/**
 * SeatView - 座位檢視資料物件
 *
 * 用於封裝每個座位的顯示資訊，例如樓層、座位編號、是否被佔用、以及目前使用者的員工編號。
 * 
 * - 由 Service（如 SeatQueryService）查詢座位狀態後，回傳給前端顯示。
 */

public class SeatView {

    private String floorSeatSeq;
    private Integer floorNo;
    private Integer seatNo;
    private boolean occupied;
    private String empId;

    public SeatView(String floorSeatSeq, Integer floorNo, Integer seatNo, boolean occupied, String empId) {
        this.floorSeatSeq = floorSeatSeq;
        this.floorNo = floorNo;
        this.seatNo = seatNo;
        this.occupied = occupied; //座位是否已被佔用
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
