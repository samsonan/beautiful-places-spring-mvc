package com.samsonan.bplaces.dao;

import java.util.List;
import com.samsonan.bplaces.model.Country;

public interface LocationDao {

	Country findCountryByCode(String countryCode) throws Exception;

	List<Country> findAllCountries();

}
