package com.cjc.springbootapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cjc.springbootapp.model.Seat;

public interface SeatRepository extends JpaRepository<Seat, Long> {
	List<Seat> findByBusId(Long busId);

//    List<Seat> findByBusIdAndBookedFalse(Long busId);
//
//    List<Seat> findByBusId(Long busId);
//    
//
//    Optional<Seat> findBySeatNumberAndBusId(String seatNumber, Long busId);
}
