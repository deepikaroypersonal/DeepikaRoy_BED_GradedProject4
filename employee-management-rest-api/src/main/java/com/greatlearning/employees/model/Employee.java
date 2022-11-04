package com.greatlearning.employees.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="employee")
@Builder
public class Employee
{
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private long id;
	
	private String firstName;
	private String lastName;
	private String email;
}
