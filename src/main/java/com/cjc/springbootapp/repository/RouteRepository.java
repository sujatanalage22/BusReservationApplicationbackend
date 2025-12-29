package com.cjc.springbootapp.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cjc.springbootapp.model.Route;

public interface RouteRepository extends JpaRepository<Route, Long> {

    List<Route> findBySourceAndDestinationAndTravelDate(
        String source,
        String destination,
        LocalDate travelDate
    );
}
