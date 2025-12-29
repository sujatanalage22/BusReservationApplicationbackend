package com.cjc.springbootapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.cjc.springbootapp.model.Customer;
import com.cjc.springbootapp.model.User;
import com.cjc.springbootapp.repository.CustomerRepository;
import com.cjc.springbootapp.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomerRepository customerRepository;

    private static final String ADMIN_SECRET = "ADMIN123";

    public User register(User user, String adminKey) {

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already registered");
        }

        // 🔐 ROLE
        if (ADMIN_SECRET.equals(adminKey)) {
            user.setRole("ADMIN");
        } else {
            user.setRole("USER");
        }

        // 1️⃣ Save USER
        User savedUser = userRepository.save(user);

        // 2️⃣ AUTO CREATE CUSTOMER
        Customer customer = new Customer();
        customer.setId(savedUser.getId());   // 👈 SAME ID
        customer.setName(savedUser.getName());
      

        customerRepository.save(customer);

        return savedUser;
    }

    public User login(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }
}
