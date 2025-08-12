package com.ridetogether.ridetogether.repository;

import com.ridetogether.ridetogether.model.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {
}
