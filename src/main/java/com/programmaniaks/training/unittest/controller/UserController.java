package com.programmaniaks.training.unittest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.programmaniaks.training.unittest.entity.User;
import com.programmaniaks.training.unittest.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/connect", method=RequestMethod.POST, produces="application/json")
	@ResponseBody
	public User connect(String username, String password) {
		return userService.login(username, password);
	}
	
	@RequestMapping(value="/", method=RequestMethod.PUT, produces="application/json")
	@ResponseBody
	public User create(@RequestBody(required=true) User user) {
		userService.create(user);
		return user;
	}
	

}
