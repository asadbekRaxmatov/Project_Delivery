package com.example.Product.delivery.service;

import com.example.Product.delivery.domain.User;
import com.example.Product.delivery.dto.UserDTO;
import com.example.Product.delivery.mapper.UserMapper;
import com.example.Product.delivery.repository.UserRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private UserRepository userRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private SCryptPasswordEncoder sCryptPasswordEncoder;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDTO> findAll() {
        return userRepository.findAll().stream()
                .map(UserMapper::toUserDTO)
                .collect(Collectors.toList());
    }

    public Optional<UserDTO> findById(Long id) {
        Optional<User> adminOptional = userRepository.findById(id);
        if (adminOptional.isPresent()) {
            return Optional.of(UserMapper.toUserDTO(adminOptional.get()));
        } else {
            return Optional.empty();
        }
    }

    //redis
    @Cacheable(value = "user", key = "#id")
    public UserDTO getUserById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return UserMapper.toUserDTO(user);
        } else {
            throw new RuntimeException("User not found with id: " + id);
        }
    }

    public UserDTO save(UserDTO userDTO) {
        User user = UserMapper.toUser(userDTO);
        return UserMapper.toUserDTO(userRepository.save(user));
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public UserDTO updateStatus(Long id, boolean status) {
        Optional<User> adminOptional = userRepository.findById(id);
        if (adminOptional.isPresent()) {
            User user = adminOptional.get();
            user.setEnabled(status);
            User userSave = userRepository.save(user);
            return UserMapper.toUserDTO(userSave);
        } else {
            throw new IllegalArgumentException("Admin not found with id: " + id);
        }
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public UserDTO save(UserDTO adminDTO, String passwordAlgorithm) {
        User user = UserMapper.toUser(adminDTO);
        if (passwordAlgorithm.equals("BCRYPT")) {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        } else if (passwordAlgorithm.equals("SCRYPT")) {
            user.setPassword(sCryptPasswordEncoder.encode(user.getPassword()));
        } else {
            throw new IllegalArgumentException("Invalid password algorithm");
        }
        user = userRepository.save(user);
        return UserMapper.toUserDTO(user);
    }


}

