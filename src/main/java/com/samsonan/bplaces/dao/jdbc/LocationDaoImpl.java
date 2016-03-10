package com.samsonan.bplaces.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.samsonan.bplaces.dao.LocationDao;
import com.samsonan.bplaces.model.LocationDetails;

@Repository("locationDao")
public class LocationDaoImpl implements LocationDao {

	private final Logger logger = LoggerFactory.getLogger(LocationDaoImpl.class);	
	
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
	public LocationDetails getLocationDetails(Integer location) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		String SQL = "select c.code as c_code, c.name as c_name, c.zone as zone, l.name as l_name, l.location_id as l_id "
				+ "from country c, location l where l.country=c.code and l.location_id= ?";

		LocationDetails locationDetails = null;
		
		try{
			locationDetails = jdbcTemplate.queryForObject(SQL, new Object[] { location },
				new LocationRowMapper(findAllZones()));	
		}catch(Exception ex){
			logger.error("getLocationDetails ERROR for location id="+location, ex);
		}

		return locationDetails;
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

class LocationRowMapper implements RowMapper<LocationDetails> 
{
	Map<String, String> zones;
	
	public LocationRowMapper(Map<String, String> zones){
		this.zones = zones;	
	}
	
	@Override
	public LocationDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
		LocationDetails locationDetails = new LocationDetails();
		locationDetails.setCountryCode(rs.getString("c_code"));
		locationDetails.setCountryName(rs.getString("c_name"));
		locationDetails.setZoneCode(rs.getString("zone"));
		locationDetails.setZoneName(zones.get( rs.getString("zone")));
		locationDetails.setLocationId(rs.getString("l_id"));
		locationDetails.setLocationName(rs.getString("l_name"));
		return locationDetails;
	}
}
