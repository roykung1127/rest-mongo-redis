package com.kvinod.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kvinod.entity.Movie;
import com.kvinod.repository.MovieCustomDAL;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/movie")
public class MovieController {

	@Autowired
	MovieCustomDAL mt_repo;

	// @Cacheable(value = "users")
	// @GetMapping
	// public List<User> get() {
	// 	log.info("returning list of all users");
		
	// 	return repo.findAll();
	// }

	@GetMapping
	public List<Movie> get() {
		log.info("returning list of all users");
		
		return mt_repo.findAll();
	}

	@PostMapping
	public Movie save(@RequestBody Movie movie) {
		log.info("saving movie details: " + movie.toString());
		mt_repo.saveMovie(movie);
		return movie;
	}



}
