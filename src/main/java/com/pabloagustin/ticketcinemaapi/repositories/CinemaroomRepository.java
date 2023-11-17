package com.pabloagustin.ticketcinemaapi.repositories;

import com.pabloagustin.ticketcinemaapi.models.CinemaRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CinemaroomRepository extends JpaRepository<CinemaRoom, Long> {

	Optional<CinemaRoom> findByName(String name);
}
