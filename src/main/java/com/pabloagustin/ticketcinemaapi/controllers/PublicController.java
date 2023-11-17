package com.pabloagustin.ticketcinemaapi.controllers;

import com.pabloagustin.ticketcinemaapi.models.CinemaRoom;
import com.pabloagustin.ticketcinemaapi.payload.MovieResponse;
import com.pabloagustin.ticketcinemaapi.payload.SeatRequest;
import com.pabloagustin.ticketcinemaapi.services.CinemaroomService;
import com.pabloagustin.ticketcinemaapi.services.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/public")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class PublicController {

	private final MovieService movieService;
	private final CinemaroomService cinemaroomService;

	@GetMapping("/all-movies")
	public List<MovieResponse> getAllMovies(){
		return movieService.getAllMovies();
	}

	@GetMapping("/cinemaroom/{cinemaroomId}")
	public List<SeatRequest> getCinemaroomSeats(@PathVariable Long cinemaroomId) {
		return cinemaroomService.getCinemaroomSeats(cinemaroomId);
	}

	@GetMapping("/cinemaroom/all-cinemarooms")
	public List<CinemaRoom> getAllCinemarooms(){
		return cinemaroomService.getAllCinemarooms();
	}

}
