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
public class ShowsExecutor {

    @Autowired
    private ShowsService showsService;

    private static final Logger logger = LoggerFactory.getLogger(ShowsExecutor.class);

    ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    public ShowResponseDTO addShow(ShowRequestDTO showRequestDTO) {
        Show show = objectMapper.convertValue(showRequestDTO, Show.class);
        showsService.saveShow(show);
        ShowResponseDTO showResponseDTO = new ShowResponseDTO();
        showResponseDTO.setStatus("Show added successfully");
        logger.info("Show added: {}", show);
        return showResponseDTO;
    }

    public List<Show> getAllShows() {
        return showsService.getAllShows();
    }

    public Show getShowById(String showId) {
        return showsService.getByShowId(showId);
    }

    public ShowResponseDTO updateShowByShowTitle(ShowRequestDTO showRequestDTO) {
        Show existingShow = showsService.getByShowTitle(showRequestDTO.getShowTitle());
        if (existingShow == null) {
            logger.info("Show with title '{}' not found", showRequestDTO.getShowTitle());
            return new ShowResponseDTO("Show not found");
        }

        existingShow.setShowType(showRequestDTO.getShowType());
        existingShow.setShowPlatform(showRequestDTO.getShowPlatform());
        existingShow.setShowGenre(showRequestDTO.getShowGenre());
        existingShow.setShowStatus(showRequestDTO.getShowStatus());
        showsService.saveShow(existingShow);

        logger.info("Show with title '{}' updated", showRequestDTO.getShowTitle());
        return new ShowResponseDTO("Show updated successfully");
    }

    public ShowResponseDTO deleteShowByShowTitle(String showTitle) {
        showsService.deleteShowByTitle(showTitle);
        logger.info("Show with title '{}' deleted", showTitle);
        return new ShowResponseDTO("Show deleted successfully");
    }

}
