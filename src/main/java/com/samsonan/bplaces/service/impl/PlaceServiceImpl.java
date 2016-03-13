package com.samsonan.bplaces.service.impl;

import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.samsonan.bplaces.dao.PlaceDao;
import com.samsonan.bplaces.model.Country;
import com.samsonan.bplaces.model.Place;
import com.samsonan.bplaces.model.PlaceFilters;
import com.samsonan.bplaces.service.PlaceService;

@Service("placeService")
@Transactional
public class PlaceServiceImpl implements PlaceService {
	
	@Autowired
    private PlaceDao dao;	

	public Set<Place> findAll(PlaceFilters filters){
		return dao.findAll(filters);
	}

	public Set<Place> findAll(){
		return dao.findAll(null);
	}
	
	@Override
	public Place findById(int id) {
		return dao.findById(id);
	}

	@Override
	public void savePlace(Place place) {
		dao.saveOrUpdate(place);
	}

	@Override
	public void deleteById(int id) {
		dao.deleteById(id);
	}

	public int getImgCountForPlace(int id){
		return dao.getImgCountForPlace(id);
	}

	@Override
	public Set<Place> findAllMyPlaces() {
		return dao.findAllMyPlaces();
	}
	
	public Country getCountryByIso2(String code) {
    	return dao.getCountryByIso2(code);
    }
	
}