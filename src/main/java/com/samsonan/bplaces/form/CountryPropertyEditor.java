package com.samsonan.bplaces.form;

import java.beans.PropertyEditorSupport;

import com.samsonan.bplaces.model.Country;
import com.samsonan.bplaces.service.PlaceService;

public class CountryPropertyEditor extends PropertyEditorSupport {

	PlaceService placeService;

	public CountryPropertyEditor(PlaceService placeService) {
		this.placeService = placeService;
	}

	// Converts a String to a Country (when submitting form)
	@Override
	public void setAsText(String code) {
		if(code == null || code.length() != 2) {
			setValue(null);
			return;
		}
		
		Country country = placeService.getCountryByIso2(code);
		this.setValue(country);
	}

	// Converts a Country to a String (when displaying form)
	@Override
	public String getAsText() {

		Object obj = getValue();
		if(obj == null) {
			return null;
		}

		if(obj instanceof Country) {
			Country country = (Country) obj;
			return country.getCodeIso2Char();
		}

		throw new IllegalArgumentException("Value must be a Country");		
	}

}
