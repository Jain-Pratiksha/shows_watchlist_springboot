package com.shows_watchlist.services;

import com.shows_watchlist.model.*;
import com.shows_watchlist.repositories.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void signUpUser(User user) {
        userRepository.save(user);
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmailId(email);
    }

    public User findUserByEmailAndRole(String email, String role) {
        return userRepository.findByEmailAndRole(email, role);
    }

}
