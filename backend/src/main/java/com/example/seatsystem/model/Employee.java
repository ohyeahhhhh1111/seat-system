package com.example.seatsystem.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Employee 實體類別
 *
 * 對應資料庫中的 employee 表，用於儲存員工基本資訊及座位分配狀態。
 */

@Entity // 標示為 JPA 實體類別
@Table(name = "employee")
public class Employee {
    @Id
    @Column(name = "EMP_ID")
    private String empId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "FLOOR_SEAT_SEQ")
    private String floorSeatSeq; // 對應座位編號

    // Getter / Setter
    public String getEmpId() { return empId; }
    public void setEmpId(String empId) { this.empId = empId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getFloorSeatSeq() { return floorSeatSeq; }
    public void setFloorSeatSeq(String floorSeatSeq) { this.floorSeatSeq = floorSeatSeq; }
}
