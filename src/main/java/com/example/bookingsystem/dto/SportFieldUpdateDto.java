package com.example.bookingsystem.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SportFieldUpdateDto {

	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate startDate;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate endDATE;
	
	private String sportName;
	
	private String description;
	
	@JsonFormat(pattern = "HH",shape=JsonFormat.Shape.STRING)
	private LocalTime startHour;

	@JsonFormat(pattern = "HH",shape=JsonFormat.Shape.STRING)
	private LocalTime endingHour;
	
	private LocalDateTime requestOn;
	
	private Integer sportfieldId;
}
