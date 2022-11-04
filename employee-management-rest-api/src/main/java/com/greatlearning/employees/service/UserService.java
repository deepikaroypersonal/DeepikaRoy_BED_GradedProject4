package com.greatlearning.employees.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import com.greatlearning.employees.model.Users;
import com.greatlearning.employees.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService
{
	private final UserRepository userRepo;

	public Set<Users> fetchAllUsers()
	{
		return new HashSet<>(userRepo.findAll());
	}

	public Users addUser(Users user)
	{
		return userRepo.save(user);
	}

}
