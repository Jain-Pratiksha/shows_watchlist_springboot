package com.shows_watchlist.model;

import com.shows_watchlist.utils.*;
import lombok.*;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.index.*;
import org.springframework.data.mongodb.core.mapping.*;

@Data
@Document(collection = "users")
public class User {

    @Id
    private String userId;
    @Indexed(unique = true)
    private String emailId;
    private String password;
    private Enums.Role role = Enums.Role.USER; // Default role is USER

}
