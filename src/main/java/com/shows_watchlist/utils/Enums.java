package com.shows_watchlist.utils;

public class Enums {

    public enum showType {
        MOVIE,
        TV_SHOW,
        SERIES
    }

    public enum showStatus {
        RELEASED,
        ABOUT_TO_RELEASE,
    }

    public enum showWatchListStatus {
        WATCHED,
        PLAN_TO_WATCH,
        DROPPED
    }

    public enum Role {
        USER,
        ADMIN
    }
}
