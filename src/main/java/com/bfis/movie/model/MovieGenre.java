package com.bfis.movie.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.bfis.model.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "movie_genre")
public class MovieGenre extends Model{
	
	@NotNull
	private String name;
	
	@OneToMany(
	mappedBy = "genre",
	fetch = FetchType.LAZY,
	cascade = CascadeType.ALL,
    orphanRemoval = false)
	@JsonIgnore
	private Set<Movie> movies = new HashSet<Movie>();
	
	
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
	
	

}
