package com.cjc.springbootapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cjc.springbootapp.model.Bus;
import com.cjc.springbootapp.model.Seat;
import com.cjc.springbootapp.repository.BusRepository;
import com.cjc.springbootapp.repository.SeatRepository;

@Service
public class BusService {

    @Autowired
    private BusRepository busRepository;

    @Autowired
    private SeatRepository seatRepository;

    public Bus saveBus(Bus bus) {
        Bus savedBus = busRepository.save(bus);

        // Auto-create seats
        for (int i = 1; i <= bus.getTotalSeats(); i++) {
            Seat seat = new Seat();
            seat.setSeatNumber("S" + i);
            seat.setBus(savedBus);
            seatRepository.save(seat);
        }
        return savedBus;
    }

    public List<Bus> getAllBuses() {
        return busRepository.findAll();
    }
}
