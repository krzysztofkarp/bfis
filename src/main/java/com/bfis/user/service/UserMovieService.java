package com.bfis.user.service;

import java.util.List;

import com.bfis.movie.model.Movie;

public interface UserMovieService {
	
	List<Movie> userMoviesToWatch(Long userId);
	List<Movie> userMoviesWatched(Long userId);

}
