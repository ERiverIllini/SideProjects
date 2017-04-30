package com.ninja.emilior.movies;

/**
 * Created by emilio on 3/28/17.
 */

public class Movie {
    private static final String IMAGE_PATH = "http://image.tmdb.org/t/p/";
    private static final String IMAGE_SIZE = "w185";
    private double vote_average;
    private double popularity;
    private int[] genre_ids;
    private String release_date;
    private String genre_name;
    private String title;
    private String poster_path;
    private String overview;

    public String getOverview() {
        return overview;
    }

    public String getPoster_path() {
        return IMAGE_PATH+IMAGE_SIZE+poster_path;
    }

    public double getVote_average() {
        return vote_average;
    }

    public double getPopularity() {
        return popularity;
    }

    public int[] getGenre_ids() {
        return genre_ids;
    }

    public String getGenre_name() {
        return genre_name;
    }

    public String getTitle() {
        return title;
    }

    public String getRelease_date() {
        return release_date;
    }


}
