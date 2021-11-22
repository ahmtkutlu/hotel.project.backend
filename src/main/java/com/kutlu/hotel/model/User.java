package com.kutlu.hotel.model;

import java.io.Serializable;

import javax.persistence.Entity;

@Entity
public class User extends BaseModel implements Serializable{

	private static final long serialVersionUID = -1578480879950004983L;
	
	private String name;
	private String mail;

	public User() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

}