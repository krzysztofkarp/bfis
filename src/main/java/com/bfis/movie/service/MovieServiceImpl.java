package com.bfis.movie.service;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bfis.apicaller.ApiCaller;
import com.bfis.common.Utils;
import com.bfis.model.MovieModel;
import com.bfis.movie.model.Movie;
import com.bfis.movie.model.MovieGenre;
import com.bfis.movie.model.MovieType;
import com.bfis.movie.repo.MovieGenreRepository;
import com.bfis.movie.repo.MovieRepository;


@Service
public class MovieServiceImpl implements MovieService {
	
	

	private MovieRepository repo;
	private MovieGenreRepository genreRepo;
	private ApiCaller caller;
	
	@Autowired
	public MovieServiceImpl(MovieRepository repo,MovieGenreRepository genreRepo, ApiCaller caller) {
		this.repo = repo;
		this.genreRepo = genreRepo;
		this.caller = caller;
	}
	
	
    @PostConstruct
    public void init() {
    	List<Movie> movies= caller.getMovies();
    	
    	
    	for(Movie m : movies) {
    		
    		MovieGenre genre = m.getGenre();
    		boolean exists =Utils.notNullAndNotEmpty( genreRepo.exists(genre.getName()));
    		if(!exists) {
    			genreRepo.save(genre);
    		}
    		
    		repo.save(m);
    		
    	}

    }
	

	@Override
	public List<Movie> getAll() {
		return Utils.asList(repo.findAll());
	}

	@Override
	public Movie add(Movie movie) {
		return repo.save(movie);
	}

	@Override
	public void remove(Movie movie) {
		repo.delete(movie);
	}

	@Override
	public List<Movie> getByType(MovieType type) {
		return Utils.asList(repo.findByType(type));
	}


	@Override
	public List<Movie> getByGenre(Long genreId) {
		return Utils.asList(repo.findByGenre(genreId));
	}

	@Override
	public List<MovieGenre> getAllGenres() {
		return Utils.asList(genreRepo.findAll());
	}

}
