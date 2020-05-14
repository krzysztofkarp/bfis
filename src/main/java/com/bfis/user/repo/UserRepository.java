package com.bfis.user.repo;

import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bfis.movie.model.Movie;
import com.bfis.user.model.SystemUser;




@Repository(value = "userRepository")
public interface UserRepository extends CrudRepository<SystemUser, Long>{
	
	@Query("from SystemUser u where u.username=:username")
	public SystemUser findByUsername(@Param("username") String username);
	
	@Query("select u.moviesWatched from SystemUser u where u.id=:id")
	public Set<Movie> findMoviesWatched(@Param("id") Long id);
	
	@Query("select u.moviesToWatch from SystemUser u where u.id=:id")
	public Set<Movie> findMoviesToWatch(@Param("id") Long id);
	
	@Query("from SystemUser u where u.email=:email")
	public SystemUser findByEmail(@Param("email") String email);

}
