package com.samsonan.bplaces.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.samsonan.bplaces.dao.LocationDao;
import com.samsonan.bplaces.model.Country;

/**
 * http://www.codejava.net/frameworks/spring/spring-mvc-with-jdbctemplate-example
 * 
 * @author ShamanXXI
 *
 */
@Repository("locationDao")
public class LocationDaoImpl implements LocationDao {

	@Autowired
	DataSource dataSource;
	
	private JdbcTemplate jdbcTemplate;
	
//	public void setDataSource(DataSource dataSource) {
//		this.dataSource = dataSource;
//		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
//	}	
	
    
	//TODO: exceptions
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

class CountryMapper implements RowMapper<Country> {
	 
    @Override
    public Country mapRow(ResultSet rs, int rowNum) throws SQLException {
        Country country = new Country();

        country.setCode(rs.getString("code"));
        country.setName(rs.getString("name"));

        return country;
    }
}

