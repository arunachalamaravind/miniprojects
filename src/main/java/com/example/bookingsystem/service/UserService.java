package com.example.bookingsystem.service;

import com.example.bookingsystem.dto.BookingDto;
import com.example.bookingsystem.dto.DefaultUserDto;
import com.example.bookingsystem.dto.SportFieldDeleteDto;
import com.example.bookingsystem.dto.SportFieldUpdateDto;
import com.example.bookingsystem.dto.UserDeleteDto;
import com.example.bookingsystem.dto.UserUpdateDto;
import com.example.bookingsystem.entity.Booking;
import com.example.bookingsystem.entity.DefaultUser;

public interface UserService {

	DefaultUser register(DefaultUserDto defaultUserDto);

	Booking sportFieldRegister(Integer userId, BookingDto booking);

	DefaultUser updateUser(UserUpdateDto dto);

	boolean deleteUser(UserDeleteDto deleteDto);

	Booking updateSportField(SportFieldUpdateDto fieldUpdateDto);

	boolean deleteUser(SportFieldDeleteDto deleteDto);

	boolean getAuthicationToken(String confirmationToken);
	
//	String sendMail(DefaultUser user);
	

}
