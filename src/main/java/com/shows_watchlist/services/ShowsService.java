package com.shows_watchlist.services;

import com.shows_watchlist.model.*;
import com.shows_watchlist.repositories.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class ShowsService {

    @Autowired
    ShowsRepository showsRepository;

    public void saveShow(Show show) {
        showsRepository.save(show);
    }

    public List<Show> getAllShows() {
        return showsRepository.findAll();
    }

    public Show getByShowId(String showId) {
        return showsRepository.findById(showId).orElse(null);
    }

    public Show getByShowTitle(String showTitle) {
        return showsRepository.findByShowTitle(showTitle);
    }

    public void deleteShowByTitle(String showTitle) {
        showsRepository.deleteShowByTitle(showTitle);
    }

    public boolean showExistsByTitle(String showTitle) {
        return showsRepository.existsByShowTitle(showTitle);
    }
}
