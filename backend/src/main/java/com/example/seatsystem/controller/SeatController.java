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

@RestController
@RequestMapping("/api/seats")
@CrossOrigin(origins = "http://localhost:5173")
public class SeatController {

    private final SeatService service;

    public SeatController(SeatService service) {
        this.service = service;
    }

    @GetMapping("/grouped")
    public Map<Integer, List<Seat>> getGroupedSeats() {
        return service.getGroupedSeats();
    }

    @GetMapping
    public List<Seat> getSeats(@RequestParam(required = false) Integer floorNo) {
        return service.getSeats(floorNo);
    }

    @PostMapping("/assign")
    public String assignSeat(@RequestParam String empId, @RequestParam String seatSeq) {
        service.assignSeat(empId, seatSeq);
        return "Seat assigned successfully.";
    }

    // 清除座位
    @PostMapping("/clear")
    public String clearSeat(@RequestParam String seatSeq) {
        service.clearSeat(seatSeq);
        return "Seat cleared successfully.";
    }

}
