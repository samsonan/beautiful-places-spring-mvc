package com.samsonan.bplaces.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.samsonan.bplaces.dao.PlaceDao;
import com.samsonan.bplaces.model.Place;

@Service("placeService")
@Transactional
public class PlaceServiceImpl implements PlaceService {
	
	@Autowired
    private PlaceDao dao;	

	public List<Place> getAllPlaces(){
		return dao.getAllPlaces();
	}
	
	@Override
	public Place findById(int id) {
		return dao.findById(id);
	}

	@Override
	public void savePlace(Place place) {
		dao.savePlace(place);
	}

	@Override
	public void deletePlace(int id) {
		dao.deletePlaceById(id);
	}
	
	@Override
	public void updatePlace(Place place) {
		Place entity = dao.findById(place.getId());
        if(entity!=null){
            entity.setTitle(place.getTitle());
            entity.setDescription(place.getDescription());
            entity.setLat(place.getLat());
            entity.setLon(place.getLon());
        }
	}

	


}