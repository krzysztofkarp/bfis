package com.bfis.model;

import java.util.List;

public class MovieModel {
	
	private String id, name;
	private List<String> genres;
	private String summary;
	
	private MovieModelImages image;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getGenres() {
		return genres;
	}

	public void setGenres(List<String> genres) {
		this.genres = genres;
	}

	public MovieModelImages getImage() {
		return image;
	}

	public void setImage(MovieModelImages image) {
		this.image = image;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}
	
	
	
	
	

}
