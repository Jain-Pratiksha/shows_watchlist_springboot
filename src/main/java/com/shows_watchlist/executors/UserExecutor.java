package com.shows_watchlist.executors;

import com.shows_watchlist.dto.*;
import com.shows_watchlist.model.*;
import com.shows_watchlist.services.*;
import com.shows_watchlist.utils.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;

@Component
public class UserExecutor {

    @Autowired
    private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(UserExecutor.class);
    /**
     * This method is used to sign up a user.
     * It takes a UserSignUpDTO object, converts it to a User object,
     * encodes the password, and saves the user using the UserService.
     *
     * @param userSignUpDTO The DTO containing user sign-up details.
     */
    public ResponseEntity<?> signUpUser(UserSignUpDTO userSignUpDTO) {
        try {
            User user = new User();
            user.setEmailId(userSignUpDTO.getEmailId());
            user.setPassword(userSignUpDTO.getPassword());
            user.setRole(userSignUpDTO.getRole() != null ? userSignUpDTO.getRole() : Enums.Role.USER); // Default to USER if role is not provided
            userService.signUpUser(user);
            logger.info("User signed up successfully with email: {}", userSignUpDTO.getEmailId());
            return ResponseEntity.ok("User signed up successfully");
        } catch (Exception e) {
            logger.error("Error signing up user: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error signing up user: " + e.getMessage());
        }
    }

    public ResponseEntity<?> loginUser(UserSignUpDTO userLoginDTO) {
        User user = userService.findUserByEmailAndRole(userLoginDTO.getEmailId(), String.valueOf(userLoginDTO.getRole()));
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }
        if(userLoginDTO.getPassword().equals(user.getPassword())) {
            return ResponseEntity.ok("Successfully logged in");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ShowWatchListResponseDTO("Invalid email or password"));
        }
    }

}
