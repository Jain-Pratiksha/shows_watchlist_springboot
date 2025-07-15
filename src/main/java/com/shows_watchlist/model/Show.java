package com.shows_watchlist.model;

import com.shows_watchlist.utils.*;
import lombok.*;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.index.*;
import org.springframework.data.mongodb.core.mapping.*;

import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "shows")
public class Show {

    @Id
    private String showId;
    @Indexed(unique = true)
    private String showTitle;
    private Enums.showType showType;
    private String showPlatform;
    private List<String> showGenre;
    private Enums.showStatus showStatus;
    private String description;
    private Date createdDate = new Date();

}
