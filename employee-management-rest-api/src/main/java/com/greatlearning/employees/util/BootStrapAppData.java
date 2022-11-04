package com.greatlearning.employees.util;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.github.javafaker.Faker;
import com.greatlearning.employees.model.Employee;
import com.greatlearning.employees.repository.EmployeeRepository;

import lombok.Builder;
import lombok.RequiredArgsConstructor;

@Builder
@Configuration
@RequiredArgsConstructor
public class BootStrapAppData 
{
	private final Faker fakeEmp = new Faker();	
	private final EmployeeRepository empRepo;

	@EventListener(ApplicationReadyEvent.class)
	public void insert(ApplicationReadyEvent event)
	{	
		for(int i=0; i<5; i++)
		{
			String firstName = fakeEmp.name().firstName();
			String lastName = fakeEmp.name().lastName();
			String email = firstName+"."+lastName+"@fakeEmp.com";
			
			Employee employee = Employee
					.builder()
					.firstName(firstName)
					.lastName(lastName)
					.email(email)
					.build();
			
			empRepo.save(employee);
		}

	}
}
