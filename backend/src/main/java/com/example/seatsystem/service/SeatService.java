package com.example.seatsystem.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.seatsystem.model.Employee;
import com.example.seatsystem.model.Seat;
import com.example.seatsystem.repository.EmployeeRepository;
import com.example.seatsystem.repository.SeatRepository;

/**
 * SeatService
 *
 * - 提供與「座位」相關的商業邏輯。
 * - 整合員工與座位資料，用於生成可供前端顯示的座位狀態。
 * - 包含呼叫儲存程序進行座位指派與清除的功能。
 */
@Service
public class SeatService {
    private final SeatRepository repo;  //座位資料存取層 (DAO)
    private final EmployeeRepository epSeatrepo; // 員工資料存取層 (DAO) 

    public SeatService(SeatRepository repo, EmployeeRepository epSeatrepo) {
        this.repo = repo;
        this.epSeatrepo = epSeatrepo;
    }

    public Map<Integer, List<Seat>> getGroupedSeats() {
        // 建立座位對應表：floorSeatSeq → empId
        List<Seat> seats = repo.findAll();
        List<Employee> empSeats = epSeatrepo.findAll();

        // 建立座位對應表 floorSeatSeq → empId
        Map<String, String> occupiedMap = empSeats.stream().collect(Collectors.toMap(Employee::getFloorSeatSeq, Employee::getEmpId));

        // 更新每個座位的狀態
        seats.forEach(seat -> {
            if (occupiedMap.containsKey(seat.getFloorSeatSeq())) {
                seat.setOccupied(true);
                seat.setEmpId(occupiedMap.get(seat.getFloorSeatSeq()));
            } else {
                seat.setOccupied(false);
                seat.setEmpId(null);
            }
        });

        // 依樓層分組
        return seats.stream()
                .collect(Collectors.groupingBy(
                        Seat::getFloorNo,
                        LinkedHashMap::new,
                        Collectors.toList()
                ));
    }

    public List<Seat> getSeats(Integer floorNo) {
        if (floorNo == null) floorNo = 1;
        return repo.findByFloorNo(floorNo);
    }

    // 指派座位
    @Transactional
    public void assignSeat(String empId, String seatSeq) {
        repo.assignSeat(empId, seatSeq);
    }

    // 清除座位
    @Transactional
    public void clearSeat(String seatSeq) {
        repo.clearSeat(seatSeq);
    }
}
