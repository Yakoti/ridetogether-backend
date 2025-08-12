//package com.ridetogether.ridetogether.dto;
//
//import com.ridetogether.ridetogether.model.UserRole;
//import jakarta.validation.constraints.*;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.time.LocalTime;
//
////TODO: ADD validation for the addresses so they form valid urls!
//
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//public class UserDTO {
//
//    @NotBlank
//    private String name;
//
//    @Email
//    @NotBlank
//    private String email;
//
//    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$",
//            message = "Password must have at least 8 chars, one uppercase, one lowercase, one digit, and one special char")
//    private String password;
//
//
//    @Pattern(regexp = "\\+?[0-9]{10,15}", message = "Invalid phone number")
//    private String phone;
//
//    private String homeAddress;
//    private String officeAddress;
//
//    private LocalTime preferredArrivalStart;
//    private LocalTime preferredArrivalEnd;
//
//    @Min(0)
//    private int flexibilityMinutes;
//
//    @DecimalMin("0.0")
//    private double flexibilityKm;
//
//    @NotNull
//    private UserRole role;
//
//}
