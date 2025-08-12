package com.ridetogether.ridetogether.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalTime;

//TODO: add password, contact info, and so on

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password;
    private String phone;
    private String homeAddress;
    private String officeAddress;
    private LocalTime preferredArrivalStart;
    private LocalTime preferredArrivalEnd;
    private int flexibilityMinutes;
    private double flexibilityKm;

    @Enumerated(EnumType.STRING)
    private UserRole role;

}
