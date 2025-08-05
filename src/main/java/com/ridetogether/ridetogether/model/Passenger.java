package com.ridetogether.ridetogether.model;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Entity
@Data
@NoArgsConstructor
public class Passenger extends User{

    public Passenger(Long id, String name, String email, String homeAddress, String officeAddress,
                  LocalTime preferredArrivalStart, LocalTime preferredArrivalEnd,
                  int flexibilityMinutes, double flexibilityKm, UserRole role) {
        super(id, name, email, homeAddress, officeAddress, preferredArrivalStart, preferredArrivalEnd,
                flexibilityMinutes, flexibilityKm, role);
    }
}
