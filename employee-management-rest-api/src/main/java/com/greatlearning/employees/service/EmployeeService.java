package com.greatlearning.employees.service;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.greatlearning.employees.model.Employee;
import com.greatlearning.employees.repository.EmployeeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeService
{
	private final EmployeeRepository empRepo;

	//get all the employees
	public List<Employee> fetchAllEmployees()
	{
		return empRepo.findAll();
	}

	//get all the employees in a sorted manner.
	public List<Employee> fetchAllEmployeesSortedByFirstName(Direction direction)
	{
		return empRepo.findAll(Sort.by(direction, "firstName"));
	}

	//get an employee based on the employee id
	public Employee findEmployeeById(Long id)
	{
		return empRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid employee id: " + id + " !!"));
	}

	public List<Employee> findEmployeesByFirstName(String firstName)
	{
		Employee employeesWithTheseNames = new Employee();
		employeesWithTheseNames.setFirstName(firstName);
		ExampleMatcher exampleMatcher = ExampleMatcher.matching()
				.withMatcher("firstName", ExampleMatcher.GenericPropertyMatchers.exact())
				.withIgnorePaths("id","lastName","email");

		Example<Employee> example = Example.of(employeesWithTheseNames, exampleMatcher);

		return empRepo.findAll(example);	
	}

	//add a new employee
	public Employee addEmployee(Employee emp)
	{
		return empRepo.save(emp);
	}

	//update an existing employee. If the given employee is not found then update user about invalid employee id
	public Employee updateEmployee(Long id, Employee updatedEmployee)
	{
		Employee existingEmployee = findEmployeeById(id);
		existingEmployee.setEmail(updatedEmployee.getEmail());
		existingEmployee.setFirstName(updatedEmployee.getFirstName());
		existingEmployee.setLastName(updatedEmployee.getLastName());
		
		return empRepo.save(existingEmployee);
	}

	//delete an employee based on the employee id
	public void deleteEmployeeById(Long id)
	{
		empRepo.deleteById(id);
	}
}
