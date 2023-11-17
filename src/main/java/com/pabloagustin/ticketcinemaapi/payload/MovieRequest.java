package com.pabloagustin.ticketcinemaapi.payload;

import java.time.LocalTime;
import java.util.Set;

public class MovieRequest {


	private String name;
	private String description;

	private Set<LocalTime> availableShowtimes;

	private Set<Long> cinemaroomId;

	public MovieRequest(){}

	public MovieRequest(String name, String description, Set<LocalTime> availableShowtimes, Set<Long> cinemaroomId) {
		this.name = name;
		this.description = description;
		this.availableShowtimes = availableShowtimes;
		this.cinemaroomId = cinemaroomId;
	}

	public Set<Long> getCinemaroomId() {
		return cinemaroomId;
	}

	public void setCinemaroomId(Set<Long> cinemaroomId) {
		this.cinemaroomId = cinemaroomId;
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
}
