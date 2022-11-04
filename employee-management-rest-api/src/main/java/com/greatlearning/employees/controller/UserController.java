package com.greatlearning.employees.controller;

import java.util.Optional;
import java.util.Set;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greatlearning.employees.model.Users;
import com.greatlearning.employees.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/register/users")
public class UserController
{
	private final UserService userService;
	
	@GetMapping
	public Set<Users> getAllUsers()
	{
		return userService.fetchAllUsers();
	}
	
	//Create End point
	@PostMapping
	public Users addRole(@RequestBody Users user)
	{
		return userService.addUser(user);
	}	
}
