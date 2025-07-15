package com.shows_watchlist.model;

import com.shows_watchlist.utils.*;
import lombok.*;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.index.*;
import org.springframework.data.mongodb.core.mapping.*;

import java.util.*;

@Data
@Document(collection = "showwatchlist")
public class ShowWatchList {

    @Id
    private String showId;
    @Indexed(unique = true)
    private String showTitle;
    private Enums.showType showType;
    private String showPlatform;
    private List<String> showGenre;
    private Enums.showWatchListStatus showWatchListStatus;
    private int showRating;
    private String notes;
    private Date createdDate = new Date();

}
