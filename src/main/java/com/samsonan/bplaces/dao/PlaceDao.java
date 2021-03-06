package com.samsonan.bplaces.dao;

import java.util.Set;

import com.samsonan.bplaces.model.Country;
import com.samsonan.bplaces.model.Place;
import com.samsonan.bplaces.model.PlaceFilters;

public interface PlaceDao {

	Set<Place> findAll(PlaceFilters filters);
	Set<Place> findAllMyPlaces();

	Place findById(int id);

	void saveOrUpdate(Place place);
	 
    void deleteById(int id);
    
    int getImgCountForPlace(int id);
    
    Country getCountryByIso2(String code);
     
}
