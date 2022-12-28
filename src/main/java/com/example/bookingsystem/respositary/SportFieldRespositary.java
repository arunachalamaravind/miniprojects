package com.example.bookingsystem.respositary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bookingsystem.entity.SportField;
@Repository
public interface SportFieldRespositary extends JpaRepository<SportField, Integer>{

}
