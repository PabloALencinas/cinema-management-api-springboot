package com.pabloagustin.ticketcinemaapi.repositories;

import com.pabloagustin.ticketcinemaapi.models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
