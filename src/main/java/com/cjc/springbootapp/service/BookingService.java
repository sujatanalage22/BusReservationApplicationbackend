package com.cjc.springbootapp.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cjc.springbootapp.dto.BookingResponseDTO;
import com.cjc.springbootapp.model.Booking;
import com.cjc.springbootapp.model.Bus;
import com.cjc.springbootapp.model.Customer;
import com.cjc.springbootapp.model.Route;
import com.cjc.springbootapp.model.Seat;
import com.cjc.springbootapp.repository.BookingRepository;
import com.cjc.springbootapp.repository.BusRepository;
import com.cjc.springbootapp.repository.CustomerRepository;
import com.cjc.springbootapp.repository.RouteRepository;
import com.cjc.springbootapp.repository.SeatRepository;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private BusRepository busRepository;

    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private SeatRepository seatRepository;

    // ✅ Save booking from DTO (used by frontend)
    @Transactional
    public Booking saveBooking(BookingResponseDTO dto) {

        Booking booking = new Booking();

        // --- Get customer by userId (frontend sends user.id as customerId)
        Customer customer = customerRepository.findByUserId(dto.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found for user id: " + dto.getCustomerId()));
        booking.setCustomer(customer);

        // --- Get bus
        Bus bus = busRepository.findById(dto.getBusId())
                .orElseThrow(() -> new RuntimeException("Bus not found: " + dto.getBusId()));
        booking.setBus(bus);

        // --- Get route
        Route route = routeRepository.findById(dto.getRouteId())
                .orElseThrow(() -> new RuntimeException("Route not found: " + dto.getRouteId()));
        booking.setRoute(route);

        // --- Get seats
        List<Seat> seats = new ArrayList<>();
        for (Long seatId : dto.getSeatIds()) {
            Seat seat = seatRepository.findById(seatId)
                    .orElseThrow(() -> new RuntimeException("Seat not found: " + seatId));
            seats.add(seat);
        }
        booking.setSeats(seats);

        // --- Set booking details
        booking.setTotalAmount(dto.getTotalAmount());
        booking.setBookingDate(LocalDateTime.now());

        // --- Save and return
        return bookingRepository.save(booking);
    }

    // --- Get bookings by user
    public List<Booking> getBookingsByUser(Long userId) {
        Customer customer = customerRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Customer not found for user id: " + userId));
        return bookingRepository.findByCustomerId(customer.getId());
    }

    // --- Get all bookings (admin)
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }
}
