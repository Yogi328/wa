package com.pecuniaPay.registrationLogin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pecuniaPay.registrationLogin.config.JwtUtils;
import com.pecuniaPay.registrationLogin.entities.JwtRequest;
import com.pecuniaPay.registrationLogin.entities.JwtResponse;
import com.pecuniaPay.registrationLogin.entities.UserDTO;
import com.pecuniaPay.registrationLogin.services.RegistrationService;
import com.pecuniaPay.registrationLogin.services.UserDetailsServiceImpl;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@CrossOrigin
public class AuthenticateController {
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	@Autowired
	private JwtUtils jwtUtils;



	// generate token
	@PostMapping("/generate-token")
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {
		try {
			authenticate(jwtRequest.getUserName(), jwtRequest.getPasssword());

		} catch (UsernameNotFoundException e) {
			e.printStackTrace();
			throw new Exception("User not Found");
		}

		final UserDetails userDetails = this.userDetailsService.loadUserByUsername(jwtRequest.getUserName());
		final String token = this.jwtUtils.generateToken(userDetails);
		return ResponseEntity.ok(new JwtResponse(token));

	}

	private void authenticate(String userName, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));

		} catch (DisabledException e) {
			throw new Exception("User Disabled" + e.getMessage());
		} catch (BadCredentialsException e) {
			throw new Exception("Invalid Credentials" + e.getMessage());
		}

	}

	

}
