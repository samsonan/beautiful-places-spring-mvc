package com.samsonan.bplaces.web;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
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

import com.samsonan.bplaces.exception.UserNotFoundException;
import com.samsonan.bplaces.model.FeedbackForm;
import com.samsonan.bplaces.model.PassResetForm;
import com.samsonan.bplaces.model.User;
import com.samsonan.bplaces.service.UserMessageService;
import com.samsonan.bplaces.service.UserService;
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
public class UserController extends BaseController {
    
    public static final String REQUEST_FEEDBACK = "/feedback";
    public static final String REQUEST_MAP = "/map";
    public static final String REQUEST_LOGIN = "/login"; 
    public static final String REQUEST_MY_PROFILE = "/users/me";
    public static final String REQUEST_USER_LIST = "/users/list";
    public static final String REQUEST_USER_RESTORE = "/restore";
    
    public static final String VIEW_MAP = "/map";
	public static final String VIEW_FEEDBACK = "/feedback";
	public static final String VIEW_USER_LOGIN = "users/login";
	public static final String VIEW_USER_REGISTER = "users/register";
	public static final String VIEW_USER_RESTORE = "users/user_restore";
    public static final String VIEW_USER_NEW_PASS = "users/new_pass";
    public static final String VIEW_USER_EDIT = "users/user_edit";
    public static final String VIEW_USER_LIST = "users/user_list";
    public static final String VIEW_USER_PROFILE_VIEW = "users/profile_view";
    public static final String VIEW_USER_PROFILE_EDIT = "users/profile_edit";
	
	
	private final Logger logger = LoggerFactory.getLogger(UserController.class);	
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserMessageService messageService;
	
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

	@ExceptionHandler(UserNotFoundException.class)
    public String handleResourceNotFoundException() {
        return VIEW_404;
    }	
	/**
	 * Redirecting to custom login page
	 * @return
	 */
	@RequestMapping(value = {"/login"}, method = RequestMethod.GET)
	public String login() {
		return VIEW_USER_LOGIN;
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
		return VIEW_FEEDBACK;
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
            return VIEW_FEEDBACK;
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

    	redirectAttributes.addFlashAttribute(FLASH_CSS_ATTR, FLASH_CSS_VALUE_OK);
		redirectAttributes.addFlashAttribute(FLASH_MSG_ATTR, getMessage("feedback.meesage.sent"));
		
    	return createRedirectViewPath(REQUEST_FEEDBACK);
	}
	
	/** ----------------------- Registration ----------------------- **/
	
	/**
	 * Registration form request
	 **/
	@RequestMapping(value = {"/register"}, method = RequestMethod.GET)
	public String register(Model model) {
	
        User user = new User();
        model.addAttribute("user", user);
		return VIEW_USER_REGISTER;
	}	

	/**
	 * Registration form submit
	 */
	@RequestMapping(value = {"/register"}, method = RequestMethod.POST)
	public String register(@ModelAttribute("user") @Valid User userInfo, 
			  BindingResult result, HttpServletRequest request, HttpServletResponse response) {
		
    	if (result.hasErrors()) {
            return VIEW_USER_REGISTER;
        }
 
    	userInfo.setRole(User.ROLE_USER);
   		userService.registerNewUser(userInfo);
   		
   		try{
   			//auto login after registration
   	   		request.login(userInfo.getName(), userInfo.getPassword());
   		}catch (Exception ex) {
   			//automatic login failed? not a big deal
   			//logger.error("Automatic login after registration for user "+userInfo.getName()+" failed",ex);
   		}
 
		return createRedirectViewPath(REQUEST_MAP);
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
				throw new Exception(getMessage("user.activate.reg.not_found"));
			}

