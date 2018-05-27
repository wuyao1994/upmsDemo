package com.upms.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.upms.domain.UserCreateForm;
import com.upms.validator.UserCreateFormValidator;
import com.upms.service.UserService;

@Controller
public class UserController {
	private static final Logger		LOGGER	= LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService				userService;

	@Autowired
	private UserCreateFormValidator	userCreateFormValidator;



	@InitBinder("form")
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(userCreateFormValidator);
	}



	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/user/create", method = RequestMethod.GET)
	public String getUserCreateFrom(Model model) {
		return "userCreate";
	}



	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/user/create", method = RequestMethod.POST)
	public String handleUserCreateFrom(@Valid @ModelAttribute("form") UserCreateForm form, BindingResult bindingResult,
			Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("Errors", bindingResult.getAllErrors());
            return "userCreate";
		}
		try {
			userService.create(form);
		} catch (DataIntegrityViolationException e) {
			bindingResult.reject("email.exists", "Email already exists");
			return "userCreate";
		}
		return "redirect:/users";
	}



	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public String getUsers(Model model) {
		model.addAttribute("users", userService.getAllUsers());
		return "users";
	}
}
