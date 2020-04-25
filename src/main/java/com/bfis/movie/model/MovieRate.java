package com.bfis.movie.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.bfis.model.Model;
import com.bfis.user.model.SystemUser;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "movie_rate")
public class MovieRate extends Model{
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "systemUser_id")
	@JsonIgnore
	private SystemUser user;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "movie_id")
	@JsonIgnore
	private Movie movie;
	
	
	private int rate;

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	public SystemUser getUser() {
		return user;
	}

	public void setUser(SystemUser user) {
		this.user = user;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}
	
	
	
	
	
	

}
