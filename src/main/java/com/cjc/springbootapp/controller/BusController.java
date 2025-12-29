package com.cjc.springbootapp.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.cjc.springbootapp.model.Bus;
import com.cjc.springbootapp.service.BusService;

@RestController
@RequestMapping("/api/buses")
@CrossOrigin(origins = "http://localhost:3000")
public class BusController {

    @Autowired
    private BusService busService;

    @PostMapping
    public Bus addBus(@RequestBody Bus bus) {
        return busService.saveBus(bus);
    }

    @GetMapping
    public List<Bus> getAllBuses() {
        return busService.getAllBuses();
    }
}
