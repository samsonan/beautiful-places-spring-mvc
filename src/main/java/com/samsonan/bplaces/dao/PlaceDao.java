package com.samsonan.bplaces.dao;

import java.util.Set;

import com.samsonan.bplaces.model.Place;
import com.samsonan.bplaces.model.PlaceFilters;

public interface PlaceDao {

	void save(Place place);
	
	Set<Place> findAll(PlaceFilters filters);
	
	Place findById(int id);
	 
    void deleteById(int id);
    
    int getImgCountForPlace(int id);
     
}
