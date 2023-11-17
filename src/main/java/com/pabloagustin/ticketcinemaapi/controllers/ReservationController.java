package com.pabloagustin.ticketcinemaapi.controllers;

import com.pabloagustin.ticketcinemaapi.models.Reservation;
import com.pabloagustin.ticketcinemaapi.payload.ReservationRequest;
import com.pabloagustin.ticketcinemaapi.payload.ReservationResponse;
import com.pabloagustin.ticketcinemaapi.services.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservation")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
public class ReservationController {

	private final ReservationService reservationService;

	@PostMapping("/user/{userId}/create-reservation")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<?> createReservation(@PathVariable Long userId,
	                                           @RequestBody ReservationRequest reservationRequest){
		reservationService.createReservation(userId, reservationRequest);
		return new ResponseEntity<>("Reservation created successfully", HttpStatus.CREATED);
	}

//	@GetMapping("/admin/all-reservations")
//	@PreAuthorize("hasRole('ADMIN')")
//	public List<ReservationResponse> getAllReservations(){
//		return reservationService.getAllReservations();
//	}

}
