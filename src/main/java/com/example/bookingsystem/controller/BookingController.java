package com.example.bookingsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.bookingsystem.dto.BookingDto;
import com.example.bookingsystem.dto.DefaultUserDto;
import com.example.bookingsystem.dto.SportFieldDeleteDto;
import com.example.bookingsystem.dto.SportFieldDto;
import com.example.bookingsystem.dto.SportFieldUpdateDto;
import com.example.bookingsystem.dto.UserDeleteDto;
import com.example.bookingsystem.dto.UserUpdateDto;
import com.example.bookingsystem.entity.Booking;
import com.example.bookingsystem.entity.DefaultUser;

import com.example.bookingsystem.exception.InvalidException;
import com.example.bookingsystem.groundinfromation.DetailsGround;
import com.example.bookingsystem.groundinfromation.GroundDetails;
import com.example.bookingsystem.groundinfromation.GroundInformationForUser;
import com.example.bookingsystem.response.Response;
import com.example.bookingsystem.respositary.BookingRespositary;
import com.example.bookingsystem.respositary.DefaultUserRespositary;
import com.example.bookingsystem.service.GroundService;
import com.example.bookingsystem.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/booking/user")
public class BookingController {

	@Autowired
	private GroundService groundService;

	@Autowired
	private UserService service;

	@Autowired
	private Response response;

	/* For register user */

	@PostMapping("/register")
	public ResponseEntity<Response> getRegister(@RequestBody DefaultUserDto defaultUserDto) {

		DefaultUser defaultUser = service.register(defaultUserDto);

		if (defaultUser != null) {
			response.setMessage("Register Sucessfully");
			response.setStatus("false");
			response.setError(200);
			return new ResponseEntity<Response>(response, HttpStatus.ACCEPTED);
		} else {

			throw new InvalidException("Something went wrong");
		}
	}
	
	@RequestMapping(value="/confirm-account", method= {RequestMethod.GET, RequestMethod.POST})
	public ResponseEntity<Response> getAuthicationRegister(@RequestParam("token")String confirmationToken) {
		
		boolean output= service.getAuthicationToken(confirmationToken);
		if (output) {
			response.setMessage("accountVerified");
			response.setStatus("false");
			response.setError(200);
			return new ResponseEntity<Response>(response, HttpStatus.ACCEPTED);
		} else {

			throw new InvalidException("Something went wrong");
		}
	}
	
	
/*	@PostMapping("/register")
	public ResponseEntity<Response> getRegister(@RequestBody DefaultUserDto defaultUserDto) {

		DefaultUser defaultUser = service.register(defaultUserDto);

		if (defaultUser != null) {
			response.setMessage("Register Sucessfully");
			response.setStatus("false");
			response.setError(200);
			return new ResponseEntity<Response>(response, HttpStatus.ACCEPTED);
		} else {

			throw new InvalidException("Something went wrong");
		}
	} */
	
	

	/* For Register Sport Field */

	@RequestMapping(value = "/sport/{userId}", method = RequestMethod.POST)
	public ResponseEntity<Response> getSportFieldRegister(@PathVariable Integer userId,
			@RequestBody BookingDto booking) {
		Booking sportBooking = service.sportFieldRegister(userId, booking);
		if (sportBooking != null) {
			response.setMessage("Register Sucessfully");
			response.setStatus("false");
			response.setError(200);
			return new ResponseEntity<Response>(response, HttpStatus.ACCEPTED);
		} else {

			throw new InvalidException("Something went wrong");
		}
	}

	/* For Fetching Ground Details */

	@GetMapping("/getGroundDetails")
	public ResponseEntity<Response> getAllGround() {
		List<DetailsGround> groundDetails = groundService.getGroundDetails();
		if (groundDetails != null) {
			response.setData(groundDetails);
			response.setStatus("false");
			response.setError(200);
			response.setMessage("Ground details");
			return new ResponseEntity<Response>(response, HttpStatus.ACCEPTED);
		} else {

			throw new InvalidException("Something went wrong");
		}
	}

	/* For Fetching Particular Ground Details */

	@RequestMapping(value = "/ground/{groundName}", method = RequestMethod.GET)
	public ResponseEntity<Response> getByGroundName(@PathVariable String groundName) {
		DetailsGround details = groundService.getByName(groundName);
		if (details != null) {
			response.setData(details);
			response.setStatus("false");
			response.setError(200);
			response.setMessage("Ground detail");
			return new ResponseEntity<Response>(response, HttpStatus.ACCEPTED);
		} else {

			throw new InvalidException("Something went wrong");
		}
	}

	/* For Update operation User Information */

	@PutMapping("/updateUser")
	public ResponseEntity<Response> updateByUser(@RequestBody UserUpdateDto dto) {

		DefaultUser defaultUser = service.updateUser(dto);
		if (defaultUser != null) {
			response.setData(defaultUser);
			response.setStatus("false");
			response.setError(200);
			response.setMessage("update sucessfully");
			return new ResponseEntity<Response>(response, HttpStatus.ACCEPTED);
		} else {

			throw new InvalidException("Something went wrong");
		}
	}

	/* For Delete operation User */

	@DeleteMapping("/deleteUser")
	public ResponseEntity<Response> deleteByUser(@RequestBody UserDeleteDto deleteDto) {
		boolean defaultUser = service.deleteUser(deleteDto);
		if (defaultUser) {
			response.setStatus("false");
			response.setError(200);
			response.setMessage("delete successfully");
			return new ResponseEntity<Response>(response, HttpStatus.ACCEPTED);
		} else {

			throw new InvalidException("Something went wrong");
		}
	}
	
	/* update operation for user sport field*/
	
	@PutMapping("/updateSportField")
	public ResponseEntity<Response> updateBySportField(@RequestBody SportFieldUpdateDto fieldUpdateDto ) {

		Booking booking = service.updateSportField(fieldUpdateDto);
		if ( booking != null) {
			response.setStatus("false");
			response.setError(200);
			response.setMessage("update sucessfully");
			return new ResponseEntity<Response>(response, HttpStatus.ACCEPTED);
		} else {

			throw new InvalidException("Something went wrong");
		}
	}
	
	/*delete operation for user sport field*/
	
	@DeleteMapping("/deletesportfield")
	public ResponseEntity<Response> deleteByUser(@RequestBody SportFieldDeleteDto deleteDto ) {
		boolean defaultUser = service.deleteUser(deleteDto);
		if (defaultUser) {
			response.setStatus("false");
			response.setError(200);
			response.setMessage("delete successfully");
			return new ResponseEntity<Response>(response, HttpStatus.ACCEPTED);
		} else {

			throw new InvalidException("Something went wrong");
		}
	}
	
	/* Forgot password operation for user */
	
}
