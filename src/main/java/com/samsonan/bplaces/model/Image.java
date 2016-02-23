package com.samsonan.bplaces.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
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

@Entity
@Table(name = "image")
public class Image implements Serializable {
    
	private static final long serialVersionUID = -6765765909373091343L;

	public final static String CONTENT_TYPE_URL = "URL";
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name="file_name", nullable=false)
    private String filename;

    @Column(name="title")
    private String title;
    
    @Column(name="description")
    private String description;

    @Column(name = "content_type")
    private String contentType;
    
    @Column(name = "created")
    private Date created;
    
    @Column(name = "updated")
    private Date updated;

    @Column(name = "thumb_file_name")
    private String thumbnailFileName;
    
    //Notes: http://stackoverflow.com/questions/3677380/proper-hibernate-annotation-for-byte
    @Basic(fetch = FetchType.LAZY)
    @Column(name="content", nullable=false)
    private byte[] content;
 
    @ManyToOne(optional = false)
    @JoinColumn(name = "PLACE_ID")
    private Place place;
    
    public Image() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
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

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
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

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	public Place getPlace() {
		return place;
	}

	public void setPlace(Place place) {
		this.place = place;
	}
	
	public String getThumbnailFileName() {
		return thumbnailFileName;
	}

	public void setThumbnailFileName(String thumbnailFileName) {
		this.thumbnailFileName = thumbnailFileName;
	}

	public String getImageSrc(){
		if (getContentType().equals(CONTENT_TYPE_URL))
			return getFilename();
		else 
			return "/images/"+getFilename();
	}
	
	@Override
    public int hashCode() {
		return new HashCodeBuilder(15, 35).
			       append(title).
			       append(filename).
			       append(contentType).
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
		Image other = (Image) obj;
		return new EqualsBuilder().appendSuper(super.equals(obj)).append(title, other.title)
				.append(filename, other.filename)
				.append(contentType, other.contentType)
				.isEquals();
	}
 
    @Override
    public String toString() {
        return "Image [id=" + id + ", title="+title+", contentType=" + contentType + ", filename=" + filename + "]";
    }
 
    
}
