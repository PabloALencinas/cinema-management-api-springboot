package com.pabloagustin.ticketcinemaapi.controllers;

import com.pabloagustin.ticketcinemaapi.models.Movie;
import com.pabloagustin.ticketcinemaapi.payload.MovieRequest;
import com.pabloagustin.ticketcinemaapi.repositories.MovieRepository;
import com.pabloagustin.ticketcinemaapi.services.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movie")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
public class MovieController {

	private final MovieService movieService;

	@PostMapping("/create-movie")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> createMovie(@RequestBody MovieRequest movieRequest){
		movieService.createMovie(movieRequest);
		return new ResponseEntity<>("Movie created successfully", HttpStatus.CREATED);
	}

	@PostMapping("/update-movie/{movieId}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> updatingMovie(@PathVariable Long movieId, @RequestBody MovieRequest updatingMovieRequest){
		movieService.updateMovie(movieId, updatingMovieRequest);
		return new ResponseEntity<>("Movie updated successfully", HttpStatus.OK);
	}

	@DeleteMapping("/{movieId}/cinemaroom/{cinemaroomId}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> deleteCinemaroomFromMovie(
			@PathVariable Long movieId,
			@PathVariable Long cinemaroomId) {

		movieService.deleteCinemaroomFromMovie(movieId, cinemaroomId);

		return new ResponseEntity<>("Cinemaroom deleted from movie successfully", HttpStatus.OK);
	}

}
