package com.ridetogether.ridetogether.repository;

import com.ridetogether.ridetogether.model.Ride;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RideRepository extends JpaRepository<Ride, Long> {
    Optional<Ride>findByName(String name);
}