			userService.setUserStatus(id, User.STATUS_ACTIVE);
			
		}catch(Exception ex){
			redirectAttributes.addFlashAttribute(FLASH_CSS_ATTR, FLASH_CSS_VALUE_ERROR);
			redirectAttributes.addFlashAttribute(FLASH_MSG_ATTR, getMessage("user.activate.error", ex.getMessage()));
			return createRedirectViewPath(VIEW_USER_LOGIN);
		}
		redirectAttributes.addFlashAttribute(FLASH_CSS_ATTR, FLASH_CSS_VALUE_OK);
		redirectAttributes.addFlashAttribute(FLASH_MSG_ATTR, getMessage("user.activate.ok"));
		
		return createRedirectViewPath(REQUEST_LOGIN);
	}	

	/** ----------------------- Password Reset ----------------------- **/
	
	/**
	 * Reset password form request
	 */
	@RequestMapping(value = {"/restore"}, method = RequestMethod.GET)
	public String restore() {
		return VIEW_USER_RESTORE;
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
                redirectAttributes.addFlashAttribute(FLASH_CSS_ATTR, FLASH_CSS_VALUE_ERROR);
        		redirectAttributes.addFlashAttribute(FLASH_MSG_ATTR, getMessage("user.name.not_found", username));
        		return createRedirectViewPath(REQUEST_USER_RESTORE);
        	}
        } else if (!email.isEmpty()) {
        	user = userService.findByEmail(email);
        	if (user == null) {
                redirectAttributes.addFlashAttribute(FLASH_CSS_ATTR, FLASH_CSS_VALUE_ERROR);
        		redirectAttributes.addFlashAttribute(FLASH_MSG_ATTR, getMessage("user.email.not_found", email));
        		return createRedirectViewPath(REQUEST_USER_RESTORE);
        	}
        } else {
            redirectAttributes.addFlashAttribute(FLASH_CSS_ATTR, FLASH_CSS_VALUE_ERROR);
    		redirectAttributes.addFlashAttribute(FLASH_MSG_ATTR, getMessage("user.name_email.empty"));
    		return createRedirectViewPath(REQUEST_USER_RESTORE);
        }
        
        userService.restoreUserPassword(user);
        
        redirectAttributes.addFlashAttribute(FLASH_CSS_ATTR, FLASH_CSS_VALUE_OK);
		redirectAttributes.addFlashAttribute(FLASH_MSG_ATTR, getMessage("message.sent"));
				
		return createRedirectViewPath(REQUEST_USER_RESTORE);
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
				throw new Exception(getMessage("user.password.reset.not_found"));
			}

		}catch(Exception ex){
			redirectAttributes.addFlashAttribute(FLASH_CSS_ATTR, FLASH_CSS_VALUE_ERROR);
			redirectAttributes.addFlashAttribute(FLASH_MSG_ATTR, getMessage("user.password.reset.error",ex.getMessage()));
			return createRedirectViewPath(REQUEST_LOGIN);
		}
		
		PassResetForm form = new PassResetForm();
		form.setId(Integer.valueOf( userId ));
		model.addAttribute("passResetForm", form);
		
		return VIEW_USER_NEW_PASS;
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
			return VIEW_USER_NEW_PASS;
		}

		userService.setUserPassword(form.getId(),form.getPassword());

		redirectAttributes.addFlashAttribute(FLASH_CSS_ATTR, FLASH_CSS_VALUE_OK);
		redirectAttributes.addFlashAttribute(FLASH_MSG_ATTR, getMessage("user.password.update.ok"));
		
		return createRedirectViewPath(REQUEST_LOGIN);
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
        return VIEW_USER_PROFILE_EDIT;
	}	
	
	/**
	 * Profile form submit 
	 */
	@RequestMapping(value = {"/users/me"}, method = RequestMethod.POST)
	public String profile(@ModelAttribute("user") @Valid User user, 
			  BindingResult result, final RedirectAttributes redirectAttributes) {
		
		if (result.hasErrors()) {
            return VIEW_USER_PROFILE_EDIT;
        }

		User currentUser = userService.findById(user.getId());

		StringBuilder builder = new StringBuilder();
		builder.append(getMessage("user.profile.update.ok"));

		//so user has changed his email
		if (!currentUser.getEmail().equals(user.getEmail())){
			builder.append(getMessage("user.registration.sent", user.getEmail()));
			messageService.sendRegistrationMessage(user);
		}
		
    	// Add message to flash scope
    	redirectAttributes.addFlashAttribute(FLASH_CSS_ATTR, FLASH_CSS_VALUE_OK);
   		redirectAttributes.addFlashAttribute(FLASH_MSG_ATTR, builder.toString());
    	
   		userService.saveUser(user);
 
		return createRedirectViewPath(REQUEST_MY_PROFILE);
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
		
		redirectAttributes.addFlashAttribute(FLASH_CSS_ATTR, FLASH_CSS_VALUE_OK);
		redirectAttributes.addFlashAttribute(FLASH_MSG_ATTR, getMessage("user.registration.user.sent", user.getEmail()));
		
		return createRedirectViewPath(REQUEST_MY_PROFILE);
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
		
		redirectAttributes.addFlashAttribute(FLASH_CSS_ATTR, FLASH_CSS_VALUE_OK);
		redirectAttributes.addFlashAttribute(FLASH_MSG_ATTR, getMessage("user.reset.sent"));
		
		return createRedirectViewPath(REQUEST_MY_PROFILE);
	}	
	
	/** ------------------------------------- Admin forms and functions -------------------------------------- **/
	
    /**
     * Show user list (for admin) 
     */
	@RequestMapping(value = {"/users","/users/list"}, method = RequestMethod.GET)
	public String listUsers(Model model) {

		List<User> list = userService.findAll();
		model.addAttribute("userList", list);

        return VIEW_USER_LIST;
	}
	
	/**
	 * Adding user by admin. Form request
	 */
	@RequestMapping(value = {"/users/add"}, method = RequestMethod.GET)
	public String addUser(Model model) {
        
		User user = new User();
        user.setRole(User.ROLE_USER);
        model.addAttribute("userForm", user);
        
        return VIEW_USER_EDIT;
	}	

	/**
	 * Updating user by admin. Form request
	 * @throws UserNotFoundException user with requested id not found 
	 */
	@RequestMapping(value = "/users/{id}/update", method = RequestMethod.GET)
	public String showUpdateUserForm(@PathVariable("id") int id, Model model) throws UserNotFoundException {

		User user = userService.findById(id);
		if (user == null)
			throw new UserNotFoundException();
		
		//need to fill both fields with same value. so it is not required to update password if it not necessary.
		//otherwise confirm pass field will be blank and both password fields have to be filled again! 
		user.setConfirmPassword(user.getPassword());
		model.addAttribute("userForm", user);
		
        return VIEW_USER_EDIT;
	}	

	
	/**
	 * Create or Update user form submit (by admin)
	 */
	@RequestMapping(value = {"/users"}, method = RequestMethod.POST)
	public String addUser(@ModelAttribute("userForm") @Valid User user, 
			  BindingResult result, final RedirectAttributes redirectAttributes) {
		
    	if (result.hasErrors()) {
            return VIEW_USER_EDIT;
        }
    	
    	// Add message to flash scope
    	redirectAttributes.addFlashAttribute(FLASH_CSS_ATTR, FLASH_CSS_VALUE_OK);
    	if(user.isNew()){
    		redirectAttributes.addFlashAttribute(FLASH_MSG_ATTR, getMessage("user.add.ok"));
    	}else{
    		redirectAttributes.addFlashAttribute(FLASH_MSG_ATTR, getMessage("user.update.ok"));
    	}
    	
    	//as user is created by admin, he is already active!
    	user.setStatus(User.STATUS_ACTIVE);
    				
   		userService.saveUser(user);
 
		return createRedirectViewPath(REQUEST_USER_LIST);
	}		
	
	/**
	 * Delete user
	 */
	@RequestMapping(value = "/users/{id}/delete", method = RequestMethod.POST)
	public String deleteUser(@PathVariable("id") int id, 
		final RedirectAttributes redirectAttributes) throws UserNotFoundException {

		logger.debug("deleteUser() : {}", id);
		
		userService.deleteById(id);
		
		redirectAttributes.addFlashAttribute(FLASH_CSS_ATTR, FLASH_CSS_VALUE_OK);
		redirectAttributes.addFlashAttribute(FLASH_MSG_ATTR, getMessage("user.delete.ok"));
		
		return createRedirectViewPath(REQUEST_USER_LIST);
	}	

	/**
	 * Resend registration email (requested by admin to selected user).
	 * @throws UserNotFoundException 
	 */
	@RequestMapping(value = "/users/{id}/send_reg", method = RequestMethod.GET)
	public String sendUserConfirmEmail(@PathVariable("id") int id, 
			final RedirectAttributes redirectAttributes) throws UserNotFoundException {

		User user = userService.findById(id);
	
		if (user == null)
			throw new UserNotFoundException();
		
		messageService.sendRegistrationMessage(user);
		
		redirectAttributes.addFlashAttribute(FLASH_CSS_ATTR, FLASH_CSS_VALUE_OK);
		redirectAttributes.addFlashAttribute(FLASH_MSG_ATTR, getMessage("user.registration.admin.sent", user.getEmail()));
		
		return createRedirectViewPath(REQUEST_USER_LIST);
	}	

	/**
	 * Send password reset email (requested by admin to selected user).
	 * @throws UserNotFoundException 
	 */
	@RequestMapping(value = "/users/{id}/send_reset", method = RequestMethod.GET)
	public String sendPasswordResetEmail(@PathVariable("id") int id, 
			final RedirectAttributes redirectAttributes) throws UserNotFoundException {

		User user = userService.findById(id);
	
		if (user == null)
			throw new UserNotFoundException();

		messageService.sendPasswordResetMessage(user);
		
		redirectAttributes.addFlashAttribute(FLASH_CSS_ATTR, FLASH_CSS_VALUE_OK);
		redirectAttributes.addFlashAttribute(FLASH_MSG_ATTR, getMessage("user.reset.sent"));
		
		return createRedirectViewPath(REQUEST_USER_LIST);
	}		
	
	/**
	 * Getting the list of roles from the form
	 */
	@ModelAttribute("roleList")
    public String [] roleList(){

        return User.ROLE_LIST;
    }	
	
    
}

