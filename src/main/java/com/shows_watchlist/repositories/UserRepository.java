package com.shows_watchlist.repositories;

import com.shows_watchlist.model.*;
import com.shows_watchlist.utils.*;
import org.springframework.data.mongodb.repository.*;
import org.springframework.stereotype.*;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    @Query("{'emailId': ?0}")
    User findByEmailId(String emailId);

    @Query(value = "{'emailId': ?0, 'role': ?1}")
    User findByEmailAndRole(String emailId, String role);
}
