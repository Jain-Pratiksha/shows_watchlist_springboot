package com.shows_watchlist.controllers;

import com.shows_watchlist.dto.*;
import com.shows_watchlist.executors.*;
import com.shows_watchlist.model.*;
import org.slf4j.*;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.*;
import org.springframework.util.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.logging.*;

@RestController
@RequestMapping("/shows")
public class ShowsController {

    @Autowired
    private ShowsExecutor showsExecutor;

    private static final Logger logger = LoggerFactory.getLogger(ShowsController.class);

    @PostMapping("/add")
    public ShowResponseDTO addShow(@RequestBody ShowRequestDTO showRequestDTO) {
        logger.info("Adding show to watchlist: {}", showRequestDTO);
        return showsExecutor.addShow(showRequestDTO);
    }

    @GetMapping("/allShows")
    public List<Show> getAllShows() {
        logger.info("Fetching all shows from watchlist");
        return showsExecutor.getAllShows();
    }

    @GetMapping("/show")
    public Show getShowById(@RequestParam String showId) {
        logger.info("Fetching show with showId: {}", showId);
        if (!StringUtils.hasText(showId)) {
            logger.error("showID is required to fetch the show details");
            return null;
        }
        return showsExecutor.getShowById(showId);
    }

    @PutMapping("/update")
    public ShowResponseDTO updateShowByTitle(@RequestBody ShowRequestDTO showWatchListRequestDTO) {
        logger.info("Updating show in watchlist: {}", showWatchListRequestDTO);
        return showsExecutor.updateShowByShowTitle(showWatchListRequestDTO);
    }

    @DeleteMapping("/delete")
    public ShowResponseDTO deleteShowByTitle(@RequestParam String showTitle) {
        logger.info("Deleting show from watchlist with title: {}", showTitle);
        return showsExecutor.deleteShowByShowTitle(showTitle);
    }

}
