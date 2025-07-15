package com.shows_watchlist.repositories;

import com.shows_watchlist.model.*;
import org.springframework.data.mongodb.repository.*;
import org.springframework.stereotype.*;

@Repository
public interface ShowsRepository extends MongoRepository<Show, String> {

    @Query(value = "{'showTitle': ?0}")
    Show findByShowTitle(String showTitle);

    @Query(value = "{'showTitle': ?0}", delete = true)
    void deleteShowByTitle(String showTitle);

    @Query(value = "{'showTitle': ?0}", exists = true)
    boolean existsByShowTitle(String showTitle);
}
