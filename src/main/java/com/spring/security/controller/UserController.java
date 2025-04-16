package com.spring.security.controller;

import com.spring.security.entity.Customer;
import com.spring.security.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody Customer customer) {

        Customer savedCustomer = userService.registerUser(customer);
        if (savedCustomer.getId() > 0) {
            return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("User registration failed", HttpStatus.BAD_REQUEST);
        }
    }
}
