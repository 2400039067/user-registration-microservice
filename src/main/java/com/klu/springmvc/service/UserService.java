package com.klu.springmvc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.klu.springmvc.entity.User;
import com.klu.springmvc.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;

    public UserService(
            UserRepository repository,
            PasswordEncoder passwordEncoder,
            JWTService jwtService) {

        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    // Register user
    public User register(User user) {

        user.setPassword(
                passwordEncoder.encode(user.getPassword())
        );

        if (user.getRole() == null || user.getRole().isEmpty()) {
            user.setRole("CUSTOMER");
        }

        return repository.save(user);
    }

    // Login user
    public String login(String email, String password) {

        Optional<User> optionalUser =
                repository.findByEmail(email);

        if (optionalUser.isEmpty()) {
            return null;
        }

        User user = optionalUser.get();

        if (!passwordEncoder.matches(
                password,
                user.getPassword())) {

            return null;
        }

        return jwtService.generateToken(user.getEmail());
    }

    // Get all users
    public List<User> getAllUsers() {
        return repository.findAll();
    }

    // Get user by ID
    public Optional<User> getUserById(Long id) {
        return repository.findById(id);
    }

    // Get user by email
    public Optional<User> getUserByEmail(String email) {
        return repository.findByEmail(email);
    }

    // Update user
    public User updateUser(Long id, User updatedUser) {

        Optional<User> optionalUser =
                repository.findById(id);

        if (optionalUser.isEmpty()) {
            return null;
        }

        User user = optionalUser.get();

        user.setName(updatedUser.getName());
        user.setPhone(updatedUser.getPhone());
        user.setAddress(updatedUser.getAddress());

        if (updatedUser.getPassword() != null
                && !updatedUser.getPassword().isEmpty()) {

            user.setPassword(
                    passwordEncoder.encode(
                            updatedUser.getPassword()
                    )
            );
        }

        return repository.save(user);
    }

    // Delete user
    public boolean deleteUser(Long id) {

        if (!repository.existsById(id)) {
            return false;
        }

        repository.deleteById(id);

        return true;
    }
}