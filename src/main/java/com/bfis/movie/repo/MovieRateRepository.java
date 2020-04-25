package com.bfis.movie.repo;

import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.bfis.movie.model.MovieRate;

@Repository(value = "movieRateRepository")
public interface MovieRateRepository extends CrudRepository<MovieRate, Long>{
	
	
	@Query("from MovieRate mr where mr.movie.id=:movieId")
	public Set<MovieRate> ratesByMovieId(@Param("movieId") Long movieId);

}
