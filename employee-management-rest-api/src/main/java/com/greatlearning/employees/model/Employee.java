package com.greatlearning.employees.model;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="employee")
public class Employee
{
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private long id;
	
	private String firstName;
	private String lastName;
	private String email;
}
