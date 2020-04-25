package com.bfis.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bfis.common.Utils;
import com.bfis.movie.model.Movie;
import com.bfis.user.repo.UserRepository;


@Service
public class UserMovieServiceImpl implements UserMovieService {
	
	
	@Autowired
	private UserRepository repo;

	@Override
	public List<Movie> userMoviesToWatch(Long userId) {
		return Utils.asList(repo.findMoviesToWatch(userId));
	}

	@Override
	public List<Movie> userMoviesWatched(Long userId) {
		return Utils.asList(repo.findMoviesWatched(userId));
	}

}
