package com.bfis.movie.service;

import java.util.List;

import com.bfis.movie.model.Movie;
import com.bfis.movie.model.MovieGenre;
import com.bfis.movie.model.MovieType;

public interface MovieService {
	

	List<Movie> getAll();
	Movie add(Movie movie);
	void remove(Movie movie);
	List<Movie> getByType(MovieType type);
	List<Movie> getByGenre(Long genreId);
	List<MovieGenre> getAllGenres();

}
