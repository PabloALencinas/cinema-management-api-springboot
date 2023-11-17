package com.pabloagustin.ticketcinemaapi.payload;

import java.time.LocalTime;
import java.util.Set;

public class ReservationRequest {

	private Long movieId;

	private Long cinemaroomId;

	private Set<Long> seatsId;

	private Set<LocalTime> showtimeRequest;

	public ReservationRequest(){
	}

	public ReservationRequest(Set<Long> seatId, Long movieId, Long cinemaroomId, Integer rowCinemaroom, Integer columnCinemaroom, Set<LocalTime> showtimeRequest) {
		this.movieId = movieId;
		this.seatsId = seatId;
		this.cinemaroomId = cinemaroomId;
		this.showtimeRequest = showtimeRequest;
	}



	public Set<Long> getSeatsId() {
		return seatsId;
	}

	public void setSeatsId(Set<Long> seatsId) {
		this.seatsId = seatsId;
	}


	public Long getMovieId() {
		return movieId;
	}

	public void setMovieId(Long movieId) {
		this.movieId = movieId;
	}

	public Long getCinemaroomId() {
		return cinemaroomId;
	}

	public void setCinemaroomId(Long cinemaroomId) {
		this.cinemaroomId = cinemaroomId;
	}

	public Set<LocalTime> getShowtimeRequest() {
		return showtimeRequest;
	}

	public void setShowtimeRequest(Set<LocalTime> showtimeRequest) {
		this.showtimeRequest = showtimeRequest;
	}
}
