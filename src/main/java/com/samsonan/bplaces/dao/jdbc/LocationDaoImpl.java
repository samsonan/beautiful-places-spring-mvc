package com.samsonan.bplaces.dao.jdbc;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.samsonan.bplaces.dao.LocationDao;
import com.samsonan.bplaces.model.Country;

/**
 * TODO: maybe it is worth using hibernate now since i still pack it in the object 
 *
 */
@Repository("locationDao")
public class LocationDaoImpl implements LocationDao {

	@Autowired
	DataSource dataSource;
	
	private JdbcTemplate jdbcTemplate;
    
	//TODO: correct exceptions
	public Country findCountryByCode(String countryCode) throws Exception {

		jdbcTemplate = new JdbcTemplate(dataSource);
		
		String SQL = "select code, name from country where id = ?";
		try{
			Country country = (Country) jdbcTemplate.queryForObject(SQL, new Object[]{countryCode}, 
					new BeanPropertyRowMapper<Country>(Country.class));

			return country;
		} catch (Exception e) {
			throw new Exception("Cannot find country for code "+countryCode);
		}
	}
	
	public List<Country> findAllCountries() {
		jdbcTemplate = new JdbcTemplate(dataSource);
		String SQL = "select name from country";
		return (List<Country>) jdbcTemplate.query(SQL, new BeanPropertyRowMapper<Country>(Country.class));
	}
	
}

