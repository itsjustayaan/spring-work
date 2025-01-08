package com.training.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.model.Movie;

@RestController
@RequestMapping("movies")
public class MovieController {
	
	@GetMapping
	public String getMovies() {
		return "get all Movies";
	}
	
	@GetMapping("{movieId}")
	public Movie getSingleMovies(@PathVariable("movieId")int movieId) {
		return new Movie(movieId,"Ayaan","Meeting",813053736);
	}
	
	@GetMapping("findMovie/{movieName}")
	public Movie findMovie(@PathVariable("movieName")String movieName) {
		return new Movie(2121,movieName,"Meeting",813053736);
	}
	
 	@GetMapping("findMovie/{fromId}/{toId}")
	public String findMovie(@PathVariable("fromId")int movieid1, @PathVariable("toId")int movieid2) {
		return "Movie found from "+ movieid1 +" to "+ movieid2;
	}
	
	@PostMapping	
	public String saveMovies(@RequestBody Movie movie) {
		return "Movie Saved "+ movie;
	}
	
	@DeleteMapping("{movieId}")
	public String deleteMovies(@PathVariable("movieId")int movieId) {
		return "Movie Delete: "+movieId;
	}
	
	@PutMapping
	public String updateMovies(@RequestBody Movie movie) {
		return "Movie Updated "+ movie;
	}
	
	@PutMapping("{movieId}")
	public String updateMovies(@PathVariable("movieId")int movieId, @RequestBody Movie movie) {
		return "Movie Updated by id: "+ movieId+" Movie Details: "+movie;
	}
	
}
