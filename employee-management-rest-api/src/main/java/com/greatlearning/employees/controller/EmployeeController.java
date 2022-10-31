package com.greatlearning.employees.controller;

import java.util.List;

import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.greatlearning.employees.model.Employee;
import com.greatlearning.employees.service.EmployeeService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/employees")
public class EmployeeController
{
	private final EmployeeService empService;

	//Update end point pending, get by first-name pending 
	
	//Get End points
	@GetMapping
	public List<Employee> getAllEmployees()
	{
		return empService.fetchAllEmployees();
	}
	
	@GetMapping("/sortedByFirstName")
	public List<Employee> getAllEmployeesInSortedManner(Direction dir)
	{
		return empService.fetchAllEmployeesSortedByFirstName(dir);
	}
	
	@GetMapping("/{id}")
	public Employee getEmployeeById(@PathVariable long id)
	{
		return empService.findEmployeeById(id);
	}
	
	@GetMapping("/firstName")
	public List<Employee> getEmployeesByFirstName(@RequestParam(name="firstName") String firstName)
	{
		return empService.findEmployeesByFirstName(firstName);
	}
	
	//Create End point
	@PostMapping
	public Employee addEmployee(@RequestBody Employee employee)
	{
		return empService.addEmployee(employee);
	}	
	
	//Update End point
	@PutMapping("/{id}")
	public Employee updateEmployee(@PathVariable long id, @RequestBody Employee emp)
	{
		return empService.updateEmployee(id, emp);
	}
	
	//Delete End point
	@DeleteMapping("/{id}")
	public void deleteEmployeeById(@PathVariable long id)
	{
		empService.deleteEmployeeById(id);
	}
}
