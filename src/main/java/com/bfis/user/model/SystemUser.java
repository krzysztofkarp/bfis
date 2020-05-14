package com.bfis.user.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.bfis.model.Model;
import com.bfis.movie.model.Movie;
import com.bfis.movie.model.MovieRate;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "system_user")
public class SystemUser extends Model {
	
	@NotNull
	private String username;
	
	@NotNull
	private String password;
	
	@NotNull
	private String salt;
	
	@NotNull
	private String email;
	
	private String resetHash;
	
	
	
	
	@OneToMany(
	mappedBy = "user",
	fetch = FetchType.LAZY,
	cascade = CascadeType.ALL,
    orphanRemoval = false)
	@JsonIgnore
	private Set<MovieRate> rates = new HashSet<MovieRate>();
	
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(
			name = "user_friend", 
			joinColumns = @JoinColumn(name = "systemUser_id"), 
			inverseJoinColumns = @JoinColumn(name = "friend_id"))
	@JsonIgnore
	private Set<SystemUser> friends = new HashSet<SystemUser>();
	
	@ManyToMany(mappedBy="friends")
	private Set<SystemUser> colleagues  = new HashSet<SystemUser>();
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(
			name = "user_movie_to_watch", 
			joinColumns = @JoinColumn(name = "systemUser_id"), 
			inverseJoinColumns = @JoinColumn(name = "movie_id"))
	@JsonIgnore
	private Set<Movie> moviesToWatch = new HashSet<Movie>();
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(
			name = "user_movie_watched", 
			joinColumns = @JoinColumn(name = "systemUser_id"), 
			inverseJoinColumns = @JoinColumn(name = "movie_id"))
	@JsonIgnore
	private Set<Movie> moviesWatched = new HashSet<Movie>();
	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}
	
	
	public String getResetHash() {
		return resetHash;
	}

	public void setResetHash(String resetHash) {
		this.resetHash = resetHash;
	}

	public Set<MovieRate> getRates() {
		return rates;
	}

	public void setRates(Set<MovieRate> rates) {
		this.rates = rates;
	}

	public Set<SystemUser> getFriends() {
		return friends;
	}

	public void setFriends(Set<SystemUser> friends) {
		this.friends = friends;
	}

	public Set<SystemUser> getColleagues() {
		return colleagues;
	}

	public void setColleagues(Set<SystemUser> colleagues) {
		this.colleagues = colleagues;
	}

	public Set<Movie> getMoviesToWatch() {
		return moviesToWatch;
	}

	public void setMoviesToWatch(Set<Movie> moviesToWatch) {
		this.moviesToWatch = moviesToWatch;
	}

	public Set<Movie> getMoviesWatched() {
		return moviesWatched;
	}

	public void setMoviesWatched(Set<Movie> moviesWatched) {
		this.moviesWatched = moviesWatched;
	}
	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void addMovieToWatch(Movie movie) {
		this.moviesToWatch.add(movie);
		movie.getViewers().add(this);
	}
	
	public void removeMovieToWatch(Movie movie) {
		this.moviesToWatch.remove(movie);
		movie.getViewers().remove(this);
	}
	
	public void addWatchedMovie(Movie movie) {
		this.moviesWatched.add(movie);
		movie.getRaters().add(this);
	}
	
	public void removeWatchedMovie(Movie movie) {
		this.moviesWatched.remove(movie);
		movie.getRaters().remove(this);
	}
	
	public void addFriend(SystemUser user) {
		this.friends.add(user);
		user.getColleagues().add(this);
	}
	
	public void removeFriend(SystemUser user) {
		this.friends.remove(user);
		user.getColleagues().remove(this);
	}
	
	public void addRate(MovieRate rate) {
		this.rates.add(rate);
		rate.setUser(this);
	}
	
	public void removeRate(MovieRate rate) {
		this.rates.remove(rate);
		rate.setUser(null);
	}
	
	
	
	

}
