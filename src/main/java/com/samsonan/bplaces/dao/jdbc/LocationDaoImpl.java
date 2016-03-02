package com.samsonan.bplaces.dao.jdbc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.samsonan.bplaces.dao.LocationDao;

/**
 * TODO: maybe it is worth using hibernate now since i still pack it in the object 
 *
 */
@Repository("locationDao")
public class LocationDaoImpl implements LocationDao {

	@Autowired
	DataSource dataSource;
	
	private JdbcTemplate jdbcTemplate;
    
	@Override
	public String findCountryByLocation(Integer location) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		String SQL = "select code from country c, location l where l.country=c.code and l.location_id= ?";
		return jdbcTemplate.queryForObject(SQL, String.class, location);
	}

	@Override
	public String findZoneByLocation(Integer location) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		String SQL = "select zone from country c, location l where l.country=c.code and l.location_id= ?";
		return jdbcTemplate.queryForObject(SQL, String.class, location);
	}

	@Override
	public Map<String, String> findAllZones() {

		//OK, it is a fixed set of values that will never change
		Map<String, String> zones = new HashMap<>();
		zones.put("E", "Europe");
		zones.put("A", "Asia");
		zones.put("SEA", "South-East Asia");
		zones.put("ME", "Middle East");
		zones.put("SA", "South America");
		zones.put("NA", "North America");
		zones.put("CA", "Central America");
		zones.put("AF", "Africa");
		
		return zones;
	}

	@Override
	public Map<String, String> findAllCountriesForZone(String zone) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		Map<String, String> countries = new HashMap<>();
		
		String SQL = "select code, name from country where zone = ? order by name asc";

		List <Map<String, Object>> results = jdbcTemplate.queryForList(SQL, zone); 

		for (Map<String, Object> m : results){
			countries.put(m.get("code").toString(), m.get("name").toString());
		}
			
		return countries;
	}

	@Override
	public Map<String, String> findAllLocationsForCountry(String country) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		Map<String, String> locations = new HashMap<>();
		
		String SQL = "select location_id, name from location where country = ? order by name asc";

		List <Map<String, Object>> results = jdbcTemplate.queryForList(SQL, country); 

		for (Map<String, Object> m : results){
			locations.put( m.get("location_id").toString() , m.get("name").toString());
		}
			
		return locations;
	}
	
}

