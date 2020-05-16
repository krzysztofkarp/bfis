package com.bfis.apicaller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bfis.common.Utils;
import com.bfis.model.MovieModel;
import com.bfis.movie.model.Movie;
import com.bfis.movie.model.MovieGenre;

@Service
public class ApiCaller {
	
	
	@Autowired
	private RestTemplate restTemplate;
	
	final String ROOT_URI = "http://api.tvmaze.com/shows?page=1&fbclid=IwAR2AWNhIZDIlZXRwcJPrhtu35o9p4WTAVyqEHnoVSVAPRlc9dTmUupj75vc";

	
	@PostConstruct
	private void init() {
		restTemplate = new RestTemplate();
	}
	
	
	private List<MovieModel> callForData() {
		ResponseEntity<MovieModel[]> response = restTemplate.getForEntity(ROOT_URI, MovieModel[].class);
		List<MovieModel> list = Arrays.asList(response.getBody());
		return list;

	}
	
	public List<Movie> getMovies(){
		List<MovieModel> lst = callForData();
		List<Movie> movies = new ArrayList<Movie>();
		lst = lst.stream().filter(m -> Utils.notNullAndNotEmpty(m.getGenres())).collect(Collectors.toList());
		lst.forEach(m -> {
			Movie nm = new Movie();
			nm.setTitle(m.getName());
			nm.setId(Long.parseLong(m.getId()));
			nm.setImageUrl(m.getImage().getOriginal());
			nm.setGenre(new MovieGenre(m.getGenres().get(0)));
			nm.setDescription(m.getSummary());
			movies.add(nm);
		});
		
		return movies;
	}

	
}
