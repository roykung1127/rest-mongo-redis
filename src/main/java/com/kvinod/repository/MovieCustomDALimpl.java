package com.kvinod.repository;


import java.time.LocalDateTime;
import java.util.List;

import com.kvinod.entity.Movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import org.springframework.stereotype.Repository;

@Repository
public class MovieCustomDALimpl implements MovieCustomDAL{

    private final MongoTemplate mongoTemplate;

    @Autowired
    public MovieCustomDALimpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Movie saveMovie(Movie movie) {
        movie.setCreateDate(LocalDateTime.now());
        mongoTemplate.insert(movie);
        return movie;
    }

    @Override
    public List<Movie> findAll() {
        return mongoTemplate.findAll(Movie.class);
    }
 
}
