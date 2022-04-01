package com.kvinod.repository;

import java.util.List;

import com.kvinod.entity.Movie;

public interface MovieCustomDAL {
    public Movie saveMovie(Movie movie);
    public List<Movie> findAll();
}
