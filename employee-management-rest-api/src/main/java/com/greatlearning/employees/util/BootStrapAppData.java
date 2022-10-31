package com.greatlearning.employees.util;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.greatlearning.employees.model.Employee;
import com.greatlearning.employees.model.Role;
import com.greatlearning.employees.model.User;
import com.greatlearning.employees.repository.EmployeeRepository;
import com.greatlearning.employees.repository.UserRepository;

import lombok.Builder;
import lombok.RequiredArgsConstructor;

@Builder
@Configuration
@RequiredArgsConstructor
public class BootStrapAppData 
{
	private final PasswordEncoder encoder;
	private final EmployeeRepository empRepo;
	private final UserRepository userRepo;

	@EventListener(ApplicationReadyEvent.class)
	public void insert(ApplicationReadyEvent event)
	{		
		Role userRole =  new Role();
		userRole.setRoleName("USER");
		
		Role adminRole =  new Role();
		adminRole.setRoleName("ADMIN");
		
		User adminUser = new User();
		adminUser.setEmailAddress("aarti@gmail.com");
		adminUser.setUsername("aartis");
		adminUser.setPassword(encoder.encode("test123"));
		adminUser.addRole(adminRole);
		
		User normalUser = new User();
		normalUser.setEmailAddress("kavita@gmail.com");
		normalUser.setUsername("kavitas");
		normalUser.setPassword(encoder.encode("xyz123"));
		normalUser.addRole(userRole);

		userRepo.save(adminUser);
		userRepo.save(normalUser);
		
		Employee e = new Employee();
		e.setFirstName("Ramesh");
		e.setLastName("Sharma");
		e.setEmail("ramesh@gmail.com");
		empRepo.save(e);
	}
}
