package com.pabloagustin.ticketcinemaapi.payload;

import com.pabloagustin.ticketcinemaapi.models.Seat;

import java.util.List;

public class CinemaroomRequest {

	private String name;

	private List<Seat> seats;

	public CinemaroomRequest(){}

	public CinemaroomRequest(String name, List<Seat> seats) {
		this.name = name;
		this.seats = seats;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Seat> getSeats() {
		return seats;
	}

	public void setSeats(List<Seat> seats) {
		this.seats = seats;
	}
}
