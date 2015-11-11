package com.samsonan.bplaces.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="places")
public class Place { 
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;  
	
	@Column(name="title")
    private String mTitle;

	@Column(name="description")
    private String mDescription;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getTitle() {
		return mTitle;
	}
	
	public void setTitle(String title) {
		this.mTitle = title;
	}

	public String getDescription() {
		return mTitle;
	}
	
	public void setDescription(String description) {
		this.mDescription = description;
	}

} 
