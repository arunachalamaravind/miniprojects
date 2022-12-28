package com.example.bookingsystem.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.bookingsystem.entity.BookStatus;
import com.example.bookingsystem.entity.Booking;
import com.example.bookingsystem.entity.DefaultUser;
import com.example.bookingsystem.entity.SportField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShowStatusRequestDto {

	private String sportName;
	
	@JsonFormat(pattern = "HH", shape = JsonFormat.Shape.STRING)
	private LocalTime startHour;
	
	@JsonFormat(pattern = "HH", shape = JsonFormat.Shape.STRING)
	private LocalTime endingHour;
	
	private Integer bookingId;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate startDate;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate endDATE;
	
	private Integer bookstatusId;
	
	private Integer userId;
}
