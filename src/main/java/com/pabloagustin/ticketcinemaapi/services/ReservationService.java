package com.pabloagustin.ticketcinemaapi.services;

import com.pabloagustin.ticketcinemaapi.models.*;
import com.pabloagustin.ticketcinemaapi.payload.MovieResponse;
import com.pabloagustin.ticketcinemaapi.payload.ReservationRequest;
import com.pabloagustin.ticketcinemaapi.payload.ReservationResponse;
import com.pabloagustin.ticketcinemaapi.repositories.CinemaroomRepository;
import com.pabloagustin.ticketcinemaapi.repositories.MovieRepository;
import com.pabloagustin.ticketcinemaapi.repositories.ReservationRepository;
import com.pabloagustin.ticketcinemaapi.repositories.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationService implements Serializable {

	private final UserRepository userRepository;
	private final ReservationRepository reservationRepository;
	private final MovieRepository movieRepository;
	private final CinemaroomRepository cinemaroomRepository;

	public void createReservation(@Valid Long userId,
	                              @Valid @RequestBody ReservationRequest reservationRequest){

			// Check the user authentication in order to allow reservations
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			if (authentication != null && authentication.isAuthenticated()) {

				Optional<Movie> movie = movieRepository.findById(reservationRequest.getMovieId());
				// Check if movie in request exists
				if(movie.isPresent()){
					Movie currentMovie = movie.get();
					// Check if the showtime in request match with any of the available showtime in movies
					if (!Collections.disjoint(currentMovie.getAvailableshowtimes(), reservationRequest.getShowtimeRequest())){
						// Cinemarooms for seats
						Optional<CinemaRoom> cinemaroom = cinemaroomRepository.findById(
								reservationRequest.getCinemaroomId()
						);
						if(cinemaroom.isPresent()){
							CinemaRoom currentCinemaroom = cinemaroom.get();
							// Check if the seats in request are valid in cinemaroom
							List<Seat> cinemaroomSeats = currentCinemaroom.getSeats();

							Set<Long> seatsToReserve = reservationRequest.getSeatsId();
							List<Seat> reservedSeats = new ArrayList<>();

							for (Long seatId : seatsToReserve){
								// Look for seat by ID
								Optional<Seat> optionalSeat = cinemaroomSeats.stream()
										.filter(seat -> seat.getId().equals(seatId))
										.findFirst();
								if (optionalSeat.isPresent()){
									Seat seat = optionalSeat.get();
									// Check if seat is available
									if(seat.getStatus() == ESeatStatus.AVAILABLE){
										// Creating a new obect Seat with the same attributes
										Seat reservedSeat = new Seat(seat.getRow(), seat.getColumn(), ESeatStatus.BUSY, currentCinemaroom, null);
										reservedSeats.add(reservedSeat);
									} else {
										throw new RuntimeException("The seat with ID " + seatId + " is not available");
									}
								} else {
									throw new RuntimeException("Seat with the ID: " + seatId + " Not found");
								}
							}

							Reservation newReservation = new Reservation();
							Optional<User> user = userRepository.findById(userId);
							if(user.isPresent()){
								User currentUser = user.get();
								newReservation.setUserClient(currentUser);
							} else {
								throw new RuntimeException("User with ID: " + userId + " is not found");
							}
							newReservation.setMovieReservation(currentMovie);
							newReservation.setReservationDateHour(reservationRequest.getShowtimeRequest());
							newReservation.setSeats(reservedSeats);
							reservationRepository.save(newReservation);

						} else {
							throw new RuntimeException("Cinemaroom in request is not valid or does not exist");
						}
					} else {
						throw new RuntimeException("There is not available showtime for that movie");
					}

				} else {
					throw new RuntimeException("You are looking a reservation for a movie that is not found");
				}
			} else {
				throw new RuntimeException("The user must be logged in to make a reservation.");
			}
	}

//	public List<ReservationResponse> getAllReservations() {
//		List<Reservation> reservations = reservationRepository.findAll();
//
//		return reservations.stream()
//				.map(reservation -> {
//					Long userId = (reservation.getUserClient() != null) ? reservation.getUserClient().getId() : null;
//					return new ReservationResponse(
//							reservation.getId(),
//							userId,
//							reservation.getReservationDateHour()
//					);
//				})
//				.collect(Collectors.toList());
//	}


}
