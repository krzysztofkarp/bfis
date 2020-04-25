package com.bfis.movie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bfis.common.BackendMappings;
import com.bfis.common.RequestParams;
import com.bfis.common.response.ItemResponse;
import com.bfis.common.response.ItemsResponse;
import com.bfis.common.response.Response;
import com.bfis.common.response.ResponseUtil;
import com.bfis.movie.model.Movie;
import com.bfis.movie.model.MovieType;
import com.bfis.movie.service.MovieService;

@RestController
public class MovieController {
	
	
	@Autowired
	private MovieService service;
	
	
	
	 @RequestMapping(value = BackendMappings.Movie.ALL, method = RequestMethod.GET)
	 public ItemsResponse<Movie> all() {
	        return ResponseUtil.runInMultiTemplate(() -> service.getAll());
	  }
	
	 
	 @RequestMapping(value = BackendMappings.Movie.ADD, method = RequestMethod.POST)
	 public ItemResponse<Movie> add(@RequestBody Movie movie) {
	        return ResponseUtil.runInItemTemplate(() -> service.add(movie));
	  }
	 
	 @RequestMapping(value = BackendMappings.Movie.REMOVE, method = RequestMethod.POST)
	 public Response remove(@RequestBody Movie movie) {
	        return ResponseUtil.runInVoidTemplate(() -> service.remove(movie));
	  }
	 
	 @RequestMapping(value = BackendMappings.Movie.BY_GENRE, method = RequestMethod.GET)
	 public ItemsResponse<Movie> byGenre(@RequestParam(RequestParams.P_GENRE_ID)Long id) {
	        return ResponseUtil.runInMultiTemplate(() -> service.getByGenre(id));
	  }
	 
	 @RequestMapping(value = BackendMappings.Movie.BY_TYPE, method = RequestMethod.GET)
	 public ItemsResponse<Movie> byType(@RequestParam(RequestParams.P_MOVIE_TYPE)MovieType type) {
	        return ResponseUtil.runInMultiTemplate(() -> service.getByType(type));
	  }

}
