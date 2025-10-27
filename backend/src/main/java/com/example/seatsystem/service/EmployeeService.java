package com.example.seatsystem.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.seatsystem.model.Employee;
import com.example.seatsystem.repository.EmployeeRepository;

/**
 * EmployeeService
 *
 * - 負責員工相關的業務邏輯（Business Logic）。
 * - 與資料存取層（EmployeeRepository）互動，對外提供簡潔的方法。
 * - 被 Controller 呼叫以執行實際的後端操作。
 */

@Service // 標示這是一個 Spring 管理的 Service 組件
public class EmployeeService {
    private final EmployeeRepository repo; //員工資料存取物件

    public EmployeeService(EmployeeRepository repo) {
        this.repo = repo;
    }

    public List<Employee> getAllEmployees() {
        return repo.findAll();
    }

    public boolean assignSeat(String empId, String floorSeatSeq) {
        try {
            // 查詢員工是否存在
            Employee employee = repo.findById(empId).orElse(null);
            if (employee == null) return false; // 找不到員工 → 指派失敗

             // 更新座位資訊
            employee.setFloorSeatSeq(floorSeatSeq);
            repo.save(employee);
            return true;
        } catch (Exception e) {
            // 捕捉例外並印出
            e.printStackTrace();
            return false;
        }
    }
}


