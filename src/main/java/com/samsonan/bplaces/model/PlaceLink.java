package com.samsonan.bplaces.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.URL;

/**
 * TODO: check url availability
 * TODO: should be filled both url and name or none
 * @author ShamanXXI
 *
 */
@Entity
@Table(name="place_link")
public class PlaceLink {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;  
	
	@NotEmpty(message = "{NotEmpty.place_link.name}")
	@Column(name="site_name")
    private String siteName;


	@URL(message="{URL.place_link.url}")
	@Column(name="url")
    private String url;

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name="PLACE_ID")
	private Place place;	
	
	public PlaceLink(){
	}

	public PlaceLink(String siteName, String url){
		this.siteName = siteName;
		this.url = url;
	}

	public Place getPlace() {
		return place;
	}

	public void setPlace(Place place) {
		this.place = place;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
    public int hashCode() {
		return new HashCodeBuilder(19, 39).
			       append(siteName).
			       append(url).
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
		PlaceLink other = (PlaceLink) obj;
		return new EqualsBuilder().appendSuper(super.equals(obj)).append(siteName, other.siteName)
				.append(url, other.url)
				.isEquals();
	}
 
    @Override
    public String toString() {
        return "User [id=" + id + ", siteName="+siteName+", url=" + url + "]";
    }
	
	
	
}
