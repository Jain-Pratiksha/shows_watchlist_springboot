package com.shows_watchlist.executors;

import com.fasterxml.jackson.databind.*;
import com.shows_watchlist.dto.*;
import com.shows_watchlist.model.*;
import com.shows_watchlist.services.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Component
public class ShowWatchListExecutor {

    @Autowired
    private ShowWatchListService showWatchListService;

    @Autowired
    private ShowsService showsService;

    private static final Logger logger = LoggerFactory.getLogger(ShowWatchListExecutor.class);

    ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    public ShowWatchListResponseDTO addShow(ShowWatchListRequestDTO showWatchListRequestDTO) {
        if (!showsService.showExistsByTitle(showWatchListRequestDTO.getShowTitle())) {
            logger.info("Show with title '{}' does not exist", showWatchListRequestDTO.getShowTitle());
            return new ShowWatchListResponseDTO("Show does not exist in the shows");
        }
        ShowWatchList showWatchList = objectMapper.convertValue(showWatchListRequestDTO, ShowWatchList.class);
        showWatchListService.saveShowWatchListItem(showWatchList);
        ShowWatchListResponseDTO showWatchListResponseDTO = new ShowWatchListResponseDTO();
        showWatchListResponseDTO.setStatus("Show added to watchlist successfully");
        logger.info("Show added to watchlist: {}", showWatchList);
        return showWatchListResponseDTO;
    }

    public List<ShowWatchList> getAllShows() {
        return showWatchListService.getAllShows();
    }

    public ShowWatchList getShowById(String showId) {
        return showWatchListService.getByShowId(showId);
    }

    public ShowWatchListResponseDTO updateShowByShowTitle(ShowWatchListRequestDTO showWatchListRequestDTO) {
        ShowWatchList existingShow = showWatchListService.getByShowTitle(showWatchListRequestDTO.getShowTitle());
        if (existingShow == null) {
            logger.info("Show with title '{}' not found in watchlist", showWatchListRequestDTO.getShowTitle());
            return new ShowWatchListResponseDTO("Show not found in watchlist");
        }

        existingShow.setShowType(showWatchListRequestDTO.getShowType());
        existingShow.setShowPlatform(showWatchListRequestDTO.getShowPlatform());
        existingShow.setShowGenre(showWatchListRequestDTO.getShowGenre());
        existingShow.setShowWatchListStatus(showWatchListRequestDTO.getShowWatchListStatus());
        existingShow.setShowRating(showWatchListRequestDTO.getShowRating());
        existingShow.setNotes(showWatchListRequestDTO.getNotes());
        showWatchListService.saveShowWatchListItem(existingShow);

        logger.info("Show with title '{}' updated in watchlist", showWatchListRequestDTO.getShowTitle());
        return new ShowWatchListResponseDTO("Show updated in watchlist successfully");
    }

    public ShowWatchListResponseDTO deleteShowByShowTitle(String showTitle) {
        showWatchListService.deleteShowByTitle(showTitle);
        logger.info("Show with title '{}' deleted from watchlist", showTitle);
        return new ShowWatchListResponseDTO("Show deleted from watchlist successfully");
    }

}
