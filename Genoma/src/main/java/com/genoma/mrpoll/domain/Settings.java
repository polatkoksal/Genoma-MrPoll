package com.genoma.mrpoll.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the "Settings" database table.
 * 
 */
@Entity
@Table(name="\"Settings\"")
@NamedQuery(name="Settings.findAll", query="SELECT s FROM Settings s")
public class Settings implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(length=255)
	private String description;

	@Column(length=255)
	private String key;

	public Settings() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getKey() {
		return this.key;
	}

	public void setKey(String key) {
		this.key = key;
	}

}