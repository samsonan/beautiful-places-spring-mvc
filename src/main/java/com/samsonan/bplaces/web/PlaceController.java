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
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.samsonan.bplaces.dao.LocationDao;
import com.samsonan.bplaces.model.Country;
import com.samsonan.bplaces.model.Place;
import com.samsonan.bplaces.model.PlaceFilters;
import com.samsonan.bplaces.model.PlaceLink;
import com.samsonan.bplaces.service.PlaceService;
import com.samsonan.bplaces.util.validation.PlaceFormValidator;

@Controller
public class PlaceController {

	private final Logger logger = LoggerFactory.getLogger(PlaceController.class);	
	
	@Autowired
	PlaceService placeService;

	@Autowired
	LocationDao locationDao; //TODO: use service?

	@Autowired
	PlaceFormValidator placeFormValidator;
	
	@InitBinder("place")
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(placeFormValidator);
	}		
	
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
	public String mapApplyFilter(@ModelAttribute("filters") PlaceFilters filters, 
			Model model) {
		
		Set<Place> set = placeService.findAll(filters);
		model.addAttribute("filters", filters);
		model.addAttribute("placeList", set);
 
		return "map";
	}		

	@RequestMapping(value = {"/", "/map"}, params = "filter.reset", method = RequestMethod.POST)
	public String mapResetFilters(@ModelAttribute("filters") PlaceFilters filters, 
			Model model) {
		
		return resetFilters(model);
	}		

	private String resetFilters(Model model) {
		Set<Place> set = placeService.findAll();
		model.addAttribute("filters", new PlaceFilters());
		model.addAttribute("placeList", set);
		
		return "map";
	}

	@RequestMapping(value = {"/places/me"}, method = RequestMethod.GET)
	public ModelAndView myPlaces() {

		Set<Place> set = placeService.findAllMyPlaces();
		ModelAndView model = new ModelAndView("places/place_list_brief");
		model.addObject("placeList", set);
		model.addObject("mode","me");
		return model;
	}
	
	
	@RequestMapping(value = {"/places/list_admin"}, method = RequestMethod.GET)
	public ModelAndView listPlacesForAdmin() {

		Set<Place> set = placeService.findAll();
		ModelAndView model = new ModelAndView("places/place_list_brief");
		model.addObject("placeList", set);
		model.addObject("mode","admin");
		return model;
	}

	@RequestMapping(value = {"/places","/places/list"}, method = RequestMethod.GET)
	public String listPlaces(Model model) {
		resetFilters(model);
		return "places/place_list";
	}

	@RequestMapping(value = {"/places","/places/list"}, method = RequestMethod.POST)
	public String applyListPlacesFilter(@ModelAttribute("filters") PlaceFilters filters, 
			Model model) {
		
		Set<Place> set = placeService.findAll(filters);
		model.addAttribute("placeList", set);
		
		return "places/place_list";
	}
	
	
    @RequestMapping(value = { "/places/add" }, method = RequestMethod.GET)
    public String newPlace(ModelMap model) {
        
    	Place place = new Place();
        model.addAttribute("place", place);
        return "places/place_edit";
    }	

    @RequestMapping(value = { "/places/edit-place-{id}" }, method = RequestMethod.GET)
    public String editPlace(@PathVariable int id, ModelMap model) {
    	
    	Place place = placeService.findById(id);
        model.addAttribute("place", place);
        return "places/place_edit";
    }	

    @RequestMapping(value = { "/places/view-place-{id}" }, method = RequestMethod.GET)
    public String viewPlace(@PathVariable int id, ModelMap model) {
        Place place = placeService.findById(id);
        model.addAttribute("place", place);
        return "places/place_view";
    }    
        
    @RequestMapping(value = { "/places/delete-place-{id}" }, method = RequestMethod.GET)
    public String deletePlace(@PathVariable int id, HttpServletRequest request) {
        placeService.deleteById(id);
        return "redirect:list_admin";
    }    

    @RequestMapping(value = { "/places/add","/places/edit-place-{id}" }, params = "edit.save.exit", method = RequestMethod.POST)
    public String savePlaceExit(@Valid Place place, BindingResult result, HttpServletRequest request) {

    	if (savePlace(place, result) < 1)
            return "places/place_edit";
 
        return "redirect:list";
    }    

    @RequestMapping(value = { "/places/add","/places/edit-place-{id}" }, params = "edit.save.photos", method = RequestMethod.POST)
    public String savePlacePhotos(@Valid Place place, BindingResult result) {

    	if (savePlace(place, result) < 1)
            return "place_edit";
    	
        return "redirect:add-image-"+place.getId();
    }    

    private int savePlace(@Valid Place place, BindingResult result){

    	boolean isError = false;
    	
    	if (result.hasErrors()) {
    		isError = true;    		
        }
 
    	//place links are detached, with no place id
    	//in case of error - remove empty links because they are added automatically on the page
    	for (Iterator<PlaceLink> iterator = place.getPlaceLinks().iterator(); iterator.hasNext();) {
    		PlaceLink link = iterator.next();
    		if ((link.getSiteName() == null && link.getUrl() == null) ||
    				(link.getSiteName().isEmpty() && link.getUrl().isEmpty()))
    			iterator.remove();
    		else if (!isError)
    			link.setPlace(place);
    	}
    	
    	if (isError) return -1;
    	
    	if (place.getId() != null) { 
    		logger.debug("img count for place {0} is {1}",place.getId(),placeService.getImgCountForPlace(place.getId()));
    		place.setStatus(placeService.findById(place.getId()).getPlaceImages().size()==0?Place.STATUS_PENDING:Place.STATUS_READY);
    	} else {
    		place.setStatus(Place.STATUS_PENDING);
    	}
    	
   		placeService.savePlace(place);
   		return 1;
    }
    
    
    @RequestMapping(value = { "/places/add","/places/edit-place-{id}", "/upload" }, params = "edit.cancel")
    public String cancelEdit() {

        return "redirect:places/list";
    }    
    
    @ModelAttribute("countryList")
    public List<Country> countryList(){

        return locationDao.findAllCountries();
    }	
    
    
    @ResponseBody
	@RequestMapping(value = "/api/getFilterResult", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Set<Place> getSearchResultViaAjax(@RequestBody PlaceFilters filters) {

		logger.debug("filter values: {0}",filters);
    	
    	Set<Place> result = placeService.findAll(filters);
		//result will be converted into json format and send back to the request.
		return result;
	}
	
	
}
