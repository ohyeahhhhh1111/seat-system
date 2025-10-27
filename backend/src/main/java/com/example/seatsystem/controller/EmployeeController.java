package com.example.seatsystem.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody; 
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.seatsystem.common.dto.AssignRequest;
import com.example.seatsystem.model.Employee;
import com.example.seatsystem.service.EmployeeService;

/**
 * EmployeeController
 *
 * 提供員工相關的 API 端點，例如：
 * - 取得所有員工清單
 * - 指派座位給員工
 *
 * URL 前綴：/api/employees
 */

@RestController //RESTful Controller，會自動將回傳物件轉成 JSON。
@RequestMapping("/api/employees") // 所有路徑都會以 /api/employees 開頭
@CrossOrigin(origins = "http://localhost:5173") // 允許前端跨域存取
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping //請求所有員工
    public List<Employee> getAllEmployees() {
        return service.getAllEmployees();
    }

    @PostMapping("/assign") //指派座位給指定員工
    public ResponseEntity<?> assignSeat(@RequestBody AssignRequest request) {
        boolean success = service.assignSeat(request.getEmpId(), request.getFloorSeatSeq());
        if (success) {
            return ResponseEntity.ok(Map.of("success", true, "message", "Seat assigned successfully"));
        } else {
            return ResponseEntity.status(500).body(Map.of("success", false, "message", "Update failed"));
        }
    }
}
