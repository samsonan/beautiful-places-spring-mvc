package com.samsonan.bplaces.dao;

import java.util.List;

import com.samsonan.bplaces.model.Image;

public interface ImageDao {

	List<Image> findAll();
    
	Image findById(int id);
     
    void save(Image image);
     
    List<Image> findAllByPlaceId(int placeId);
     
    void deleteById(int id);	
	
}
