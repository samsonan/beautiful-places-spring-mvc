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

//TODO: int id to integer
@Entity
@Table(name="place_link")
public class PlaceLink {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;  
	
	@Column(name="site_name")
    private String siteName;

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

	public int getId() {
		return id;
	}

	public void setId(int id) {
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
