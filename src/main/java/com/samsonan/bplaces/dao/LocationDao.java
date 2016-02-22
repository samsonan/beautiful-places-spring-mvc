package com.samsonan.bplaces.dao;

import java.util.List;
import com.samsonan.bplaces.model.Country;

public interface LocationDao {

	public Country findCountryByCode(String countryCode) throws Exception;

	public List<Country> findAllCountries();

}
