package com.example.bookingsystem.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.example.bookingsystem.dto.AdminfetchUserDto;
import com.example.bookingsystem.dto.DetailsGroundDto;
import com.example.bookingsystem.dto.GroundDeleteDto;
import com.example.bookingsystem.dto.SetStatusByAdminDto;
import com.example.bookingsystem.dto.ShowStatusRequestDto;
import com.example.bookingsystem.entity.BookStatus;
import com.example.bookingsystem.entity.DefaultUser;
import com.example.bookingsystem.groundinfromation.DetailsGround;
import com.example.bookingsystem.groundinfromation.GroundDetails;

public interface AdminService {

	Collection<ShowStatusRequestDto> showStatusRequest();

	BookStatus setStatusbyAdmin(SetStatusByAdminDto adminDto);

	List<AdminfetchUserDto> getUserDetails();

	AdminfetchUserDto getUserID(Integer userId);

	DetailsGround createGround(DetailsGroundDto detailsGround);

	DetailsGround updateGround(DetailsGroundDto detailsGround);

	boolean deleteGround(GroundDeleteDto deleteDto);
	
	

}
