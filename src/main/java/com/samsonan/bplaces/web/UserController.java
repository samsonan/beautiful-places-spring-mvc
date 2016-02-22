package com.samsonan.bplaces.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.samsonan.bplaces.model.User;
import com.samsonan.bplaces.service.UserService;


/**
 * 
 * http://www.mkyong.com/spring-mvc/spring-mvc-form-handling-example/
 * 
 * @author ShamanXXI
 *
 */
@Controller
public class UserController {

	private final Logger logger = LoggerFactory.getLogger(UserController.class);	
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/admin**", method = RequestMethod.GET)
	public ModelAndView adminPage() {

		ModelAndView model = new ModelAndView();
		model.addObject("title", "Admin Dashboard");
		model.setViewName("admin");

		return model;

	}

	@RequestMapping(value = {"/login"}, method = RequestMethod.GET)
	public String login() {
		return "login";
	}

	@RequestMapping(value = {"/users/add"}, method = RequestMethod.GET)
	public String register(ModelMap model) {
        User user = new User();
        model.addAttribute("user", user);
		return "register";
	}	

	@RequestMapping(value = {"/users/add"}, method = RequestMethod.POST)
	public String register(@ModelAttribute("user") @Valid User userInfo, 
			  BindingResult result) {
		
    	if (result.hasErrors()) {
            return "/register";
        }
 
    	try{
    		userService.registerNewUser(userInfo);
    	}catch(Exception ex){
    		//TODO
    	}
 
		return "redirect:/map";
	}	
	
	@RequestMapping(value = {"/users","/users/list"}, method = RequestMethod.GET)
	public String listUsers(ModelMap model) {

		List<User> list = userService.getAllUsers();

		model.addAttribute("userList", list);

		return "user_list";
	}
	
	@RequestMapping(value = "/users/{id}/delete", method = RequestMethod.POST)
	public String deleteUser(@PathVariable("id") int id, 
		final RedirectAttributes redirectAttributes) {

		logger.debug("deleteUser() : {}", id);

		userService.deleteUser(id);
		
		redirectAttributes.addFlashAttribute("css", "success");
		redirectAttributes.addFlashAttribute("msg", "User is deleted!");
		
		return "redirect:/users";

	}	
    
}

