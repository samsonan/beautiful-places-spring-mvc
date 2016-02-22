package com.samsonan.bplaces.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.samsonan.bplaces.dao.LocationDao;
import com.samsonan.bplaces.model.Country;
import com.samsonan.bplaces.model.Place;
import com.samsonan.bplaces.model.PlaceFilters;
import com.samsonan.bplaces.model.PlaceLink;
import com.samsonan.bplaces.model.User;
import com.samsonan.bplaces.service.PlaceService;

@Controller
public class PlaceController {

	private final Logger logger = LoggerFactory.getLogger(PlaceController.class);	
	
	@Autowired
	PlaceService placeService;

	@Autowired
	LocationDao locationDao; //TODO: better use service

	@RequestMapping(value = {"/", "/map"}, method = RequestMethod.GET)
	public String mapPlaces(Model model) {
		
		return resetFilters(model);
	}
	
	@RequestMapping(value = {"/", "/map"}, params = {"lat", "lon"}, method = RequestMethod.GET)
	public String mapPlaces(@RequestParam String lat, @RequestParam String lon, Model model) {

		model.addAttribute("lat", lat);
		model.addAttribute("lon", lon);
		return resetFilters(model);
	}

	@RequestMapping(value = {"/", "/map"}, params = "filter.apply", method = RequestMethod.POST)
	public String mapApplyFilter(@ModelAttribute("filters") @Valid PlaceFilters filters, 
			Model model) {
		
		Set<Place> set = placeService.getAllPlaces(filters);
		model.addAttribute("filters", filters);
		model.addAttribute("placeList", set);
 
		return "map";
	}		

	@RequestMapping(value = {"/", "/map"}, params = "filter.reset", method = RequestMethod.POST)
	public String mapResetFilters(@ModelAttribute("filters") @Valid PlaceFilters filters, 
			Model model) {
		
		return resetFilters(model);
	}		

	private String resetFilters(Model model) {
		Set<Place> set = placeService.getAllPlaces();
		model.addAttribute("filters", new PlaceFilters());
		model.addAttribute("placeList", set);
		
		return "map";
	}
	
	//TODO: remove
	@RequestMapping(value = {"/places/list_obsolete"}, method = RequestMethod.GET)
	public ModelAndView listPlaces2() {

		Set<Place> set = placeService.getAllPlaces();
		ModelAndView model = new ModelAndView("list");
		model.addObject("placeList", set);
		return model;
	}

	@RequestMapping(value = {"/places","/places/list"}, method = RequestMethod.GET)
	public String listPlaces(Model model) {

		resetFilters(model);

		return "place_list";
	}
	
    @RequestMapping(value = { "/places/add" }, method = RequestMethod.GET)
    public String newPlace(ModelMap model) {
        
    	Place place = new Place();
        model.addAttribute("place", place);
        return "place_edit";
    }	

    //TODO: user control access
    @RequestMapping(value = { "/places/edit-place-{id}" }, method = RequestMethod.GET)
    public String editPlace(@PathVariable int id, ModelMap model) {
    	
    	Place place = placeService.findById(id);
        model.addAttribute("place", place);
        return "place_edit";
    }	

    @RequestMapping(value = { "/places/view-place-{id}" }, method = RequestMethod.GET)
    public String viewPlace(@PathVariable int id, ModelMap model) {
        Place place = placeService.findById(id);
        model.addAttribute("place", place);
        return "place_view";
    }    
        
    @RequestMapping(value = { "/places/delete-place-{id}" }, method = RequestMethod.GET)
    public String deletePlace(@PathVariable int id) {
        placeService.deletePlace(id);
        return "redirect:list";
    }    

    @RequestMapping(value = { "/places/add","/places/edit-place-{id}" }, params = "edit.save.exit", method = RequestMethod.POST)
    public String savePlaceExit(@Valid Place place, BindingResult result, HttpServletRequest request) {

    	if (savePlace(place, result) < 1)
            return "place_edit";
 
        return "redirect:list";
    }    

    @RequestMapping(value = { "/places/add","/places/edit-place-{id}" }, params = "edit.save.photos", method = RequestMethod.POST)
    public String savePlacePhotos(@Valid Place place, BindingResult result) {

    	if (savePlace(place, result) < 1)
            return "place_edit";
    	
        return "redirect:/places/add-image-"+place.getId();
    }    

    private int savePlace(@Valid Place place, BindingResult result){

    	if (result.hasErrors()) {
            return -1;
        }
 
    	//place links are detached, with no place id
    	for (Iterator<PlaceLink> iterator = place.getPlaceLinks().iterator(); iterator.hasNext();) {
    		PlaceLink link = iterator.next();
    		if (link.getSiteName().isEmpty() || link.getUrl().isEmpty())
    			iterator.remove();
    		else
    			link.setPlace(place);
    	}
    	
    	logger.debug("img count for place {0} is {1}",place.getId(),placeService.getImgCountForPlace(place.getId()));
    	
    	place.setStatus(placeService.findById(place.getId()).getPlaceImages().size()==0?Place.STATUS_PENDING:Place.STATUS_READY);
    	
   		placeService.savePlace(place);
   		return 1;
    }
    
    
    @RequestMapping(value = { "/places/add","/places/edit-place-{id}", "/upload" }, params = "edit.cancel")
    public String cancelEdit() {

        return "redirect:list";
    }    
    
    @ModelAttribute("countryList")
    public List<Country> countryList(){

        return locationDao.findAllCountries();
    }	
	
	
}
