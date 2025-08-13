package com.ridetogether.ridetogether.service;

import com.ridetogether.ridetogether.model.*;
import com.ridetogether.ridetogether.repository.DriverRepository;
import com.ridetogether.ridetogether.repository.PassengerRepository;
import com.ridetogether.ridetogether.repository.UserRepository;
import com.ridetogether.ridetogether.security.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final DriverRepository driverRepository;
    private final PassengerRepository passengerRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(
            UserRepository userRepository,
            DriverRepository driverRepository,
            PassengerRepository passengerRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.driverRepository = driverRepository;
        this.passengerRepository = passengerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<User> findById(long id) {
        return userRepository.findById(id);
    }

    public Optional<User> findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    private List<User> findByRoleAndTimeOverlap(UserRole role, LocalTime startTime, LocalTime endTime) {
        return userRepository.findByRoleAndTimeOverlap(role, startTime, endTime);
    }

    //TODO: they should be order by the closest! Shouldn't return all users and so on
    public List<User> findUsersWithMatchingSchedulesAndOppositeRole(long id) {
        Optional<User> userOpt = userRepository.findById(id);
        User user = userOpt.get();
        //set user role to the opposite
        UserRole role = user.getRole() == UserRole.DRIVER ? UserRole.PASSENGER : UserRole.DRIVER;

        return findByRoleAndTimeOverlap(role, user.getPreferredArrivalStart(), user.getPreferredArrivalEnd());
    }

    // Map RegisterRequest to proper User subclass based on role and save
    public User registerNewUser(RegisterRequest request) {
        if (request.getRole() == UserRole.DRIVER) {
            Driver driver = new Driver();
            mapCommonFieldsDriver(driver, request);
           return driverRepository.save(driver);
        } else if (request.getRole() == UserRole.PASSENGER) {
            Passenger passenger = new Passenger();
            mapCommonFieldsPassenger(passenger, request);
            return passengerRepository.save(passenger);
        } else {
            throw new IllegalArgumentException("Unsupported role: " + request.getRole());
        }

    }

    private void mapCommonFieldsDriver(Driver driver, RegisterRequest request) {
        driver.setName(request.getName());
        driver.setEmail(request.getEmail());
        driver.setPassword(passwordEncoder.encode(request.getPassword()));
        driver.setRole(UserRole.DRIVER);
        driver.setPhone(request.getPhone());
        driver.setHomeAddress(request.getHomeAddress());
        driver.setOfficeAddress(request.getOfficeAddress());
        driver.setPreferredArrivalStart(request.getPreferredArrivalStart());
        driver.setPreferredArrivalEnd(request.getPreferredArrivalEnd());
        driver.setFlexibilityMinutes(request.getFlexibilityMinutes());
        driver.setFlexibilityKm(request.getFlexibilityKm());
        driver.setAvailableSeats(request.getAvailableSeats());
        driver.setCostPer100KmEUR(request.getCostPer100KmEUR());
    }

    private void mapCommonFieldsPassenger(Passenger passenger, RegisterRequest request) {
        passenger.setName(request.getName());
        passenger.setEmail(request.getEmail());
        passenger.setPassword(passwordEncoder.encode(request.getPassword()));
        passenger.setRole(UserRole.PASSENGER);
        passenger.setPhone(request.getPhone());
        passenger.setHomeAddress(request.getHomeAddress());
        passenger.setOfficeAddress(request.getOfficeAddress());
        passenger.setPreferredArrivalStart(request.getPreferredArrivalStart());
        passenger.setPreferredArrivalEnd(request.getPreferredArrivalEnd());
        passenger.setFlexibilityMinutes(request.getFlexibilityMinutes());
        passenger.setFlexibilityKm(request.getFlexibilityKm());
    }
}