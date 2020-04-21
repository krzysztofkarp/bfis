package com.bfis.movie;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "movie_rate")
public class MovieRate {
	
	
	private int rate;

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}
	
	
	
	

}
