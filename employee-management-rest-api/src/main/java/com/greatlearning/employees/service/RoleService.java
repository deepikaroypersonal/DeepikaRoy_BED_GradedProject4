package com.greatlearning.employees.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import com.greatlearning.employees.model.Role;
import com.greatlearning.employees.repository.RoleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleService
{
	private final RoleRepository roleRepo;

	public Set<Role> fetchAllRoles()
	{
		return new HashSet<>(roleRepo.findAll());
	}

	public Role addRole(Role role)
	{
		return roleRepo.save(role);
	}

}
