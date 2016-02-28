package com.samsonan.bplaces.model;

import org.springframework.web.multipart.MultipartFile;

public class ImageUploadForm {
 
	private String editImageSrc;
	private MultipartFile file;
	private String imageUrl;
	
	private String description;
	
	private String title;
	
    public MultipartFile getFile() {
        return file;
    }
 
    public void setFile(MultipartFile file) {
        this.file = file;
    }
    
    public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getDescription() {
        return description;
    }
 
    public void setDescription(String description) {
        this.description = description;
    }

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getEditImageSrc() {
		return editImageSrc;
	}

	public void setEditImageSrc(String editImageSrc) {
		this.editImageSrc = editImageSrc;
	}    
    
	
    
    
}