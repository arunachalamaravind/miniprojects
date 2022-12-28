package com.example.bookingsystem.respositary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bookingsystem.groundinfromation.GroundDetails;

@Repository
public interface GroundDetailsRepositary extends JpaRepository<GroundDetails, String>{

}
