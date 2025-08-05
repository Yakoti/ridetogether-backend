package com.ridetogether.ridetogether.repository;

import com.ridetogether.ridetogether.model.User;
import com.ridetogether.ridetogether.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalTime;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByRole(UserRole role);

    @Query("SELECT u FROM User u WHERE u.role = :role AND " +
            "((u.preferredArrivalStart <= :endTime AND u.preferredArrivalEnd >= :startTime))")
    List<User> findByRoleAndTimeOverlap(@Param("role") UserRole role,
                                        @Param("startTime") LocalTime startTime,
                                        @Param("endTime") LocalTime endTime);
}
