package com.example.seatsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.seatsystem.model.Employee;

/**
 * EmployeeRepository
 *
 * - 提供與資料庫中 employee 表互動的方法。
 * - 繼承 JpaRepository，可自動取得常用的 CRUD 操作（查詢、儲存、刪除等）。
 * - Spring Data JPA 會自動產生實作類別，因此不需自行撰寫 SQL。
 *
 * 主要繼承方法（來自 JpaRepository）：
 *  - findAll()         ：查詢全部員工
 *  - findById(String)  ：依 empId 查詢員工
 *  - save(Employee)    ：新增或更新員工資料
 *  - deleteById(String)：刪除指定員工
 */

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {
}
