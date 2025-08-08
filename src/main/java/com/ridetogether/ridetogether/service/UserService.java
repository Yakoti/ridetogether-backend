package com.ridetogether.ridetogether.service;

import com.ridetogether.ridetogether.model.User;
import com.ridetogether.ridetogether.model.UserRole;
import com.ridetogether.ridetogether.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User findById(long id) {
        return userRepository.findById(id).orElse(null);
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
}
