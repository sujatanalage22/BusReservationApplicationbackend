package com.cjc.springbootapp.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.cjc.springbootapp.dto.RouteCreateRequest;
import com.cjc.springbootapp.dto.RouteSearchRequest;
import com.cjc.springbootapp.model.Route;
import com.cjc.springbootapp.service.RouteService;

@RestController
@RequestMapping("/api/routes")
//@CrossOrigin(origins = "http://localhost:3000")
public class RouteController {

    @Autowired
    private RouteService routeService;

    @PostMapping("/add")
    public Route addRoute(@RequestBody RouteCreateRequest request) {
        return routeService.saveRoute(request);
    }

    @GetMapping
    public List<Route> getRoutes() {
        return routeService.getAllRoutes();
    }

    @PostMapping("/search")
    public List<Route> searchRoutes(@RequestBody RouteSearchRequest request) {
        return routeService.searchRoutes(request.getSource(), request.getDestination(), request.getTravelDate());
    }
}
