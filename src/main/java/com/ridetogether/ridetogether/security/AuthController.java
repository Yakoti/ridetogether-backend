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
                    .body("EMAIL_INVALID " + "Account with this email: " + request.getEmail() + " already exists");
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
                    .body("VALIDATION_ERROR Invalid register request");
        }

        return ResponseEntity.ok(userService.registerNewUser(request));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        if (userService.findByEmail(request.getEmail()).isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }

        User user = userService.findByEmail(request.getEmail()).get();

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }

        String token = jwtService.generateToken(user);

        // 4) Return the token to the client
// tf is that       return ResponseEntity.ok(new AuthenticationResponse(token));
        return ResponseEntity.ok(token);
    }

    @GetMapping("/test")
    public String testAuth(){
        return "hey acces to auth/ endpoints works";
    }

//    @GetMapping("/login")
//    public ResponseEntity<?> loginBrowser(
//            @RequestParam String email,
//            @RequestParam String password) {
//        var userOpt = userService.findByEmail(email);
//        if (userOpt.isEmpty()) {
//            System.out.println("User not found for email: " + email);
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
//        }
//
//        User user = userOpt.get();
//        boolean matches = passwordEncoder.matches(password, user.getPassword());
//        System.out.println("Password matches: " + matches);
//        if (!matches) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
//        }
//        String token = jwtService.generateToken(user);
//        return ResponseEntity.ok(token);
//    }


}
