package com.pabloagustin.ticketcinemaapi.repositories;

import com.pabloagustin.ticketcinemaapi.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Long> {

	Optional<Movie> findByName(String name);

}
