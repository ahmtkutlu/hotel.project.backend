package com.kutlu.hotel.model;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
public class BaseModel implements Serializable {

	private static final long serialVersionUID = -5832983605723606838L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private Long id;
	private boolean status;
	
	public BaseModel() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

}