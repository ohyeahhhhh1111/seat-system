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

@Service
public class SeatService {
    private final SeatRepository repo;
    private final EmployeeRepository epSeatrepo; // 假設有員工座位表

    public SeatService(SeatRepository repo, EmployeeRepository epSeatrepo) {
        this.repo = repo;
        this.epSeatrepo = epSeatrepo;
    }

    public Map<Integer, List<Seat>> getGroupedSeats() {
        List<Seat> seats = repo.findAll();
        List<Employee> empSeats = epSeatrepo.findAll();

        // 建立座位占用對應表
        Map<String, String> occupiedMap = empSeats.stream().collect(Collectors.toMap(Employee::getFloorSeatSeq, Employee::getEmpId));

        // 填入 occupied 與 empId
        seats.forEach(seat -> {
            if (occupiedMap.containsKey(seat.getFloorSeatSeq())) {
                seat.setOccupied(true);
                seat.setEmpId(occupiedMap.get(seat.getFloorSeatSeq()));
            } else {
                seat.setOccupied(false);
                seat.setEmpId(null);
            }
        });

        // 分樓層
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
