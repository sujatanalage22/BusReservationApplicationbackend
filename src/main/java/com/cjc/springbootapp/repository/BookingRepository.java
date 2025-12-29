package com.cjc.springbootapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cjc.springbootapp.model.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {
	List<Booking> findByCustomerId(Long customerId);
	
	@Query("SELECT s.seatNumber FROM Booking b JOIN b.seats s WHERE b.bus.id = :busId")
    List<String> findBookedSeatNumbers(@Param("busId") Long busId);


}
