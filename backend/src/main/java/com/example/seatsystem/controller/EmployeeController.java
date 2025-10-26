package com.example.seatsystem.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody; // 等一下會解釋這個
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.seatsystem.common.dto.AssignRequest;
import com.example.seatsystem.model.Employee;
import com.example.seatsystem.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin(origins = "http://localhost:5173")
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        return service.getAllEmployees();
    }

    @PostMapping("/assign")
    public ResponseEntity<?> assignSeat(@RequestBody AssignRequest request) {
        boolean success = service.assignSeat(request.getEmpId(), request.getFloorSeatSeq());
        if (success) {
            return ResponseEntity.ok(Map.of("success", true, "message", "Seat assigned successfully"));
        } else {
            return ResponseEntity.status(500).body(Map.of("success", false, "message", "Update failed"));
        }
    }
}
