package com.upms.validator;

import com.upms.domain.UserCreateForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.upms.service.user.UserService;

@Component
public class UserCreateFormValidator implements Validator {
	private static final Logger	LOGGER	= LoggerFactory.getLogger(UserCreateFormValidator.class);

	@Autowired
	private UserService			userService;



	@Override
	public boolean supports(Class<?> aClass) {
		return aClass.equals(UserCreateForm.class);
	}



	@Override
	public void validate(Object o, Errors errors) {
		LOGGER.debug("Validating {}", o);
		UserCreateForm form = (UserCreateForm) o;
		validatePassword(errors, form);
		validateEmail(errors, form);
	}



	private void validatePassword(Errors errors, UserCreateForm form) {
		if (!form.getPassword().equals(form.getPasswordRepeated())) {
			errors.reject("password.mo_match", "password do not match");
		}
	}



	private void validateEmail(Errors errors, UserCreateForm form) {
		if (userService.getUserByEmail(form.getEmail()).isPresent()) {
			errors.reject("email.exists", "email already exists");
		}
	}
}
