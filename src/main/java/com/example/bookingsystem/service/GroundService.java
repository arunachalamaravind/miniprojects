package com.example.bookingsystem.service;


import java.util.List;

import com.example.bookingsystem.groundinfromation.DetailsGround;
import com.example.bookingsystem.groundinfromation.GroundDetails;
import com.example.bookingsystem.groundinfromation.GroundInformationForUser;

public interface GroundService {

	List<DetailsGround> getGroundDetails();

	DetailsGround getByName(String groundName);
	
	//void registerGround();

}
