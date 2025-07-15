package com.shows_watchlist.dto;

import com.shows_watchlist.utils.*;
import lombok.*;

import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShowRequestDTO {

    private String showTitle;
    private Enums.showType showType;
    private String showPlatform;
    private List<String> showGenre;
    private Enums.showStatus showStatus;
    private String description;
    private Enums.Role role;

}
