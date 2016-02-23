package com.samsonan.bplaces.dao;

import java.util.List;

import com.samsonan.bplaces.model.Image;

public interface ImageDao {

	List<Image> findAll();
    List<Image> findAllByPlaceId(int placeId);
    
	Image findById(int id);
     
    void saveOrUpdate(Image image);
     
    void deleteById(int id);	

}
