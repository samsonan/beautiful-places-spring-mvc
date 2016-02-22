package com.samsonan.bplaces.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class FileBucket {
 
	private MultipartFile file;
	private String imageUrl;
	
	private String description;
	
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
    
}