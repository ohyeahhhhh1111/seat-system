package com.example.seatsystem.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.seatsystem.model.Employee;
import com.example.seatsystem.repository.EmployeeRepository;

@Service
public class EmployeeService {
    private final EmployeeRepository repo;

    public EmployeeService(EmployeeRepository repo) {
        this.repo = repo;
    }

    public List<Employee> getAllEmployees() {
        return repo.findAll();
    }

    public boolean assignSeat(String empId, String floorSeatSeq) {
        try {
            Employee employee = repo.findById(empId).orElse(null);
            if (employee == null) return false;

            employee.setFloorSeatSeq(floorSeatSeq);
            repo.save(employee);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}


