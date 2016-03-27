package com.samsonan.bplaces.util.validation;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.samsonan.bplaces.model.Place;
import com.samsonan.bplaces.model.PlaceLink;
import com.samsonan.bplaces.service.UserService;

@Component
public class PlaceFormValidator implements Validator {

	@Autowired
	UserService userService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Place.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		Place place = (Place) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "NotEmpty.place.title");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "NotEmpty.place.description");
		
		if(place.getDescription().length()>4000)
			errors.rejectValue("description", "Length.place.description");
		
		if (place.getLat()<-90 || place.getLat() >90)
			errors.rejectValue("lat", "Range.place.latitude");
		
		if (place.getLon()<-180 || place.getLon() >180)
			errors.rejectValue("lon", "Range.place.longitude");

		if (place.isUnesco()) {
			if (place.getUnescoUrl().isEmpty()) {
				errors.rejectValue("unescoUrl", "NotEmpty.place.unesco");
			} else {
				if (!isValidURI(place.getUnescoUrl()))
					errors.rejectValue("unescoUrl", "URL.place.unesco");
			}
				
		}
		
		if (place.getPlaceTypes() == null || place.getPlaceTypes().isEmpty())
			errors.rejectValue("placeTypes", "Empty.place.placeTypes");

		if (place.getPlaceLinks() != null && !place.getPlaceLinks().isEmpty()) {
			//TODO: multiple errors logged or not? 
			for (Iterator<PlaceLink> iterator = place.getPlaceLinks().iterator(); iterator.hasNext();) {
	    		PlaceLink link = iterator.next();
	    		if (link == null || link.getSiteName() == null || link.getUrl() == null) continue;
	    		if ((link.getSiteName().isEmpty() && !link.getUrl().isEmpty()) 
	    				|| (!link.getSiteName().isEmpty() && link.getUrl().isEmpty()) ){
	    			errors.rejectValue("placeLinks", "Consistent.place.placeLinks");
	    		} else if (!link.getSiteName().isEmpty() && !link.getUrl().isEmpty() && !isValidURI(link.getUrl())) {
					errors.rejectValue("placeLinks", "URL.place.placeLinks", new String []{ link.getSiteName()},"");
	    		}
			}
		}
		
		
	}
	
	private boolean isValidURI(String uriStr) {
	    try {
	        @SuppressWarnings("unused")
			URI uri = new URI(uriStr);
	        return true;
	      }
	      catch (URISyntaxException e) {
	          return false;
	      }
	  }
	
	
}