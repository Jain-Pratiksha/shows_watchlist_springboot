package com.shows_watchlist.controllers;

import com.shows_watchlist.dto.*;
import com.shows_watchlist.executors.*;
import com.shows_watchlist.model.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.util.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/watchlist")
public class ShowWatchListController {

    @Autowired
    private ShowWatchListExecutor showWatchListExecutor;

    private static final Logger logger = LoggerFactory.getLogger(ShowWatchListController.class);

    @PostMapping("/add")
    public ShowWatchListResponseDTO addShowWatchList(@RequestBody ShowWatchListRequestDTO showWatchListRequestDTO) {
        logger.info("Adding show to watchlist: {}", showWatchListRequestDTO);
        return showWatchListExecutor.addShow(showWatchListRequestDTO);
    }

    @GetMapping("/allShows")
    public List<ShowWatchList> getAllShows() {
        logger.info("Fetching all shows from watchlist");
        return showWatchListExecutor.getAllShows();
    }

    @GetMapping("/show")
    public ShowWatchList getShowById(@RequestParam String showId) {
        logger.info("Fetching show with showId: {}", showId);
        if (!StringUtils.hasText(showId)) {
            logger.error("showID is required to fetch the show details");
            return null;
        }
        return showWatchListExecutor.getShowById(showId);
    }

    @PutMapping("/update")
    public ShowWatchListResponseDTO updateShowWatchListByTitle(@RequestBody ShowWatchListRequestDTO showWatchListRequestDTO) {
        logger.info("Updating show in watchlist: {}", showWatchListRequestDTO);
        return showWatchListExecutor.updateShowByShowTitle(showWatchListRequestDTO);
    }

    @DeleteMapping("/delete")
    public ShowWatchListResponseDTO deleteShowWatchListByTitle(@RequestParam String showTitle) {
        logger.info("Deleting show from watchlist with title: {}", showTitle);
        return showWatchListExecutor.deleteShowByShowTitle(showTitle);
    }

}
