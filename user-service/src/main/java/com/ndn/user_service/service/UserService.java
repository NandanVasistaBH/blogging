package com.ndn.user_service.service;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ndn.user_service.model.User;
import com.ndn.user_service.repository.UserRepository;

@Service("UserService")
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user) {
        // Password encoding logic
        return userRepository.save(user);
    }

    public Optional<User> findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
