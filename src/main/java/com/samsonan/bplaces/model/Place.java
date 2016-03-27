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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.URL;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * TODO:
 * http://stackoverflow.com/questions/913025/best-spring-hibernate-configuration-for-never-changing-tables
 *  
 * 
 * 
 * @author ShamanXXI
 *
 */

@Entity
@Table(name="place")
public class Place { 
	
	public final static int STATUS_PENDING = 0; 
	public final static int STATUS_READY = 1; 
	public final static int STATUS_PUBLISHED = 2; 
	public final static int STATUS_APPROVED = 2; 

	public final static int CATEGORY_CULTURE = 1; //01
	public final static int CATEGORY_NATURE = 2;  //10
	public final static int CATEGORY_MIX = 3;     //11
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;  
	
	@NotEmpty
	@Column(name="title")
    private String title;

	@NotEmpty
	@Column(name="description")
    private String description;

	@Column(name="lat")
    private double lat; 

	@Column(name="lon")
    private double lon; 

	@Column(name="is_unesco")
    private boolean isUnesco; 
	
	@URL
	@Column(name="unesco_url")
    private String unescoUrl; 
	
	@Column(name="status")
    private int status;  

	@Column(name="category")
    private int category;  
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created")
	private Date created;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updated")
	private Date updated;

	@ManyToOne
	@JoinColumn(name="created_by")
	@JsonIgnore 
    private User createdBy; 

	@ManyToOne
	@JoinColumn(name="updated_by")
	@JsonIgnore 
    private User updatedBy; 
	
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name="PLACE_TYPE", joinColumns=@JoinColumn(name="PLACE_ID"))
	@Column(name="TYPE")
	private Set<String> placeTypes = new HashSet<String>(0);

	@OneToMany(mappedBy="place", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	@JsonIgnore 
	private List<PlaceLink> placeLinks = new ArrayList<PlaceLink>();

	@OneToMany(mappedBy="place", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = false)
	private Set<Image> placeImages = new HashSet<Image>();


	//TODO
	//@ManyToOne //eager by default
	//@JoinColumn(name="location_id", referencedColumnName="id")
	//private Location location;
	
	@ManyToOne //eager by default
    @JoinColumn(name="country_code", referencedColumnName="iso_2")
	private Country country;
	
	public Place(){
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
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
		return placeTypes;
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

	public int getImageCount() {
		if (placeImages != null)
			return placeImages.size();
		else return 0;
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

	//TODO: message.properties
	public String getStatusStr() {
		if (getStatus() == STATUS_PENDING)
			return "Pending";
		else if (getStatus() == STATUS_READY) 
			return "Ready";
		else if (getStatus() == STATUS_PUBLISHED) 
			return "Published";
		else return "Unknown";

	}
	
	public void setStatus(int status) {
		this.status = status;
	}
	
	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
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

	public Country getCountry(){
		return country;
	}

	public void setCountry(Country country){
		this.country = country;
	}

//	public Location getLocation() {
//	return location;
//}
	
//	public void setLocation(Location location) {
//		this.location = location;
//	}	

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
	
	public String getRegionCode(){
		if (country != null)
			return country.getSubRegionCode();
		else return null;
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
        return "Place [id=" + id + ", title="+title+", description=" + description + ""
        		+ ", lat=" + lat + ", lon=" + lon + ", status=" + status + "]";
    }
    	
	
} 
