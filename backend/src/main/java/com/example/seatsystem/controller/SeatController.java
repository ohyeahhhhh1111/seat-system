package com.example.seatsystem.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.seatsystem.model.Seat;
import com.example.seatsystem.service.SeatService;

/**
 * SeatController
 *
 * 提供座位相關操作的 REST API
 * - 取得所有座位資料
 * - 依樓層分組查詢座位
 * - 指派或清除座位
 *
 * URL 前綴：/api/seats
 */

@RestController
@RequestMapping("/api/seats")
@CrossOrigin(origins = "http://localhost:5173")
public class SeatController {

    private final SeatService service;

    public SeatController(SeatService service) {
        this.service = service;
    }

    @GetMapping("/grouped") //取得所有座位並依樓層分組
    public Map<Integer, List<Seat>> getGroupedSeats() {
        return service.getGroupedSeats();
    }

    @GetMapping //取得座位資料
    public List<Seat> getSeats(@RequestParam(required = false) Integer floorNo) {
        return service.getSeats(floorNo);
    }

    @PostMapping("/assign") //指派座位給指定員工
    public String assignSeat(@RequestParam String empId, @RequestParam String seatSeq) {
        service.assignSeat(empId, seatSeq);
        return "Seat assigned successfully.";
    }

    @PostMapping("/clear") // 清除座位
    public String clearSeat(@RequestParam String seatSeq) {
        service.clearSeat(seatSeq);
        return "Seat cleared successfully.";
    }

}
