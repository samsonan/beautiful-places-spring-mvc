package com.samsonan.bplaces.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
import com.samsonan.bplaces.model.PassResetForm;
import com.samsonan.bplaces.model.User;
import com.samsonan.bplaces.service.UserService;
import com.samsonan.bplaces.service.impl.MessageServiceImpl;
import com.samsonan.bplaces.util.validation.PassResetFormValidator;
import com.samsonan.bplaces.util.validation.UserFormValidator;

/**
 * Controller for all use-related actions/forms:
 *  - login
 *  - logout
 *  - registration
 *  - password reset
 *  - user list
 *  - user CRUD
 *  - feedback
 *  - user profile view/update
 *  
 * @author Andrey Samsonov (samsonan)
 *
 */
@Controller
public class UserController {

	private final Logger logger = LoggerFactory.getLogger(UserController.class);	
	
	@Autowired
	UserService userService;
	
	@Autowired
	MessageServiceImpl messageService;
	
	@Autowired
	UserFormValidator userFormValidator;
	
	/**
	 * Used in the following cases:
	 *  - profile edit
	 *  - user create/update by admin
	 *  - registration
	 *  
	 *  Not used for feedback, new password or request password forms 
	 *  
	 * @param binder
	 */
	@InitBinder({"userForm","user"})
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(userFormValidator);
	}	

	
	/**
	 * Redirecting to custom login page
	 * @return
	 */
	@RequestMapping(value = {"/login"}, method = RequestMethod.GET)
	public String login() {
		return "/users/login";
	}

	
	/** ----------------------- Feedback form ----------------------- **/
	
	/**
	 * Feedback form request
	 * @return view
	 */
	@RequestMapping(value = {"/feedback"}, method = RequestMethod.GET)
	public String feedback(Model model) {
	
        FeedbackForm form = new FeedbackForm();
        model.addAttribute("feedbackForm", form);
		return "/feedback";
	}	

	/**
	 * Feedback form submit
	 * @param redirectAttributes additional info to show on redirected page
	 * @return view
	 */
	@RequestMapping(value = {"/feedback"}, method = RequestMethod.POST)
	public String register(@ModelAttribute("feedbackForm") @Valid FeedbackForm form, 
			  BindingResult result, final RedirectAttributes redirectAttributes) {
		
    	if (result.hasErrors()) {
            return "/feedback";
        }

		User user = null;
		
		try{
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    	String name = auth.getName();
			user = userService.findByName(name);
		}catch(Exception ex) {
			//OK - user is not required to be logged in
		}
		
		messageService.sendFeedback(user, form);

    	redirectAttributes.addFlashAttribute("css", "success");
		redirectAttributes.addFlashAttribute("msg", "Your feedback has been successfully sent!");
		
    	return "redirect:/feedback";
	}
	
	/** ----------------------- Registration ----------------------- **/
	
	/**
	 * Registration form request
	 **/
	@RequestMapping(value = {"/register"}, method = RequestMethod.GET)
	public String register(Model model) {
	
        User user = new User();
        model.addAttribute("user", user);
		return "/users/register";
	}	

	/**
	 * Registration form submit
	 */
	@RequestMapping(value = {"/register"}, method = RequestMethod.POST)
	public String register(@ModelAttribute("user") @Valid User userInfo, 
			  BindingResult result, HttpServletRequest request, HttpServletResponse response) {
		
    	if (result.hasErrors()) {
            return "/users/register";
        }
 
    	userInfo.setRole(User.ROLE_USER);
   		userService.registerNewUser(userInfo);
   		
   		try{
   			//auto login after registration
   	   		request.login(userInfo.getName(), userInfo.getPassword());
   		}catch (Exception ex) {
   			//automatic login failed? not a big deal
   			logger.error("Automatic login after registration for user "+userInfo.getName()+" failed",ex);
   		}
 
		return "redirect:/map";
	}	
	
	/**
	 * Confirm registration (using link from registration email)
	 * @param uuid uuid which identifies the registration request/message
	 * @param user_id user id for whom registration was sent
	 */
	@RequestMapping(value = "/confirm_reg", method = RequestMethod.GET)
	public String confirmEmail(@RequestParam(value = "uuid", required = true) String uuid,
			@RequestParam(value = "user_id", required = true) String userId,
			final RedirectAttributes redirectAttributes) {

		try{
			int id = Integer.valueOf(userId);
			if (!messageService.checkRegistrationUUID(id, uuid)) {
				throw new Exception("Registration details not found or expired!");
			}

			userService.setUserStatus(id, User.STATUS_ACTIVE);
			
		}catch(Exception ex){
			redirectAttributes.addFlashAttribute("css", "danger");
			redirectAttributes.addFlashAttribute("msg", "Error while activating the account:"+ex.getMessage()+". Please contact support.");
			return "redirect:/login";
		}
		redirectAttributes.addFlashAttribute("css", "success");
		redirectAttributes.addFlashAttribute("msg", "Your email is confirmed!");
		
		return "redirect:/login";
	}	

	/** ----------------------- Password Reset ----------------------- **/
	
	/**
	 * Reset password form request
	 */
	@RequestMapping(value = {"/restore"}, method = RequestMethod.GET)
	public String restore() {
		return "/users/user_restore";
	}	

	/**
	 * Reset password form submit
	 */
    @RequestMapping(value = {"/restore"}, method = RequestMethod.POST)
    public String restore(HttpServletRequest request, RedirectAttributes redirectAttributes){
    	
        // this way you get value of the input you want
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        
        User user = null;
        
        //TODO:encapsulate -> to validator
        
        if (!username.isEmpty()) {
        	user = userService.findByName(username);
        	if (user == null) {
                redirectAttributes.addFlashAttribute("css", "danger");
        		redirectAttributes.addFlashAttribute("msg", "User "+username+" not found!");
        		return "redirect:/restore";
        	}
        } else if (!email.isEmpty()) {
        	user = userService.findByEmail(email);
        	if (user == null) {
                redirectAttributes.addFlashAttribute("css", "danger");
        		redirectAttributes.addFlashAttribute("msg", "User with email "+email+" not found!");
        		return "redirect:/restore";
        	}
        } else {
            redirectAttributes.addFlashAttribute("css", "danger");
    		redirectAttributes.addFlashAttribute("msg", "Please specify either username or email");
    		return "redirect:/restore";
        }
        
        userService.restoreUserPassword(user);
        
        redirectAttributes.addFlashAttribute("css", "success");
		redirectAttributes.addFlashAttribute("msg", "Message has been sent!");
				
        return "redirect:/restore";
    }

    /**
	* Confirm password reset and create new password (using link from pass reset email)
	* @param uuid uuid which identifies the registration request/message
	* @param user_id user id for whom registration was sent
	*/	
    @RequestMapping(value = "/confirm_reset", method = RequestMethod.GET)
	public String confirmPasswordReset(@RequestParam(value = "uuid", required = false) String uuid,
			@RequestParam(value = "user_id", required = false) String userId,Model model,
			final RedirectAttributes redirectAttributes) {

		try{

			if (!messageService.checkPasswordResetUUID(Integer.valueOf( userId ), uuid)) {
				throw new Exception("Password reset details not found or expired!");
			}

		}catch(Exception ex){
			redirectAttributes.addFlashAttribute("css", "danger");
			redirectAttributes.addFlashAttribute("msg", "Error while trying to reset the password:"+ex.getMessage()+". Please contact support.");
			return "redirect:/login";
		}
		
		PassResetForm form = new PassResetForm();
		form.setId(Integer.valueOf( userId ));
		model.addAttribute("passResetForm", form);
		
		return "/users/new_pass";
	}	
    
	/**
	 * New password submit
	 */
	@RequestMapping(value = { "/confirm_reset" }, method = RequestMethod.POST)
	public String newPasswordSubmit(@ModelAttribute("passResetForm") PassResetForm form,  
			  BindingResult result, final RedirectAttributes redirectAttributes) {

		PassResetFormValidator validator = new PassResetFormValidator();
		validator.validate(form, result);
		
		if (result.hasErrors()) {
			return "/users/new_pass";
		}

		userService.setUserPassword(form.getId(),form.getPassword());

		redirectAttributes.addFlashAttribute("css", "success");
		redirectAttributes.addFlashAttribute("msg", "Password updated successfully!");
		
		return "redirect:/login";
	}  
	/** ----------------------- User self profile ----------------------- **/
	
	/**
	 * Show self profile
	 */
	@RequestMapping(value = {"/users/me"}, method = RequestMethod.GET)
	public String profile(Model model) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String name = auth.getName();
	      
		User user = userService.findByName(name);

		//need to fill both fields with same value. so it is not required to update password unless user wants to.
		//otherwise confirm pass field will be blank and both password fields have to be filled again! 
		user.setConfirmPassword(user.getPassword());
		
        model.addAttribute("user", user);
		return "/users/user_profile_edit";
	}	
	
	/**
	 * Profile form submit 
	 */
	@RequestMapping(value = {"/users/me"}, method = RequestMethod.POST)
	public String profile(@ModelAttribute("user") @Valid User user, 
			  BindingResult result, final RedirectAttributes redirectAttributes) {
		
		if (result.hasErrors()) {
            return "/users/user_profile_edit";
        }

		User currentUser = userService.findById(user.getId());

		StringBuilder builder = new StringBuilder();
		builder.append("User Profile updated successfully!\n");

		//so user has changed his email
		if (!currentUser.getEmail().equals(user.getEmail())){
			builder.append("Activation email is sent to "+user.getEmail()+". Please check you mailbox and confirm it.");
			messageService.sendRegistrationMessage(user);
		}
		
    	// Add message to flash scope
    	redirectAttributes.addFlashAttribute("css", "success");
   		redirectAttributes.addFlashAttribute("msg", builder.toString());
    	
   		userService.saveUser(user);
 
		return "redirect:/users/me";
	}		
	
	/**
	 * Resend registration email (requested by user to himself).
	 */
	@RequestMapping(value = "/users/me/send_reg", method = RequestMethod.GET)
	public String sendMeConfirmEmail(final RedirectAttributes redirectAttributes) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String name = auth.getName();
		User user = userService.findByName(name);
	
		messageService.sendRegistrationMessage(user);
		
		redirectAttributes.addFlashAttribute("css", "success");
		redirectAttributes.addFlashAttribute("msg", "Registration email is sent to "+user.getEmail());
		
		return "redirect:/users/me";
	}	

	/**
	 * Send password reset email (requested by user to himself).
	 */
	@RequestMapping(value = "/users/me/send_reset", method = RequestMethod.GET)
	public String sendMePasswordResetEmail(final RedirectAttributes redirectAttributes) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String name = auth.getName();
		User user = userService.findByName(name);
	
		messageService.sendPasswordResetMessage(user);
		
		redirectAttributes.addFlashAttribute("css", "success");
		redirectAttributes.addFlashAttribute("msg", "Password Reset email is sent!");
		
		return "redirect:/users/me";
	}	
	
	/** ------------------------------------- Admin forms and functions -------------------------------------- **/
	
    /**
     * Show user list (for admin) 
     */
	@RequestMapping(value = {"/users","/users/list"}, method = RequestMethod.GET)
	public String listUsers(Model model) {

		List<User> list = userService.findAll();
		model.addAttribute("userList", list);

		return "/users/user_list";
	}
	
	/**
	 * Adding user by admin. Form request
	 */
	@RequestMapping(value = {"/users/add"}, method = RequestMethod.GET)
	public String addUser(Model model) {
        
		User user = new User();
        user.setRole(User.ROLE_USER);
        model.addAttribute("userForm", user);
        
		return "/users/user_edit";
	}	

	/**
	 * Updating user by admin. Form request
	 */
	@RequestMapping(value = "/users/{id}/update", method = RequestMethod.GET)
	public String showUpdateUserForm(@PathVariable("id") int id, Model model) {

		User user = userService.findById(id);
		//need to fill both fields with same value. so it is not required to update password if it not necessary.
		//otherwise confirm pass field will be blank and both password fields have to be filled again! 
		user.setConfirmPassword(user.getPassword());
		model.addAttribute("userForm", user);
		
		return "/users/user_edit";
	}	

	
	/**
	 * Create or Update user form submit (by admin)
	 */
	@RequestMapping(value = {"/users"}, method = RequestMethod.POST)
	public String addUser(@ModelAttribute("userForm") @Valid User user, 
			  BindingResult result, final RedirectAttributes redirectAttributes) {
		
    	if (result.hasErrors()) {
            return "/users/user_edit";
        }
    	
    	// Add message to flash scope
    	redirectAttributes.addFlashAttribute("css", "success");
    	if(user.isNew()){
    		redirectAttributes.addFlashAttribute("msg", "User added successfully!");
    	}else{
    		redirectAttributes.addFlashAttribute("msg", "User updated successfully!");
    	}
    	
    	//as user is created by admin, he is already active!
    	user.setStatus(User.STATUS_ACTIVE);
    				
   		userService.saveUser(user);
 
		return "redirect:/users/list";
	}		
	
	/**
	 * Delete user
	 */
	@RequestMapping(value = "/users/{id}/delete", method = RequestMethod.POST)
	public String deleteUser(@PathVariable("id") int id, 
		final RedirectAttributes redirectAttributes) {

		logger.debug("deleteUser() : {}", id);

		userService.deleteById(id);
		
		redirectAttributes.addFlashAttribute("css", "success");
		redirectAttributes.addFlashAttribute("msg", "User is deleted!");
		
		return "redirect:/users/list";

	}	

	/**
	 * Resend registration email (requested by admin to selected user).
	 */
	@RequestMapping(value = "/users/{id}/send_reg", method = RequestMethod.GET)
	public String sendUserConfirmEmail(@PathVariable("id") int id, 
			final RedirectAttributes redirectAttributes) {

		User user = userService.findById(id);
	
		messageService.sendRegistrationMessage(user);
		
		redirectAttributes.addFlashAttribute("css", "success");
		redirectAttributes.addFlashAttribute("msg", "Registration email is sent!");
		
		return "redirect:/users/list";
	}	

	/**
	 * Send password reset email (requested by admin to selected user).
	 */
	@RequestMapping(value = "/users/{id}/send_reset", method = RequestMethod.GET)
	public String sendPasswordResetEmail(@PathVariable("id") int id, 
			final RedirectAttributes redirectAttributes) {

		User user = userService.findById(id);
	
		messageService.sendPasswordResetMessage(user);
		
		redirectAttributes.addFlashAttribute("css", "success");
		redirectAttributes.addFlashAttribute("msg", "Password Reset email is sent!");
		
		return "redirect:/users/list";
	}		
	
	/**
	 * Getting the list of roles from the form
	 */
	@ModelAttribute("roleList")
    public String [] roleList(){

        return User.ROLE_LIST;
    }	
    
}

