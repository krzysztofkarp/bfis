package com.bfis.movie.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bfis.movie.model.MovieGenre;

@Repository(value = "movieGenreRepository")
public interface MovieGenreRepository extends CrudRepository<MovieGenre, Long>{

}
