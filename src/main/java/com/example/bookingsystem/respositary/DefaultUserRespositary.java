package com.example.bookingsystem.respositary;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.example.bookingsystem.entity.DefaultUser;
@Repository

public interface DefaultUserRespositary extends JpaRepository<DefaultUser,Integer>{

//	@Query(nativeQuery = true)
	public abstract DefaultUser findByEmail(String email);
	public abstract Optional<DefaultUser> findByUserName(String userName);
	
}
