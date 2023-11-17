package com.pabloagustin.ticketcinemaapi.repositories;

import com.pabloagustin.ticketcinemaapi.models.ERole;
import com.pabloagustin.ticketcinemaapi.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long>{
	Optional<Role> findByName(ERole name);
}
