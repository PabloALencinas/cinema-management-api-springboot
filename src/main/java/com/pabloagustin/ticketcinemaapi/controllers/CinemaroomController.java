package com.pabloagustin.ticketcinemaapi.controllers;

import com.pabloagustin.ticketcinemaapi.payload.CinemaroomRequest;
import com.pabloagustin.ticketcinemaapi.payload.SeatRequest;
import com.pabloagustin.ticketcinemaapi.services.CinemaroomService;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cinemaroom")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
public class CinemaroomController {

	private final CinemaroomService cinemaroomService;

	@PostMapping("/admin/create-cinemaroom")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> createCinemaRoom(@RequestParam int rows,
	                                          @RequestParam int columns,
	                                          @RequestBody CinemaroomRequest cinemaroomRequest){
		cinemaroomService.createRoom(rows, columns, cinemaroomRequest);
		return new ResponseEntity<>("Cinema room created successfully", HttpStatus.CREATED);
	}

	@PostMapping("/admin/update-cinemaroom/{cinemaroomId}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> updateCinemaRoom(@PathVariable Long cinemaroomId,
	                                          @Nullable @RequestParam Integer rows,
	                                          @Nullable @RequestParam Integer columns,
	                                          @RequestBody CinemaroomRequest cinemaroomRequest){
		cinemaroomService.updateCinemaroom(cinemaroomId, rows, columns, cinemaroomRequest);
		return new ResponseEntity<>("Cinema room updated successfully", HttpStatus.OK);
	}


}
