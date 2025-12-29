package com.cjc.springbootapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cjc.springbootapp.dto.LoginRequest;
import com.cjc.springbootapp.dto.RegisterRequest;
import com.cjc.springbootapp.model.Customer;
import com.cjc.springbootapp.model.User;
import com.cjc.springbootapp.repository.CustomerRepository;
import com.cjc.springbootapp.repository.UserRepository;
import com.cjc.springbootapp.service.UserService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    private final UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private CustomerRepository customerRepository;

    AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // REGISTER
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword()); // plain text
        user.setRole("USER");

        user = userRepository.save(user);

        // 🔥 CREATE CUSTOMER AUTOMATICALLY
        Customer customer = new Customer();
        customer.setUser(user);
        customer.setName(request.getName());

        customerRepository.save(customer);

        return ResponseEntity.ok("User registered successfully");
    }

    // LOGIN
    @PostMapping("/login")
    public User login(@RequestBody LoginRequest request) {
        User user = userService.login(
                request.getEmail(),
                request.getPassword() // plain text
        );

        if (user == null) {
            throw new RuntimeException("Invalid credentials");
        }
        return user; // includes role (ADMIN / USER)
    }
}
