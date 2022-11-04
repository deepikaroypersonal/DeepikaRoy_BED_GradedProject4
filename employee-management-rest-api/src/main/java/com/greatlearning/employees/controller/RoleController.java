package com.greatlearning.employees.controller;

import java.util.Optional;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.greatlearning.employees.model.Role;
import com.greatlearning.employees.service.RoleService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/register/roles")
public class RoleController
{
	private final RoleService roleService;
	
	@GetMapping
	public Set<Role> getAllRoles()
	{
		return roleService.fetchAllRoles();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Role addRole(@RequestBody Role role)
	{
		return roleService.addRole(role);
	}	
	
}
