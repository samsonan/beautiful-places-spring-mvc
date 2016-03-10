package com.samsonan.bplaces.dao;

import java.util.Map;

import com.samsonan.bplaces.model.LocationDetails;

public interface LocationDao {

	String findCountryByLocation(Integer location);
	String findZoneByLocation(Integer location);
	
	LocationDetails getLocationDetails(Integer location);

	Map<String, String> findAllZones();
	Map<String, String> findAllCountriesForZone(String zone);
	Map<String, String> findAllLocationsForCountry(String country);

}
