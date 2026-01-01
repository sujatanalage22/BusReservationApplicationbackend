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
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private UserService userService;

    // ================= REGISTER =================
    @PostMapping("/register")
    public ResponseEntity<?> register(
            @RequestBody RegisterRequest request,
            @RequestParam(required = false) String adminKey
    ) {

        // ✅ Prevent duplicate email
        if (userRepository.existsByEmail(request.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body("Email already registered");
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword()); // plain text (later hash)
        
        // ✅ SAFE admin check
        if ("ADMIN@123".equals(adminKey)) {
            user.setRole("ADMIN");
        } else {
            user.setRole("USER");
        }

        user = userRepository.save(user);

        Customer customer = new Customer();
        customer.setUser(user);
        customer.setName(request.getName());

        customerRepository.save(customer);

        return ResponseEntity.ok("User registered successfully");
    }

    // ================= LOGIN =================
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {

        User user = userService.login(
                request.getEmail(),
                request.getPassword()
        );

        if (user == null) {
            return ResponseEntity
                    .status(401)
                    .body("Invalid credentials");
        }

        return ResponseEntity.ok(user);
    }
}
