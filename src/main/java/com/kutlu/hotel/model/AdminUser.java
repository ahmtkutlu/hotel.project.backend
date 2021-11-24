package com.kutlu.hotel.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="admin_user")
public class AdminUser extends BaseModel implements Serializable {

	private static final long serialVersionUID = -1578480879950004983L;

	private String name;

	@Column(unique = true, nullable = false)
	private String mail;

	private String password;

	public AdminUser() {
	}

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}