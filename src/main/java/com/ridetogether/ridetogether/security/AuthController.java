package com.ridetogether.ridetogether.security;

import com.ridetogether.ridetogether.model.Driver;
import com.ridetogether.ridetogether.model.User;
import com.ridetogether.ridetogether.repository.UserRepository;
import com.ridetogether.ridetogether.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthController(UserRepository userRepository, UserService userService,
                          PasswordEncoder passwordEncoder,
                          JwtService jwtService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest request, BindingResult result) {
        // 1) Check if email is taken
        if (userService.findByEmail(request.getEmail()).isPresent()) {
            return ResponseEntity
                    .badRequest()
                    .body("Account with this email: " + request.getEmail() + " already exists");
        }

        //2) Handle different invalid inputs
        /*
            one option is to return a JSON
            static class ErrorResponse {
                private String errorCode;
                private String message;
         */
        if (result.hasErrors()) {
            for (FieldError error : result.getFieldErrors()) {
                String field = error.getField();
                String msg = error.getDefaultMessage();
                switch (field) {
                    case "email":
                        return ResponseEntity.badRequest()
                                .body("EMAIL_INVALID " + msg);
                    case "password":
                        return ResponseEntity.badRequest()
                                .body("PASSWORD_INVALID " + msg);
                    case "phone":
                        return ResponseEntity.badRequest()
                                .body("PHONE_INVALID " + msg);
                    case "flexibilityMinutes":
                        return ResponseEntity.badRequest()
                                .body("FLEXIBILITY_MINUTES_INVALID " + msg);
                    case "flexibilityKm":
                        return ResponseEntity.badRequest()
                                .body("FLEXIBILITY_KM_INVALID " + msg);
                    case "role":
                        return ResponseEntity.badRequest()
                                .body("ROLE_INVALID " + msg);
                }
            }
            return ResponseEntity.badRequest()
                    .body("VALIDATION_ERROR Invalid input");
        }

        return ResponseEntity.ok(userService.registerNewUser(request));
    }

//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
//        // 1) Find user by username
//        Optional<User> userOpt = userRepository.findByUsername(request.getUsername());
//        if (userOpt.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
//        }
//        // sending 401 instead of 404 is better because the attacker does not know
//        // if there is such account
//        // if the username or pass is wrong
//
//        User user = userOpt.get();
//
//        // 2) Check password matches hashed password stored
//        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
//        }
//        //it extracts the salt and cost from the stored hash
//        //and hashes the raw password the same way to compare.
//
//        // 3) Generate JWT token (we’ll implement this soon)
//        String token = jwtService.generateToken(user.getUsername());
//
//        // 4) Return the token to the client
////        return ResponseEntity.ok(new AuthenticationResponse(token));
//        return ResponseEntity.ok(token);
//    }

}
