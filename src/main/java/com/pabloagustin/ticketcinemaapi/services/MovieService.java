package com.pabloagustin.ticketcinemaapi.services;

import com.pabloagustin.ticketcinemaapi.models.CinemaRoom;
import com.pabloagustin.ticketcinemaapi.models.Movie;
import com.pabloagustin.ticketcinemaapi.payload.MovieRequest;
import com.pabloagustin.ticketcinemaapi.payload.MovieResponse;
import com.pabloagustin.ticketcinemaapi.repositories.CinemaroomRepository;
import com.pabloagustin.ticketcinemaapi.repositories.MovieRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovieService {

	private final MovieRepository movieRepository;
	private final CinemaroomRepository cinemaroomRepository;

	public void createMovie(@Valid @RequestBody MovieRequest movieRequest){

		Optional<Movie> currentMovie = movieRepository.findByName(movieRequest.getName());

		if(currentMovie.isEmpty()){
			Movie newMovie = new Movie();
			newMovie.setName(movieRequest.getName());
			newMovie.setDescription(movieRequest.getDescription());
			newMovie.setAvailableshowtimes(movieRequest.getAvailableShowtimes());

			Set<Long> cinemaRoomIds = movieRequest.getCinemaroomId();
			if (cinemaRoomIds != null && !cinemaRoomIds.isEmpty()) {
				Set<CinemaRoom> cinemaRooms = new HashSet<>();

				for (Long cinemaRoomId : cinemaRoomIds) {
					Optional<CinemaRoom> cinemaRoom = cinemaroomRepository.findById(cinemaRoomId);
					cinemaRoom.ifPresent(room -> {
						cinemaRooms.add(room);
						room.getMovies().add(newMovie); // Aquí añadimos la película a la lista en CinemaRoom
					});
				}

				newMovie.setCinemaRooms(cinemaRooms);
			}

			movieRepository.save(newMovie);

		} else {
			throw new RuntimeException("Movie already created");
		}

	}

	public void updateMovie(Long movieId, @RequestBody MovieRequest updatingMovie){

		Optional<Movie> movie = movieRepository.findById(movieId);

		if(movie.isPresent()){
			Movie currentMovie = movie.get();
			if(updatingMovie.getName() != null){
				currentMovie.setName(updatingMovie.getName());
			} else {
				currentMovie.setName(currentMovie.getName());
			}

			if(updatingMovie.getDescription() != null){
				currentMovie.setDescription(updatingMovie.getDescription());
			} else {
				currentMovie.setDescription(currentMovie.getDescription());
			}

			if(updatingMovie.getAvailableShowtimes() != null){
				currentMovie.setAvailableshowtimes(updatingMovie.getAvailableShowtimes());
			} else {
				currentMovie.setAvailableshowtimes(currentMovie.getAvailableshowtimes());
			}

			Set<Long> cinemaRoomIds = updatingMovie.getCinemaroomId();

			if (!cinemaRoomIds.isEmpty()) {
				Set<CinemaRoom> cinemaRooms = new HashSet<>();

				for (Long cinemaRoomId : cinemaRoomIds) {
					Optional<CinemaRoom> cinemaRoom = cinemaroomRepository.findById(cinemaRoomId);

					cinemaRoom.ifPresent(room -> {
						cinemaRooms.add(room);
						room.getMovies().add(currentMovie);
					});
				}

				currentMovie.setCinemaRooms(cinemaRooms);
			} else {
				throw new RuntimeException("Set of ID's are null");
			}

			movieRepository.save(currentMovie);

		} else {
			throw new RuntimeException("Movie is not found");
		}
	}

	public void deleteMovie(Long movieId){
		movieRepository.deleteById(movieId);
	}


	public List<MovieResponse> getAllMovies() {
		List<Movie> movies = movieRepository.findAll();

		return movies.stream()
				.map(movie -> new MovieResponse(
						movie.getId(),
						movie.getName(),
						movie.getDescription(),
						movie.getAvailableshowtimes(),
						movie.getCinemaRooms().stream().map(CinemaRoom::getId).collect(Collectors.toSet())
				))
				.collect(Collectors.toList());
	}

	public void deleteCinemaroomFromMovie(Long movieId, Long cinemaroomId) {
		Optional<Movie> movieOptional = movieRepository.findById(movieId);

		movieOptional.ifPresent(movie -> {
			Optional<CinemaRoom> cinemaRoomOptional = cinemaroomRepository.findById(cinemaroomId);

			cinemaRoomOptional.ifPresent(cinemaRoom -> {
				movie.getCinemaRooms().remove(cinemaRoom);
				cinemaRoom.getMovies().remove(movie);

				movieRepository.save(movie);
				cinemaroomRepository.save(cinemaRoom);
			});
		});
	}

}
