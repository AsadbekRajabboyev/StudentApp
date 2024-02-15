/******************************************************************************
 * Copyright (c)  2/15/24, 9:02 PM                                            *
 * Asadbek Rajabboyev                                                         *
 ******************************************************************************/

package uz.asadbek.student.app.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import uz.asadbek.student.app.dto.UserLoginDto;
import uz.asadbek.student.app.dto.UserRegisterDto;
import uz.asadbek.student.app.models.User;
import uz.asadbek.student.app.securityService.UserDetails;
import uz.asadbek.student.app.securityService.UserDetailsService;
import uz.asadbek.student.app.util.Uservalidator;

@RestController
@RequestMapping("api/auth")
public class AuthController {

	private final UserDetailsService service;
	private final Uservalidator uservalidator;
	private final ModelMapper modelMapper;

	@Autowired
	public AuthController(UserDetailsService service, Uservalidator uservalidator, ModelMapper modelMapper) {
		this.service = service;
		this.uservalidator = uservalidator;
		this.modelMapper = modelMapper;
	}

	@PostMapping("/register")
	public ResponseEntity<String> registerPersonJson(@RequestBody @Valid UserRegisterDto userRegisterDto, BindingResult bindingResult) {
		uservalidator.validate(userRegisterDto, bindingResult);
		if (bindingResult.hasErrors()) {
			return ResponseEntity.badRequest().body("Invalid user data");
		}

		service.registerPerson(convertToUser(userRegisterDto));
		return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
	}


	@PostMapping("/login")
	public ResponseEntity<String> loginUser(@RequestBody UserLoginDto loginRequest) {
		String username = loginRequest.getUsername();
		String password = loginRequest.getPassword();

		UserDetails userDetails;
		try {
			userDetails = service.loadUserByUsername(username);
		} catch (UsernameNotFoundException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
		}

		if (password.equals(userDetails.getPassword())) {
			return ResponseEntity.ok("User logged in successfully");
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
		}
	}

	/*****************Converts***********************************************************************************/
	private User convertToUser(UserRegisterDto userRegisterDto) {
		return modelMapper.map(userRegisterDto, User.class);
	}
	/****************Converst************************************************************************************/


}
