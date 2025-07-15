package com.shows_watchlist.controllers;

import com.shows_watchlist.dto.*;
import com.shows_watchlist.executors.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserExecutor userExecutor;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody UserSignUpDTO userSignUpDTO) {
        // Logic to handle user signup
        // This should include saving the user to the database and returning a success message
        logger.info("Signing up user with email: {}", userSignUpDTO.getEmailId());
        return userExecutor.signUpUser(userSignUpDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserSignUpDTO userLoginDTO) {
        // Logic to handle user login
        // This should include validating the user's credentials and returning a success message or token
        logger.info("Logging in user with email: {}", userLoginDTO.getEmailId());
        return userExecutor.loginUser(userLoginDTO);
    }
}
