package com.samsonan.bplaces.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="place")
public class Place { 
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;  
	
	@Column(name="title")
    private String mTitle;

	@Column(name="description")
    private String mDescription;

	@Column(name="lat")
    private double mLat; 

	@Column(name="lon")
    private double mLon; 

	@Column(name="is_unesco")
    private boolean mIsUnesco; 
	
	//http://websystique.com/springmvc/springmvc-hibernate-many-to-many-example-annotation-using-join-table/
	@ManyToMany(cascade = CascadeType.ALL, targetEntity=PlaceType.class, fetch = FetchType.EAGER) //TODO:lazy
	@JoinTable(name = "PLACE_2_TYPE", joinColumns = { @JoinColumn(name = "PLACE_ID") }, inverseJoinColumns = { @JoinColumn(name = "PLACE_TYPE_ID") })
	private Set<PlaceType> placeTypes = new HashSet<PlaceType>(0);
	
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
	public double getLat() {
		return mLat;
	}
	public void setLat(double lat) {
		this.mLat = lat;
	}
	public double getLon() {
		return mLon;
	}
	public void setLon(double lon) {
		this.mLon = lon;
	}
	public boolean isUnesco() {
		return mIsUnesco;
	}
	public void setUnesco(boolean isUnesco) {
		this.mIsUnesco = isUnesco;
	}

	public Set<PlaceType> getPlaceTypes() {
		return this.placeTypes;
	}	
	
	
} 
