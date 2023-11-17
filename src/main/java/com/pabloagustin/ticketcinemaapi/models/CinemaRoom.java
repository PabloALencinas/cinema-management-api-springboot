package com.pabloagustin.ticketcinemaapi.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "cinemarooms")
public class CinemaRoom {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(max = 20)
	private String name;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cinemaRoom", cascade = CascadeType.ALL)
	private List<Seat> seats;

	@ManyToMany
	@JoinTable(
			name = "movie_cinemaroom",
			joinColumns = @JoinColumn(name = "cinemaroom_id"),
			inverseJoinColumns = @JoinColumn(name = "movie_id"))
	@JsonBackReference
	private Set<Movie> movies;

	public CinemaRoom(){}

	public CinemaRoom(String name, List<Seat> seats, Set<Movie> movies) {
		this.name = name;
		this.seats = seats;
		this.movies = movies;
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

	public List<Seat> getSeats() {
		return seats;
	}

	public void setSeats(List<Seat> seats) {
		this.seats = seats;
	}

	public Set<Movie> getMovies() {
		return movies;
	}

	public void setMovies(Set<Movie> movies) {
		this.movies = movies;
	}

	public void addSeat(Seat seat) {
		seats.add(seat);
		seat.setCinemaRoom(this);
	}
}
