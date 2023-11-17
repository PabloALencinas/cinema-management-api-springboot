package com.pabloagustin.ticketcinemaapi.controllers;

import com.pabloagustin.ticketcinemaapi.models.ERole;
import com.pabloagustin.ticketcinemaapi.models.Role;
import com.pabloagustin.ticketcinemaapi.models.User;
import com.pabloagustin.ticketcinemaapi.payload.LoginRequest;
import com.pabloagustin.ticketcinemaapi.payload.MessageResponse;
import com.pabloagustin.ticketcinemaapi.payload.SignupRequest;
import com.pabloagustin.ticketcinemaapi.repositories.RoleRepository;
import com.pabloagustin.ticketcinemaapi.repositories.UserRepository;
import com.pabloagustin.ticketcinemaapi.services.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthController {

	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final PasswordEncoder passwordEncoder;
	private final AuthService authService;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest){
		return authService.login(loginRequest);
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signupRequest){

		if (userRepository.existsByEmail(signupRequest.getUsername())){
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username already taken"));
		}

		if (userRepository.existsByEmail(signupRequest.getEmail())){
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use"));
		}

		// Creating new user's account
		User user = new User(signupRequest.getUsername(),
				signupRequest.getEmail(),
				passwordEncoder.encode(signupRequest.getPassword())
		);

		Set<String> strRoles = signupRequest.getRole();
		Set<Role> roles = new HashSet<>();

		if(strRoles == null){
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found"));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				if (role.equals("admin")) {
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found"));
					roles.add(adminRole);
				} else {
					Role userRole = roleRepository.findByName(ERole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}

		user.setRoles(roles);
		userRepository.save(user);

		return ResponseEntity.ok(new MessageResponse("User registered successfully"));

	}

}
