package com.pecuniaPay.registrationLogin.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pecuniaPay.registrationLogin.entities.Registration;
@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Long> {
	 public Registration findByUserName(String userName);
}
