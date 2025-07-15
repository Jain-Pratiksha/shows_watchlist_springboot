package com.shows_watchlist.dto;

import com.shows_watchlist.utils.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShowWatchListRequestDTO {

    private String showTitle;
    private Enums.showType showType;
    private String showPlatform;
    private List<String> showGenre;
    private Enums.showWatchListStatus showWatchListStatus;
    @Min(value = 1, message = "Show rating must be between 1-10")
    @Max(10)
    private int showRating;
    private String notes;

}
