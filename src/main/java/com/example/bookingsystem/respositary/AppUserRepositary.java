package com.example.bookingsystem.respositary;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bookingsystem.entity.AppUser;
@Repository
public interface AppUserRepositary extends JpaRepository<AppUser, String>{

	Optional<AppUser> findByUserName(String username);
}
