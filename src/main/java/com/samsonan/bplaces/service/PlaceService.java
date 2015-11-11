package com.samsonan.bplaces.service;

import java.util.List;

import com.samsonan.bplaces.model.Place;

public interface PlaceService {

	Place findById(int id);
    
    void savePlace(Place place);
     
    void updatePlace(Place place);

    void deletePlace(int id);
    
    List<Place> getAllPlaces(); 
     
}
