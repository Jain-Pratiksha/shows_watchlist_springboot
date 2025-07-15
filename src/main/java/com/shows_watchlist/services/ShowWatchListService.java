package com.shows_watchlist.services;

import com.shows_watchlist.model.*;
import com.shows_watchlist.repositories.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class ShowWatchListService {

    @Autowired
    ShowWatchListRepository showWatchListRepository;

    public void saveShowWatchListItem(ShowWatchList showWatchList) {
        showWatchListRepository.save(showWatchList);
    }

    public List<ShowWatchList> getAllShows() {
        return showWatchListRepository.findAll();
    }

    public ShowWatchList getByShowId(String showId) {
        return showWatchListRepository.findById(showId).orElse(null);
    }

    public ShowWatchList getByShowTitle(String showTitle) {
        return showWatchListRepository.findByShowTitle(showTitle);
    }

    public void deleteShowByTitle(String showTitle) {
        showWatchListRepository.deleteShowByTitle(showTitle);
    }
}
