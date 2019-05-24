package com.springsecuritywithoutannotation.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SpringSecurityWithoutAnnotationController {

	@RequestMapping(value = { "/", "/visitor**" }, method = RequestMethod.GET)
	public ModelAndView welcomePage() {

		ModelAndView model = new ModelAndView();
		model.addObject("tt", "welcome visitor");
		model.addObject("message", "navigate on other link!");
		model.setViewName("home_page");
		return model;

	}

	@RequestMapping(value = "/administrator", method = RequestMethod.GET)
	public ModelAndView adminPage() {

		ModelAndView model = new ModelAndView();
		model.addObject("tt", "welcome to admin portal");
		model.addObject("message", "enjoy browsing!");
		model.setViewName("administrator_portal");

		return model;

	}
	
	/*@RequestMapping(value = "/administrator_login", method = RequestMethod.GET)
	public ModelAndView adminPage1() {

		ModelAndView model = new ModelAndView();
		model.addObject("tt", "welcome to admin portal");
		model.addObject("message", "enjoy browsing!");
		model.setViewName("administrator_portal");

		return model;

	}*/
	
	
	@RequestMapping(value = "/employeeLogin", method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,@RequestParam(value = "logout", required = false) String logout) {

		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Invalid username and password!");
		}

		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		}
		model.setViewName("employeeLogin");

		return model;

	}
	
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public ModelAndView accesssDenied() {

		ModelAndView model = new ModelAndView();
		
		//check if user is login
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			System.out.println(userDetail);
		
			model.addObject("username", userDetail.getUsername());
			
		}
		
		model.setViewName("403");
		return model;

	}


}