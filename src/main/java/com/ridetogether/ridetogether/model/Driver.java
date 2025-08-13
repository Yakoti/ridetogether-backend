package com.ridetogether.ridetogether.model;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalTime;

@Entity
@Data
@NoArgsConstructor
public class Driver extends User {
    private int availableSeats;
    private double costPer100KmEUR;

    public Driver(Long id, String name, String password, String phone, String email, String homeAddress, String officeAddress,
                  LocalTime preferredArrivalStart, LocalTime preferredArrivalEnd,
                  int flexibilityMinutes, double flexibilityKm, UserRole role, int availableSeats, double costPer100KmEUR) {
        super(id, name, email, password, phone, homeAddress, officeAddress, preferredArrivalStart, preferredArrivalEnd,
                flexibilityMinutes, flexibilityKm, role);
        this.availableSeats = availableSeats;
        this.costPer100KmEUR = costPer100KmEUR;
    }
}
