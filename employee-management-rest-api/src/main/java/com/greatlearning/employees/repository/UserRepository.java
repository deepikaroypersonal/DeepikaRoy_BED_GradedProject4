package com.greatlearning.employees.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.greatlearning.employees.model.Role;
import com.greatlearning.employees.model.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Long>
{
	Optional<Users> findByUsername(String username);
}
