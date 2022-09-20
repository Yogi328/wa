package com.pecuniaPay.registrationLogin.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pecuniaPay.registrationLogin.entities.Registration;
import com.pecuniaPay.registrationLogin.entities.UserDTO;
import com.pecuniaPay.registrationLogin.repositories.RegistrationRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private RegistrationRepository registrationRepository;
	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Registration registration = this.registrationRepository.findByUserName(username);
		if (registration == null) {
			System.out.println("Registration Not Found");
			throw new UsernameNotFoundException("No Registration Found");
		}

		return new User(registration.getUserName(), registration.getPassword(), new ArrayList<>());
	}

	
}
