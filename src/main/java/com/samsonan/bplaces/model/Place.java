package com.samsonan.bplaces.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.NotEmpty;

//TODO: int to Integers
@Entity
@Table(name="place")
public class Place { 
	
	public final static int STATUS_PENDING = 0; 
	public final static int STATUS_READY = 1; 
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;  
	
	@NotEmpty(message = "{NotEmpty.place.title}")
	@Column(name="title")
    private String title;

	@NotEmpty(message = "{NotEmpty.place.description}")
	@Column(name="description")
    private String description;

	@Column(name="lat")
    private double lat; 

	@Column(name="lon")
    private double lon; 

	@Column(name="is_unesco")
    private boolean isUnesco; 
	
	@Column(name="unesco_url")
    private String unescoUrl; 
	
	@Column(name="status")
    private int status;  
	
	@Column(name="created")
	private Date created;

	@Column(name="updated")
	private Date updated;

	@Column(name="country")
    private String country; //TODO: location/code 

	@ManyToOne
	@JoinColumn(name="created_by")
    private User createdBy; 

	@ManyToOne
	@JoinColumn(name="updated_by")
    private User updatedBy; 
	
	
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name="PLACE_TYPE", joinColumns=@JoinColumn(name="PLACE_ID"))
	@Column(name="TYPE")
	private Set<String> placeTypes = new HashSet<String>(0);
		
	@OneToMany(mappedBy="place", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	private List<PlaceLink> placeLinks = new ArrayList<PlaceLink>();

	@OneToMany(mappedBy="place", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = false)
	private Set<Image> placeImages = new HashSet<Image>();
	
	public Place(){
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLon() {
		return lon;
	}
	public void setLon(double lon) {
		this.lon = lon;
	}

	public Set<String> getPlaceTypes() {
		return this.placeTypes;
	}	
	
	public void setPlaceTypes(Set<String> placeTypes) {
		this.placeTypes = placeTypes;
	}

	public List<PlaceLink> getPlaceLinks() {
		return this.placeLinks;
	}	

	public void setPlaceLinks(List<PlaceLink> placeLinks) {
		this.placeLinks = placeLinks;
	}
	
	public Set<Image> getPlaceImages() {
		return placeImages;
	}

	public void setPlaceImages(Set<Image> placeImages) {
		this.placeImages = placeImages;
	}

	public boolean isUnesco() {
		return isUnesco;
	}

	public void setUnesco(boolean isUnesco) {
		this.isUnesco = isUnesco;
	}

	public String getUnescoUrl() {
		return unescoUrl;
	}

	public void setUnescoUrl(String unescoUrl) {
		this.unescoUrl = unescoUrl;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}	
	
	public String getLocationPath(){
		return "China";
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public User getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(User updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Override
    public int hashCode() {
		return new HashCodeBuilder(19, 39).
			       append(title).
			       append(lat).
			       append(lon).
			       toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (obj.getClass() != getClass()) {
			return false;
		}
		Place other = (Place) obj;
		return new EqualsBuilder().appendSuper(super.equals(obj)).append(title, other.title)
				.append(lat, other.lat)
				.append(lon, other.lon)
				.isEquals();
	}
 
    @Override
    public String toString() {
        return "User [id=" + id + ", title="+title+", description=" + description + ""
        		+ ", lat=" + lat + ", lon=" + lon + ", status=" + status + "]";
    }
    	
	
} 
