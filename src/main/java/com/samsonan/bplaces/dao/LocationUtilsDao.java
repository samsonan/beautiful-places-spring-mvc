package com.samsonan.bplaces.dao;

import java.util.List;
import java.util.Map;

public interface LocationUtilsDao {

	String findCountryByLocation(Integer location);
	String findRegionByLocation(Integer location);

	Map<String, String> findAllRegions();
	Map<String, String> findAllCountriesForRegion(String region);
	Map<String, String> findAllLocationsForCountry(String country);

	List<String> getCountryNamesByCodes(String[] countryCodes);
	
}
