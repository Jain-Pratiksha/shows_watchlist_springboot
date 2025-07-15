package com.shows_watchlist.repositories;

import com.shows_watchlist.model.*;
import org.springframework.data.mongodb.repository.*;
import org.springframework.stereotype.*;

@Repository
public interface ShowWatchListRepository extends MongoRepository<ShowWatchList, String> {

    @Query(value = "{'showTitle': ?0}")
    ShowWatchList findByShowTitle(String showTitle);

    @Query(value = "{'showTitle': ?0}", delete = true)
    void deleteShowByTitle(String showTitle);
}
