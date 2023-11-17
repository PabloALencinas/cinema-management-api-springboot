package com.pabloagustin.ticketcinemaapi.services;

import com.pabloagustin.ticketcinemaapi.auth.JwtService;
import com.pabloagustin.ticketcinemaapi.payload.JwtResponse;
import com.pabloagustin.ticketcinemaapi.payload.LoginRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthService {

	private final AuthenticationManager authenticationManager;
	private final JwtService jwtService;
	public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest){
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						loginRequest.getUsername(),
						loginRequest.getPassword())
		);

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtService.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse
				(jwt,
						userDetails.getId(),
						userDetails.getUsername(),
						userDetails.getEmail(),
						roles)
		);
	}
}