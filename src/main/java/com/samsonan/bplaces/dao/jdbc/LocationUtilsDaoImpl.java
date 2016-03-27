package com.samsonan.bplaces.dao.jdbc;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.samsonan.bplaces.dao.LocationUtilsDao;

//Note: Country domain object is processed by hibernate now
@Repository("locationDao")
public class LocationUtilsDaoImpl implements LocationUtilsDao {

//	private final Logger logger = LoggerFactory.getLogger(LocationDaoImpl.class);	
	
	@Autowired
	DataSource dataSource;
	
	private JdbcTemplate jdbcTemplate; //TODO: is it the corrct way???
    
	@Override
	public String findCountryByLocation(Integer location) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		String SQL = "select iso_2 from country c, location l where l.country_code=c.iso_2 and l.id= ?";
		return jdbcTemplate.queryForObject(SQL, String.class, location);
	}

	@Override
	public String findRegionByLocation(Integer location) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		String SQL = "select subregion_code from country c, location l where l.country_code=c.iso_2 and l.id= ?";
		return jdbcTemplate.queryForObject(SQL, String.class, location);
	}

	@Override
	public Map<String, String> findAllCountriesForRegion(String subregionCode) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		Map<String, String> countries = new LinkedHashMap<>();
		
		String SQL = "select iso_2 as code, name from country where subregion_code = ? or region = ? order by name asc";

		List <Map<String, Object>> results = jdbcTemplate.queryForList(SQL, subregionCode, subregionCode); 

		for (Map<String, Object> m : results){
			countries.put(m.get("code").toString(), m.get("name").toString());
		}
			
		return countries;
	}

	@Override
	public Map<String, String> findAllRegions() {
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		Map<String, String> regions = new LinkedHashMap<>();
		
//		String SQL = "select distinct subregion_code as code, region, subregion from country order by region asc, subregion asc";
		
		String SQL = "(select distinct subregion_code as code, region, subregion from country " + 
				" union " +
				" select distinct region as code, region, 'All '||region from country) " + 
				" order by region asc, subregion asc ";

		List <Map<String, Object>> results = jdbcTemplate.queryForList(SQL); 

		for (Map<String, Object> m : results){
			regions.put(m.get("code").toString(), m.get("region") +" / "+ m.get("subregion"));
		}
			
		return regions;
	}
	
	@Override
	public Map<String, String> findAllLocationsForCountry(String countryIso2Code) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		Map<String, String> locations = new HashMap<>();
		
		String SQL = "select id, name from location where country_code = ? order by name asc";

		List <Map<String, Object>> results = jdbcTemplate.queryForList(SQL, countryIso2Code); 

		for (Map<String, Object> m : results){
			locations.put( m.get("id").toString() , m.get("name").toString());
		}
			
		return locations;
	}

	@Override
	public List<String> getCountryNamesByCodes(String[] countryCodes) {
		
		NamedParameterJdbcTemplate namedParameterJdbcTemplate = new	NamedParameterJdbcTemplate(dataSource);

		Map<String, List<String>> param = new HashMap<>();
		param.put("codes", Arrays.asList(countryCodes));
		
		String sql = "SELECT name FROM country WHERE iso_2 IN (:codes)";
		List<String> result = namedParameterJdbcTemplate.queryForList(sql, param, String.class);
		
		return result;
	}

}


