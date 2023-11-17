package com.pabloagustin.ticketcinemaapi.payload;

import java.time.LocalTime;
import java.util.Set;

public class MovieResponse {
	private Long id;
	private String name;
	private String description;
	private Set<LocalTime> availableShowtimes;
	private Set<Long> cinemaRoomIds;

	public MovieResponse(){}

	public MovieResponse(Long id, String name, String description, Set<LocalTime> availableShowtimes, Set<Long> cinemaRoomIds) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.availableShowtimes = availableShowtimes;
		this.cinemaRoomIds = cinemaRoomIds;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<LocalTime> getAvailableShowtimes() {
		return availableShowtimes;
	}

	public void setAvailableShowtimes(Set<LocalTime> availableShowtimes) {
		this.availableShowtimes = availableShowtimes;
	}

	public Set<Long> getCinemaRoomIds() {
		return cinemaRoomIds;
	}

	public void setCinemaRoomIds(Set<Long> cinemaRoomIds) {
		this.cinemaRoomIds = cinemaRoomIds;
	}
}
