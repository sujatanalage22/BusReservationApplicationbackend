package com.cjc.springbootapp.dto;

import java.time.LocalDateTime;
import java.util.List;

public class BookingResponseDTO {

 
//    private List<String> skippedSeats; // optional info for seats that couldn't be booked
    
        private Long customerId;
        private Long busId;
        private Long routeId;
        private List<Long> seatIds;
        private double totalAmount;
		public Long getCustomerId() {
			return customerId;
		}
		public void setCustomerId(Long customerId) {
			this.customerId = customerId;
		}
		public Long getBusId() {
			return busId;
		}
		public void setBusId(Long busId) {
			this.busId = busId;
		}
		public Long getRouteId() {
			return routeId;
		}
		public void setRouteId(Long routeId) {
			this.routeId = routeId;
		}
		public List<Long> getSeatIds() {
			return seatIds;
		}
		public void setSeatIds(List<Long> seatIds) {
			this.seatIds = seatIds;
		}
		public double getTotalAmount() {
			return totalAmount;
		}
		public void setTotalAmount(double totalAmount) {
			this.totalAmount = totalAmount;
		}}
    


    