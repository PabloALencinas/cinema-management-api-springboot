package com.pabloagustin.ticketcinemaapi.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalTime;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "movies")
public class Movie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(max = 50)
	private String name;

	private String description;

	private Set<LocalTime> availableshowtimes;

	@ManyToMany(mappedBy = "movies")
	private Set<CinemaRoom> cinemaRooms;

	public Movie(){
	}

	public Movie(String name, String description, Set<LocalTime> availableshowtimes, Set<CinemaRoom>  cinemaRooms) {
		this.name = name;
		this.description = description;
		this.availableshowtimes = availableshowtimes;
		this.cinemaRooms = cinemaRooms;
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

	public Set<LocalTime> getAvailableshowtimes() {
		return availableshowtimes;
	}

	public void setAvailableshowtimes(Set<LocalTime> availableshowtimes) {
		this.availableshowtimes = availableshowtimes;
	}

	public Set<CinemaRoom>  getCinemaRooms() {
		return cinemaRooms;
	}

	public void setCinemaRooms(Set<CinemaRoom>  cinemaRooms) {
		this.cinemaRooms = cinemaRooms;
	}
}
