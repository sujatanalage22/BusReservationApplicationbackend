package com.cjc.springbootapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cjc.springbootapp.model.Bus;
import com.cjc.springbootapp.model.Seat;
import com.cjc.springbootapp.repository.BusRepository;
import com.cjc.springbootapp.repository.SeatRepository;

@Service
public class SeatService {

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private BusRepository busRepository;

    // ✅ ADMIN: Create seats for a bus
    public void addSeats(Long busId, int seatCount) {

        Bus bus = busRepository.findById(busId)
                .orElseThrow(() -> new RuntimeException("Bus not found"));

        for (int i = 1; i <= seatCount; i++) {
            Seat seat = new Seat();
            seat.setSeatNumber("S" + i);
            seat.setBus(bus);

            // ❌ NO booked logic
            seatRepository.save(seat);
        }
    }

    // ✅ USER: Fetch ALL seats of a bus (SIMPLE)
    public List<Seat> getSeatsByBus(Long busId) {
        return seatRepository.findByBusId(busId);
    }
}
