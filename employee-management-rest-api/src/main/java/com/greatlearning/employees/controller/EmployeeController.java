package com.greatlearning.employees.controller;

import java.util.List;

import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
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
	
	//Get End points
	@GetMapping
	public List<Employee> getAllEmployees()
	{
		return empService.fetchAllEmployees();
	}
	
	@GetMapping("/sort/{order}")
	public List<Employee> getEmployeesCustomSortedByFirstName(@RequestParam(name = "order", defaultValue = "ASC", required = false) Direction sortOrder)
	{
		return empService.fetchAllEmployeesSortedByFirstName(sortOrder);
	}
	
	@GetMapping("/{id}")
	public Employee getEmployeeById(@PathVariable("id") long employeeId)
	{
		return empService.findEmployeeById(employeeId);
	}
	
	@GetMapping("/search/{firstName}")
	public List<Employee> getEmployeesByFirstName(@PathVariable String firstName)
	{
		return empService.findEmployeesByFirstName(firstName);
	}
	
	//Create End point
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Employee addEmployee(@RequestBody Employee newEmployee)
	{
		return empService.addEmployee(newEmployee);
	}	
	
	//Update End point
	@PutMapping("/{id}")
	public Employee updateEmployee(@PathVariable("id") long employeeId, @RequestBody Employee employee)
	{
		return empService.updateEmployeeById(employeeId, employee);
	}
	
	//Delete End point
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteEmployeeById(@PathVariable("id") long employeeId)
	{
		empService.deleteEmployeeById(employeeId);
	}
}
