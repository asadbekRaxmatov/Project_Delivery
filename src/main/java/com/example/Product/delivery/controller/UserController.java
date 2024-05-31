package com.example.Product.delivery.controller;

import com.example.Product.delivery.dto.UserDTO;
import com.example.Product.delivery.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Log4j2
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PreAuthorize("hasAnyRole('ADMIN', 'EDITOR', 'READER')")
    @GetMapping("/list")
    public List<UserDTO> getAllAdmins() {
        log.debug("Get a list of users");
        return userService.findAll();
    }


    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        log.debug("Get by id "+ id);

        Optional<UserDTO> user = userService.findById(id);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO, @RequestParam String passwordAlgorithm) {
        log.debug("Creating new admin from {}", userDTO);
        if (userService.existsByUsername(userDTO.getUsername()) || userService.existsByEmail(userDTO.getEmail())) {
            return ResponseEntity.badRequest().body(null);
        }
        UserDTO createdAdmin = userService.save(userDTO, passwordAlgorithm);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAdmin);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserDTO> updateAdmin(@PathVariable Long id, @RequestBody UserDTO adminDTO, @RequestParam String passwordAlgorithm) {
        log.debug("Update user" + adminDTO);

        Optional<UserDTO> existingAdmin = userService.findById(id);
        if (existingAdmin.isPresent()) {
            UserDTO updatedAdmin = userService.save(adminDTO, passwordAlgorithm);
            return ResponseEntity.ok(updatedAdmin);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        log.debug("delete user by id");
        if (userService.findById(id).isPresent()) {
            userService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/updateStatus/{id}")
    public ResponseEntity<UserDTO> updateAdminStatus(@PathVariable Long id, @RequestParam boolean status) {
        try {
            UserDTO updatedAdmin = userService.updateStatus(id, status);
            return ResponseEntity.ok(updatedAdmin);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
