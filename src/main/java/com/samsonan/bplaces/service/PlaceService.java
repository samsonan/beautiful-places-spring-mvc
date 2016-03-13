package com.samsonan.bplaces.service;

import java.util.Set;

import com.samsonan.bplaces.model.Country;
import com.samsonan.bplaces.model.Place;
import com.samsonan.bplaces.model.PlaceFilters;

public interface PlaceService {

	Place findById(int id);

    Set<Place> findAll(); 
    Set<Place> findAll(PlaceFilters filters); 
    Set<Place> findAllMyPlaces();
	
    void savePlace(Place place);
     
    void deleteById(int id);
    
	int getImgCountForPlace(int id);
	
    Country getCountryByIso2(String code);

     
}
