package com.bfis.movie.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.bfis.model.Model;
import com.bfis.user.model.SystemUser;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "movie")
public class Movie extends Model{
	
	
	private String title;
	private String description;
	private String imageUrl;
	private MovieType type;
	
	
	
	@OneToMany(
	mappedBy = "movie",
	fetch = FetchType.LAZY,
	cascade = CascadeType.ALL,
    orphanRemoval = false)
	@JsonIgnore
	private Set<MovieRate> rates = new HashSet<MovieRate>();
	
	@ManyToMany(mappedBy="moviesToWatch")
	private Set<SystemUser> viewers  = new HashSet<SystemUser>();
	
	@ManyToMany(mappedBy="moviesWatched")
	private Set<SystemUser> raters  = new HashSet<SystemUser>();
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "movie_genre_id")
	@JsonIgnore
	private MovieGenre genre;
	
	
	
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public MovieType getType() {
		return type;
	}
	public void setType(MovieType type) {
		this.type = type;
	}
	public Set<MovieRate> getRates() {
		return rates;
	}
	public void setRates(Set<MovieRate> rates) {
		this.rates = rates;
	}
	public Set<SystemUser> getViewers() {
		return viewers;
	}
	public void setViewers(Set<SystemUser> viewers) {
		this.viewers = viewers;
	}
	public Set<SystemUser> getRaters() {
		return raters;
	}
	public void setRaters(Set<SystemUser> raters) {
		this.raters = raters;
	}
	
	public void addViewer(SystemUser user) {
		this.viewers.add(user);
		user.getMoviesToWatch().add(this);
	}
	
	public void removeViewer(SystemUser user) {
		this.viewers.remove(user);
		user.getMoviesToWatch().remove(this);
	}
	
	public void addRater(SystemUser user) {
		this.raters.add(user);
		user.getMoviesWatched().add(this);
	}
	
	public void removeRater(SystemUser user) {
		this.raters.remove(user);
		user.getMoviesWatched().remove(this);
	}
	
	public void addRate(MovieRate rate) {
		this.rates.add(rate);
		rate.setMovie(this);
	}
	
	public void removeRate(MovieRate rate) {
		this.rates.remove(rate);
		rate.setMovie(null);
	}
	
	
	
	
	
	
	

}
