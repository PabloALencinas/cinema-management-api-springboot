package com.pabloagustin.ticketcinemaapi.services;

import com.pabloagustin.ticketcinemaapi.models.CinemaRoom;
import com.pabloagustin.ticketcinemaapi.models.ESeatStatus;
import com.pabloagustin.ticketcinemaapi.models.Seat;
import com.pabloagustin.ticketcinemaapi.payload.CinemaroomRequest;
import com.pabloagustin.ticketcinemaapi.payload.SeatRequest;
import com.pabloagustin.ticketcinemaapi.repositories.CinemaroomRepository;
import com.pabloagustin.ticketcinemaapi.repositories.SeatRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class CinemaroomService {

	private final CinemaroomRepository cinemaroomRepository;
	private final SeatRepository seatRepository;


	public void createRoom(Integer rows, Integer columns, @Valid @RequestBody CinemaroomRequest cinemaroomRequest) {

		Optional<CinemaRoom> cinemaRoom = cinemaroomRepository.findByName(cinemaroomRequest.getName());

		if (cinemaRoom.isEmpty()) {
			CinemaRoom newCinemaRoom = new CinemaRoom();
			newCinemaRoom.setName(cinemaroomRequest.getName());
			List<Seat> seatsList = new ArrayList<>();

			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < columns; j++) {
					Seat seat = new Seat();
					seat.setRow(i);
					seat.setColumn(j);
					seat.setStatus(ESeatStatus.AVAILABLE);
					seatsList.add(seat);
					seat.setCinemaRoom(newCinemaRoom);
				}
			}

			newCinemaRoom.setSeats(seatsList);


			cinemaroomRepository.save(newCinemaRoom);
			seatRepository.saveAll(seatsList);

		} else {
			throw new RuntimeException("Cinema room already created");
		}
	}

	public void updateCinemaroom(Long cinemaRommId,
	                             Integer rows,
	                             Integer columns,
	                             @Valid @RequestBody CinemaroomRequest cinemaroomRequest){

		Optional<CinemaRoom> cinemaRoom = cinemaroomRepository.findById(cinemaRommId);

		if(cinemaRoom.isPresent()){
			CinemaRoom currentCinemaRoom = cinemaRoom.get();
			if(cinemaroomRequest.getName() == null){
				currentCinemaRoom.setName(currentCinemaRoom.getName());
			} else {
				currentCinemaRoom.setName(cinemaroomRequest.getName());
			}

			if (rows == 0 || columns == 0){
				currentCinemaRoom.setSeats(currentCinemaRoom.getSeats());
			} else {
				List<Seat> seatsList = new ArrayList<>();

				for (int i = 0; i < rows; i++) {
					for (int j = 0; j < columns; j++) {
						Seat seat = new Seat();
						seat.setRow(i);
						seat.setColumn(j);
						seat.setStatus(ESeatStatus.AVAILABLE);
						seatsList.add(seat);
						seat.setCinemaRoom(currentCinemaRoom);
					}
				}

				currentCinemaRoom.setSeats(seatsList);

				cinemaroomRepository.save(currentCinemaRoom);
				seatRepository.saveAll(seatsList);
			}

		} else {
			throw new RuntimeException("Cinemaroom does not exist");
		}

	}

	public List<SeatRequest> getCinemaroomSeats(Long cinemaroomId) {
		Optional<CinemaRoom> cinemaRoom = cinemaroomRepository.findById(cinemaroomId);

		if (cinemaRoom.isPresent()) {
			CinemaRoom currentCinemaroom = cinemaRoom.get();
			List<Seat> cinemaroomSeats = currentCinemaroom.getSeats();

			return cinemaroomSeats.stream()
					.map(seat -> new SeatRequest(seat.getId(), seat.getStatus()))
					.collect(Collectors.toList());
		} else {
			throw new RuntimeException("Cinemaroom does not exist");
		}
	}

	public List<SeatRequest> getAvailableCinemaroomSeats(Long cinemaroomId) {
		Optional<CinemaRoom> cinemaRoom = cinemaroomRepository.findById(cinemaroomId);

		if (cinemaRoom.isPresent()) {
			CinemaRoom currentCinemaroom = cinemaRoom.get();
			List<Seat> cinemaroomSeats = currentCinemaroom.getSeats();

			// Filtrar los asientos que tengan status "AVAILABLE"
			List<SeatRequest> availableSeats = cinemaroomSeats.stream()
					.filter(seat -> seat.getStatus() == ESeatStatus.AVAILABLE)
					.map(seat -> new SeatRequest(seat.getId(), seat.getStatus()))
					.collect(Collectors.toList());

			return availableSeats;
		} else {
			throw new RuntimeException("Cinemaroom does not exist");
		}
	}

	public List<CinemaRoom> getAllCinemarooms(){
		return cinemaroomRepository.findAll();
	}

}
