package com.skywell.social.controllers;

import com.skywell.social.entity.User;
import com.skywell.social.repositories.UserRepository;
import com.skywell.social.entity.UserAuthentication;
import com.skywell.social.entity.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

	@Autowired
	UserRepository userRepository;

	@RequestMapping(value = "/api/user/current", method = RequestMethod.GET)
	public Object getCurrent() {
		return SecurityContextHolder.getContext().getAuthentication();
	}

	@RequestMapping(value = "/admin/api/user", method = RequestMethod.GET)
	public List<User> list() {
		return userRepository.findAll();
	}
}
