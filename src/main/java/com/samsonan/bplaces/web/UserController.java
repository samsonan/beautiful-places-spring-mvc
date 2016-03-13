package com.samsonan.bplaces.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.samsonan.bplaces.model.FeedbackForm;
import com.samsonan.bplaces.model.User;
import com.samsonan.bplaces.service.UserService;
import com.samsonan.bplaces.service.impl.MessageServiceImpl;
import com.samsonan.bplaces.util.validation.UserFormValidator;


@Controller
public class UserController {

	private final Logger logger = LoggerFactory.getLogger(UserController.class);	
	
	@Autowired
	UserService userService;
	
	@Autowired
	MessageServiceImpl messageService;
	
	@Autowired
	UserFormValidator userFormValidator;
	
	@InitBinder("user")
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(userFormValidator);
	}	
	
	@RequestMapping(value = {"/login"}, method = RequestMethod.GET)
	public String login() {
		return "users/login";
	}

	@RequestMapping(value = {"/feedback"}, method = RequestMethod.GET)
	public String feedback(ModelMap model) {
	
        FeedbackForm form = new FeedbackForm();
        model.addAttribute("feedbackForm", form);
		return "/feedback";
	}	

	@RequestMapping(value = {"/feedback"}, method = RequestMethod.POST)
	public String register(@ModelAttribute("feedbackForm") @Valid FeedbackForm form, 
			  BindingResult result, final RedirectAttributes redirectAttributes) {
		
    	if (result.hasErrors()) {
            return "/feedback";
        }

    	redirectAttributes.addFlashAttribute("css", "success");
		redirectAttributes.addFlashAttribute("msg", "Your feedback has been successfuly sent!");

		User user = null;
		
		try{
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    	String name = auth.getName();
			user = userService.findByName(name);
		}catch(Exception ex) {
			//OK
		}
		
		messageService.sendFeedback(user, form);
		
    	return "redirect:/feedback";
	}
	
	
	/**
	 * Registration 
	 **/
	@RequestMapping(value = {"/register"}, method = RequestMethod.GET)
	public String register(ModelMap model) {
	
        User user = new User();
        model.addAttribute("user", user);
		return "users/register";
	}	

	@RequestMapping(value = {"/register"}, method = RequestMethod.POST)
	public String register(@ModelAttribute("user") @Valid User userInfo, 
			  BindingResult result, HttpServletRequest request, HttpServletResponse response) {
		
    	if (result.hasErrors()) {
            return "users/register";
        }
 
    	userInfo.setRole(User.ROLE_USER);
    	
   		userService.registerNewUser(userInfo);
   		
   		
   		try{
   	   		request.login(userInfo.getName(), userInfo.getPassword());
   		}catch (Exception ex) {
   			//automatic login failed? not a big deal
   			logger.error("Automatic login after registration for user "+userInfo.getName()+" failed",ex);
   		}
 
		return "redirect:map";
	}	

	
	@RequestMapping(value = {"/restore"}, method = RequestMethod.GET)
	public String restore(ModelMap model) {
		return "users/user_restore";
	}	

    @RequestMapping(value = {"/restore"}, method = RequestMethod.POST)
    public String restore(HttpServletRequest request, RedirectAttributes redirectAttributes){  
        // this way you get value of the input you want
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        
        User user = null;
        
        if (!username.isEmpty()) {
        	user = userService.findByName(username);
        	if (user == null) {
                redirectAttributes.addFlashAttribute("css", "danger");
        		redirectAttributes.addFlashAttribute("msg", "User "+username+" not found!");
        		return "redirect:restore";
        	}
        } else if (!email.isEmpty()) {
        	user = userService.findByEmail(email);
        	if (user == null) {
                redirectAttributes.addFlashAttribute("css", "danger");
        		redirectAttributes.addFlashAttribute("msg", "User with email "+email+" not found!");
        		return "redirect:restore";
        	}
        } else {
            redirectAttributes.addFlashAttribute("css", "danger");
    		redirectAttributes.addFlashAttribute("msg", "Please specify either username or email");
    		return "redirect:restore";
        }
        
        userService.restoreUserPassword(user);
        
        redirectAttributes.addFlashAttribute("css", "success");
		redirectAttributes.addFlashAttribute("msg", "Message has been sent!");
				
        return "redirect:restore";
    }
	
	@RequestMapping(value = {"/users","/users/list"}, method = RequestMethod.GET)
	public String listUsers(ModelMap model) {

		List<User> list = userService.findAll();

		model.addAttribute("userList", list);

		return "users/user_list";
	}

	@RequestMapping(value = {"/users/me"}, method = RequestMethod.GET)
	public String profile(ModelMap model) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String name = auth.getName();
	      
		User user = userService.findByName(name);
        model.addAttribute("userForm", user);
		return "users/user_form";
	}	
	
	/**
	 * Adding user by admin
	 * @return view
	 */
	@RequestMapping(value = {"/users/add"}, method = RequestMethod.GET)
	public String addUser(ModelMap model) {
        User user = new User();
        model.addAttribute("userForm", user);
		return "users/user_form";
	}	

	
	// show update form
	@RequestMapping(value = "/users/{id}/update", method = RequestMethod.GET)
	public String showUpdateUserForm(@PathVariable("id") int id, Model model) {

		User user = userService.findById(id);
		model.addAttribute("userForm", user);
		
		return "users/user_form";

	}	

	@RequestMapping(value = "/users/{id}/send_reg", method = RequestMethod.GET)
	public String sendConfirmEmail(@PathVariable("id") int id, 
			final RedirectAttributes redirectAttributes) {

		User user = userService.findById(id);
	
		messageService.sendRegistrationMessage(user);
		
		redirectAttributes.addFlashAttribute("css", "success");
		redirectAttributes.addFlashAttribute("msg", "Registration email is sent!");
		
		return "redirect:/users/list";
	}	
	
	
	@RequestMapping(value = "/users/{id}/confirm_reg", method = RequestMethod.GET)
	public String confirmEmail(@PathVariable("id") int id, 
			@RequestParam(value = "uuid", required = true) String uuid,
			final RedirectAttributes redirectAttributes) {

		User user = userService.findById(id);
		
		try{
			if (!messageService.checkRegistrationUUID(user, uuid)) {
				throw new Exception("Registration details not found or expired!");
			}
		}catch(Exception ex){
			redirectAttributes.addFlashAttribute("css", "danger");
			redirectAttributes.addFlashAttribute("msg", "Error while activating the account:"+ex.getMessage()+". Please contact support.");
			return "redirect:/login";
			
		}
		redirectAttributes.addFlashAttribute("css", "success");
		redirectAttributes.addFlashAttribute("msg", "Your email is confirmed!");
		
		return "redirect:/login";
	}	

	@RequestMapping(value = "/users/{id}/send_reset", method = RequestMethod.GET)
	public String sendPasswordResetEmail(@PathVariable("id") int id, 
			final RedirectAttributes redirectAttributes) {

		User user = userService.findById(id);
	
		messageService.sendPasswordResetMessage(user);
		
		redirectAttributes.addFlashAttribute("css", "success");
		redirectAttributes.addFlashAttribute("msg", "Password Reset email is sent!");
		
		return "redirect:/users/list";
	}		
	
	@RequestMapping(value = "/users/{id}/confirm_reset", method = RequestMethod.GET)
	public String confirmPasswordReset(@PathVariable("id") int id, 
			@RequestParam(value = "uuid", required = false) String uuid,
			final RedirectAttributes redirectAttributes) {

		User user = userService.findById(id);
		
		try{
			if (!messageService.checkPasswordResetUUID(user, uuid)) {
				throw new Exception("Password reset details not found or expired!");
			}
		}catch(Exception ex){
			redirectAttributes.addFlashAttribute("css", "danger");
			redirectAttributes.addFlashAttribute("msg", "Error while trying to reset the password:"+ex.getMessage()+". Please contact support.");
			return "redirect:/login";
		}		
		return "users/new_pass";
	}	
	
	@RequestMapping(value = {"/users"}, method = RequestMethod.POST)
	public String addUser(@ModelAttribute("userForm") @Valid User user, 
			  BindingResult result, final RedirectAttributes redirectAttributes) {
		
    	if (result.hasErrors()) {
            return "users/user_form";
        }
 
    	// Add message to flash scope
    	redirectAttributes.addFlashAttribute("css", "success");
    	if(user.isNew()){
    		redirectAttributes.addFlashAttribute("msg", "User added successfully!");
    	}else{
    		redirectAttributes.addFlashAttribute("msg", "User updated successfully!");
    	}
    				
   		userService.saveUser(user);
 
		return "redirect:users/list";
	}		
	
	
	@RequestMapping(value = "/users/{id}/delete", method = RequestMethod.POST)
	public String deleteUser(@PathVariable("id") int id, 
		final RedirectAttributes redirectAttributes) {

		logger.debug("deleteUser() : {}", id);

		userService.deleteById(id);
		
		redirectAttributes.addFlashAttribute("css", "success");
		redirectAttributes.addFlashAttribute("msg", "User is deleted!");
		
		return "redirect:users/list";

	}	
	
    @ModelAttribute("roleList")
    public String [] roleList(){

        return User.ROLE_LIST;
    }	
    
}

