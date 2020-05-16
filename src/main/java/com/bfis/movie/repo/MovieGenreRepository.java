package com.bfis.movie.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.bfis.movie.model.MovieGenre;

@Repository(value = "movieGenreRepository")
public interface MovieGenreRepository extends CrudRepository<MovieGenre, Long>{
	
	
	@Query("from MovieGenre m where m.name=:name")
	public MovieGenre exists(@Param("name") String name);

}
