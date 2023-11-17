package com.pabloagustin.ticketcinemaapi.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.NonNull;

@Entity
@Table(name = "seats")
public class Seat {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NonNull
	private Integer rownumber;
	@NonNull
	private Integer columnnumber;
	@NotBlank
	private ESeatStatus status;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cinema_room_id")
	private CinemaRoom cinemaRoom;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "seats_reservation")
	private Reservation reservation;

	public Seat(){}

	public Seat(@NonNull Integer rownumber,
	            @NonNull Integer columnnumber,
	            @NotBlank ESeatStatus status,
	            CinemaRoom cinemaRoom,
	            Reservation reservation) {
		this.rownumber = rownumber;
		this.columnnumber = columnnumber;
		this.status = status;
		this.cinemaRoom = cinemaRoom;
		this.reservation = reservation;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CinemaRoom getCinemaRoom() {
		return cinemaRoom;
	}

	public void setCinemaRoom(CinemaRoom cinemaRoom) {
		this.cinemaRoom = cinemaRoom;
	}

	public Integer getRow() {
		return rownumber;
	}

	public void setRow(Integer rownumber) {
		this.rownumber = rownumber;
	}

	public Integer getColumn() {
		return columnnumber;
	}

	public void setColumn(Integer columnnumber) {
		this.columnnumber = columnnumber;
	}

	public ESeatStatus getStatus() {
		return status;
	}

	public void setStatus(ESeatStatus status) {
		this.status = status;
	}

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}
}
