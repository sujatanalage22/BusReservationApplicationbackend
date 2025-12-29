package com.cjc.springbootapp.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;





@Entity
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String source;
    private String destination;

    private LocalDate travelDate;
    private LocalTime departureTime;

    @ManyToOne
    @JoinColumn(name = "bus_id")
    private Bus bus;

    // getters & setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getSource() { return source; }
    public void setSource(String source) { this.source = source; }

    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }

    public LocalDate getTravelDate() { return travelDate; }
    public void setTravelDate(LocalDate travelDate) { this.travelDate = travelDate; }

    public LocalTime getDepartureTime() { return departureTime; }
    public void setDepartureTime(LocalTime departureTime) { this.departureTime = departureTime; }

    public Bus getBus() { return bus; }
    public void setBus(Bus bus) { this.bus = bus; }
}
