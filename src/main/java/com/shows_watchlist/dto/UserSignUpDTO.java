package com.shows_watchlist.dto;

import com.shows_watchlist.utils.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
public class UserSignUpDTO {

    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Invalid email format")
    private String emailId;
    @NotBlank(message = "Password cannot be empty")
    @Min(value = 8, message = "Password must be at least 8 characters long")
    @Max(value = 20, message = "Password must not exceed 20 characters")
    private String password;
    private Enums.Role role = Enums.Role.USER; // Default role is USER
}
