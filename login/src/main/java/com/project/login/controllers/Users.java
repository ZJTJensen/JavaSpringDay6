package com.project.login.controllers;

import com.project.login.models.User;
import com.project.login.services.UserService;
import com.project.login.validators.UserValidator;
import java.security.Principal;
import java.util.List;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Users {
	private UserService userService;
	 // NEW
	 private UserValidator userValidator;
    
	 // NEW
	 public Users(UserService userService, UserValidator userValidator) {
		 this.userService = userService;
		 this.userValidator = userValidator;
	 }
	
	 @RequestMapping("/registration")
    public String registerForm(@Valid @ModelAttribute("user") User user) {
        return "registrationPage";
    }
	@PostMapping("/registration")
    public String registration(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {
        // NEW
        userValidator.validate(user, result);
        if (result.hasErrors()) {
            return "registrationPage";
        }
        
        userService.saveUserWithAdminRole(user);
        return "redirect:/login";
    }
    @RequestMapping("/admin")
    public String adminPage(Principal principal, Model model) {
        String username = principal.getName();
        List<User> user = userService.allUsers();
        model.addAttribute("currentUser", userService.findByEmail(username));
        model.addAttribute("users", user);
        return "adminPage";
    }
    
    
	@RequestMapping("/login")
    public String login(Principal principal, @RequestParam(value="error", required=false) String error, @RequestParam(value="logout", required=false) String logout, Model model) {
        String username = principal.getName();
        User currentUser = userService.findByEmail(username);
        if(error != null) {
            model.addAttribute("errorMessage", "Invalid Credentials, Please try again.");
        }
        if(logout != null) {
            model.addAttribute("logoutMessage", "Logout Successful!");
        }
        if(currentUser.users_roles = "ROLE_ADMIN"){
            return "redirect:/admin";
        }
        return "loginPage";
    }
    @RequestMapping(value = {"/", "/home"})
    public String home(Principal principal, Model model) {
        String email = principal.getName();
        model.addAttribute("currentUser", userService.findByEmail(email));
        return "homePage";
    }
}
