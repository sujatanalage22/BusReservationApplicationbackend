package com.cjc.springbootapp.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.cjc.springbootapp.model.Seat;
import com.cjc.springbootapp.repository.SeatRepository;
import com.cjc.springbootapp.service.SeatService;

@RestController
@RequestMapping("/api/seats")
//@CrossOrigin(origins = "http://localhost:3000")
public class SeatController {

    @Autowired
    private SeatService seatService;
    @Autowired
    private SeatRepository seatRepository;

    // ✅ ADMIN: Add multiple seats
    @PostMapping("/add")
    public String addSeats(@RequestBody Map<String, Object> payload) {

        Long busId = Long.valueOf(payload.get("busId").toString());
        int seatCount = Integer.parseInt(payload.get("seatCount").toString());

        seatService.addSeats(busId, seatCount);
        return "Seats added successfully";
    }

    // ✅ USER: Fetch available seats
   

        
        

        @GetMapping("/bus/{busId}")
        public List<Seat> getSeatsByBus(@PathVariable Long busId) {
            return seatRepository.findByBusId(busId);
        }
    }

    


