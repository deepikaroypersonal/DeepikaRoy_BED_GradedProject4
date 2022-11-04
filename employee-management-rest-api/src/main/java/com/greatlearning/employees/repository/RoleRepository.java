package com.greatlearning.employees.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.greatlearning.employees.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>
{
}
