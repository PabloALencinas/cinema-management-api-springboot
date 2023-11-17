package com.pabloagustin.ticketcinemaapi.payload;

import java.time.LocalTime;
import java.util.Set;

public class ReservationResponse {

	private Long reservationId;
	private Long userId;
	private LocalTime reservationHour;

	public ReservationResponse(Long id, Long userId, Set<LocalTime> reservationDateHour){}

	public ReservationResponse(Long reservationId, Long userId, LocalTime reservationHour) {
		this.reservationId = reservationId;
		this.userId = userId;
		this.reservationHour = reservationHour;
	}

	public Long getReservationId() {
		return reservationId;
	}

	public void setReservationId(Long reservationId) {
		this.reservationId = reservationId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public LocalTime getReservationHour() {
		return reservationHour;
	}

	public void setReservationHour(LocalTime reservationHour) {
		this.reservationHour = reservationHour;
	}
}
