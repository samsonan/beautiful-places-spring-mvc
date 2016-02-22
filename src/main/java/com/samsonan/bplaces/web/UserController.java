package com.samsonan.bplaces.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.samsonan.bplaces.model.Place;
import com.samsonan.bplaces.service.PlaceService;

@Controller
public class AppController {

	@Autowired
	PlaceService service;
	
	@RequestMapping(value = {"/","/places","/list"}, method = RequestMethod.GET)
	public ModelAndView listPlaces() {

		List<Place> list = service.getAllPlaces();

		//return back to index.jsp
		ModelAndView model = new ModelAndView("index");
		model.addObject("placeList", list);

		return model;
	}
	
    @RequestMapping(value = { "/new" }, method = RequestMethod.GET)
    public String newEmployee(ModelMap model) {
        Place place = new Place();
        model.addAttribute("place", place);
        model.addAttribute("edit", false);
        return "new_place";
    }	
    
    @RequestMapping(value = { "/new" }, method = RequestMethod.POST)
    public String saveEmployee(@Valid Place place, BindingResult result, ModelMap model) {
 
        if (result.hasErrors()) {
            return "new_place";
        }
 
        service.savePlace(place);
 
        return "redirect:/list";
    }    
    
    @RequestMapping(value = { "/view-place-{id}" }, method = RequestMethod.GET)
    public String editEmployee(@PathVariable int id, ModelMap model) {
        Place place = service.findById(id);
        model.addAttribute("place", place);
        model.addAttribute("edit", true);
        return "place_info";
    }    
    
    @RequestMapping(value = { "/delete-place-{id}" }, method = RequestMethod.GET)
    public String deletePlace(@PathVariable int id) {
        service.deletePlace(id);
        return "redirect:/list";
    }    

}

