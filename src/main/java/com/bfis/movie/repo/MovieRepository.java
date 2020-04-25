package com.bfis.movie.repo;

import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bfis.movie.model.Movie;
import com.bfis.movie.model.MovieType;

@Repository(value = "movieRepository")
public interface MovieRepository extends CrudRepository<Movie, Long> {
	
	
	@Query("from Movie m where m.type=:type")
	public Set<Movie> findByType(@Param("type") MovieType type);
	
	@Query("from Movie m where m.genre.id=:genreId")
	public Set<Movie> findByGenre(@Param("genreId") Long genreId);
	

}
