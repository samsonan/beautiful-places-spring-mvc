package com.samsonan.bplaces.dao;

import java.util.List;

import com.samsonan.bplaces.model.Place;

public interface PlaceDao {

	void savePlace(Place place);
	
	List<Place> getAllPlaces();
	
	Place findById(int id);
	 
    void deletePlaceById(int id);
     
}
