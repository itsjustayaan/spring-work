package com.training.model;

public class Movie {
	
	private int movieId;
	private String movieName;
	private String directorName;
	private int budget;
	
	
	public Movie(int movieId, String movieName, String directorName, int budget) {
		this.movieId = movieId;
		this.movieName = movieName;
		this.directorName = directorName;
		this.budget = budget;
	}
	
	public int getMovieId() {
		return movieId;
	}
	
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	
	public String getMovieName() {
		return movieName;
	}
	
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	
	public String getDirectorName() {
		return directorName;
	}
	
	public void setDirectorName(String directorName) {
		this.directorName = directorName;
	}
	
	public int getBudget() {
		return budget;
	}

	public void setBudget(int budget) {
		this.budget = budget;
	}

	@Override
	public String toString() {
		return "Movie [movieId=" + movieId + ", movieName=" + movieName + ", directorName=" + directorName + ", budget="
				+ budget + "]";
	}
	
}
