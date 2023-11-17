package com.pabloagustin.ticketcinemaapi.models;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "reservations")
public class Reservation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User userClient;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "movie_id")
	private Movie movieReservation;

//	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Set<LocalTime> reservationDateHour;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "reservation", cascade = CascadeType.ALL)
	private List<Seat> seats;

	public Reservation(){}

	public Reservation(User userClient,
	                   Movie movieReservation,
	                   Set<LocalTime> reservationDateHour,
	                   List<Seat>seats) {
		this.userClient = userClient;
		this.movieReservation = movieReservation;
		this.reservationDateHour = reservationDateHour;
		this.seats = seats;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUserClient() {
		return userClient;
	}

	public void setUserClient(User userClient) {
		this.userClient = userClient;
	}

	public Movie getMovieReservation() {
		return movieReservation;
	}

	public void setMovieReservation(Movie movieReservation) {
		this.movieReservation = movieReservation;
	}

	public Set<LocalTime> getReservationDateHour() {
		return reservationDateHour;
	}

	public void setReservationDateHour(Set<LocalTime> reservationDateHour) {
		this.reservationDateHour = reservationDateHour;
	}

	public List<Seat> getSeats() {
		return seats;
	}

	public void setSeats(List<Seat> seats) {
		this.seats = seats;
	}
}
