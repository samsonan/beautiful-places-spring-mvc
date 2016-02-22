package com.samsonan.bplaces.service;

import java.util.Set;

import com.samsonan.bplaces.model.Place;
import com.samsonan.bplaces.model.PlaceFilters;

public interface PlaceService {

	Place findById(int id);
    
    void savePlace(Place place);
     
    void deletePlace(int id);
    
    Set<Place> getAllPlaces(); 
    Set<Place> getAllPlaces(PlaceFilters filters); 
    
	int getImgCountForPlace(int id);

     
}
