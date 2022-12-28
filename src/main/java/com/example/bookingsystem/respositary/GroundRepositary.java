package com.example.bookingsystem.respositary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bookingsystem.groundinfromation.GroundDetails;
import com.example.bookingsystem.groundinfromation.GroundInformationForUser;
@Repository
public interface GroundRepositary extends JpaRepository<GroundInformationForUser,Integer> {

}
