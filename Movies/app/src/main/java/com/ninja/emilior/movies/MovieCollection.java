package com.ninja.emilior.movies;

import java.util.ArrayList;

/**
 * Created by emilio on 3/28/17.
 */

public class MovieCollection {
    private int page;
    private ArrayList<Movie> results;
    private int total_results;

    public int getTotal_results(){
        return total_results;
    }

    public int getPage() {
        return page;
    }

    public ArrayList<Movie> getResults() {
        return results;
    }

}
