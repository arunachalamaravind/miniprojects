package com.example.bookingsystem.respositary;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bookingsystem.entity.Roles;



@Repository
public interface RoleRepository extends JpaRepository<Roles, Integer> {
	Optional<Roles> findByRoleId(Integer id);
}
