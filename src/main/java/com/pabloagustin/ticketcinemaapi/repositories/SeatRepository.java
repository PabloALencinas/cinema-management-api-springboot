package com.pabloagustin.ticketcinemaapi.repositories;

import com.pabloagustin.ticketcinemaapi.models.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat, Long> {
}
