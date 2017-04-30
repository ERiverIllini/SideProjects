package com.ninja.emilior.movies;

/**
 * Created by emilio on 3/28/17.
 */

public class APIKey {
    private static String url = "https://api.themoviedb.org/3/movie/popular?" +
            "api_key=";
    private static String givenKey = "On a need to know basis only";

    public static String APIKey(){
        return url + givenKey +"&language=en-US&page=";
    }
}
