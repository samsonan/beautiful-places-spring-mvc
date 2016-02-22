package com.samsonan.bplaces.service;

import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.samsonan.bplaces.dao.PlaceDao;
import com.samsonan.bplaces.model.Place;
import com.samsonan.bplaces.model.PlaceFilters;

@Service("placeService")
@Transactional
public class PlaceServiceImpl implements PlaceService {
	
	@Autowired
    private PlaceDao dao;	

	public Set<Place> getAllPlaces(PlaceFilters filters){
		return dao.findAll(filters);
	}

	public Set<Place> getAllPlaces(){
		return dao.findAll(null);
	}
	
	@Override
	public Place findById(int id) {
		return dao.findById(id);
	}

	@Override
	public void savePlace(Place place) {
		dao.save(place);
	}

	@Override
	public void deletePlace(int id) {
		dao.deleteById(id);
	}

	public int getImgCountForPlace(int id){
		return dao.getImgCountForPlace(id);
	}
	
}