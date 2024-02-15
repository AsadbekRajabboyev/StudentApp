/******************************************************************************
 * Copyright (c)  2/15/24, 10:19 PM                                           *
 * Asadbek Rajabboyev                                                         *
 ******************************************************************************/

package uz.asadbek.student.app.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import uz.asadbek.student.app.models.User;
import uz.asadbek.student.app.securityService.UserDetailsService;

public class Uservalidator implements Validator {

	private final UserDetailsService userDetailsService;
	@Autowired
	public Uservalidator(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		User user = (User) target;
		try {
			userDetailsService.loadUserByUsername(user.getUsername());
		}catch (UsernameNotFoundException e){
			return;
		}
		errors.rejectValue("username", "","Bunday username avval ro'yhatdan o'tgaan");

	}
}
