package com.example.bookingsystem.controller;

import java.util.Collection;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RestController;

import com.example.bookingsystem.dto.AdminfetchUserDto;
import com.example.bookingsystem.dto.DetailsGroundDto;
import com.example.bookingsystem.dto.GroundDeleteDto;
import com.example.bookingsystem.dto.SetStatusByAdminDto;
import com.example.bookingsystem.dto.ShowStatusRequestDto;
import com.example.bookingsystem.dto.UserDeleteDto;
import com.example.bookingsystem.entity.BookStatus;
import com.example.bookingsystem.entity.DefaultUser;
import com.example.bookingsystem.exception.InvalidException;
import com.example.bookingsystem.groundinfromation.DetailsGround;
import com.example.bookingsystem.groundinfromation.GroundDetails;
import com.example.bookingsystem.response.Response;
import com.example.bookingsystem.service.AdminService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/booking/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;

	@Autowired
	private Response response;

	@GetMapping("/showstatusrequest")
	public ResponseEntity<Response> statusRequest() {
		Collection<ShowStatusRequestDto> list = adminService.showStatusRequest();
		if (list != null) {
			response.setData(list);
			response.setStatus("false");
			response.setError(200);
			response.setMessage("user register request");
			return new ResponseEntity<Response>(response, HttpStatus.ACCEPTED);
		} else {

			throw new InvalidException("Something went wrong");
		}
	}

	@PostMapping("/status")
	public ResponseEntity<Response> statusUpdate(@RequestBody SetStatusByAdminDto adminDto) {
		BookStatus bookStatus = adminService.setStatusbyAdmin(adminDto);
		if (bookStatus != null) {
			response.setStatus("false");
			response.setError(200);
			response.setMessage("Admin setting the status");
			return new ResponseEntity<Response>(response, HttpStatus.ACCEPTED);
		} else {

			throw new InvalidException("Something went wrong");
		}
	}

	@GetMapping("/allUserDetails")
	public ResponseEntity<Response> getAlldetails(){
		List<AdminfetchUserDto> list= adminService.getUserDetails();
		if (list != null) {
			response.setData(list);
			response.setStatus("false");
			response.setError(200);
			response.setMessage("All user information");
			return new ResponseEntity<Response>(response, HttpStatus.ACCEPTED);
		} else {

			throw new InvalidException("Something went wrong");
		}
	}
	
	@GetMapping("/DetailsbyUserId{userId}")
	public ResponseEntity<Response> getdetailsbyUserID(@PathVariable Integer userId){
		AdminfetchUserDto dto= adminService.getUserID(userId);
		if (dto != null) {
			response.setData(dto);
			response.setStatus("false");
			response.setError(200);
			response.setMessage("Information of User ID");
			return new ResponseEntity<Response>(response, HttpStatus.ACCEPTED);
		} else {
			
			throw new InvalidException("Something went wrong");
		}
	}
	
	@PutMapping("/CreateGround")
	public ResponseEntity<Response> createGround(@RequestBody DetailsGroundDto detailsGround){
	DetailsGround details	= adminService.createGround(detailsGround);
	if (details != null) {
		response.setStatus("false");
		response.setError(200);
		response.setMessage("Data Added Successfully");
		return new ResponseEntity<Response>(response, HttpStatus.ACCEPTED);
	} else {

		throw new InvalidException("Something went wrong");
	}
	}
	@PutMapping("/updateGround")
	public ResponseEntity<Response> updateGround(@RequestBody DetailsGroundDto detailsGround){
		DetailsGround details	= adminService.updateGround(detailsGround);
		if (details != null) {
			response.setStatus("false");
			response.setError(200);
			response.setMessage("Update Successfully");
			return new ResponseEntity<Response>(response, HttpStatus.ACCEPTED);
		} else {
			
			throw new InvalidException("Something went wrong");
		}
	}
	
	@DeleteMapping("/deleteGround")
	public ResponseEntity<Response> deleteByAdmin(@RequestBody GroundDeleteDto deleteDto) {
		boolean defaultUser = adminService.deleteGround(deleteDto);
		if (defaultUser) {
			response.setStatus("false");
			response.setError(200);
			response.setMessage("delete successfully");
			return new ResponseEntity<Response>(response, HttpStatus.ACCEPTED);
		} else {

			throw new InvalidException("Something went wrong");
		}
	}
}
