package com.kutlu.hotel.model;

import java.io.Serializable;

import javax.persistence.Entity;

@Entity
public class Hotel extends BaseModel implements Serializable{

	private static final long serialVersionUID = -5454685628840176412L;
	
	private String name;

	public Hotel() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}