package com.cjc.springbootapp.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cjc.springbootapp.dto.RouteCreateRequest;
import com.cjc.springbootapp.model.Bus;
import com.cjc.springbootapp.model.Route;
import com.cjc.springbootapp.repository.BusRepository;
import com.cjc.springbootapp.repository.RouteRepository;

@Service
public class RouteService {

    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private BusRepository busRepository;

    // ADMIN: Add Route
    public Route saveRoute(RouteCreateRequest dto) {

        Bus bus = busRepository.findById(dto.getBusId())
            .orElseThrow(() -> new RuntimeException("Bus not found"));

        Route route = new Route();
        route.setSource(dto.getSource());
        route.setDestination(dto.getDestination());
        route.setTravelDate(dto.getTravelDate());
        route.setDepartureTime(dto.getDepartureTime());
        route.setBus(bus);

        return routeRepository.save(route);
    }

    // ADMIN / USER
    public List<Route> getAllRoutes() {
        return routeRepository.findAll();
    }

    // USER SEARCH
    public List<Route> searchRoutes(String source, String destination, LocalDate travelDate) {
        return routeRepository
            .findBySourceAndDestinationAndTravelDate(source, destination, travelDate);
    }
}
