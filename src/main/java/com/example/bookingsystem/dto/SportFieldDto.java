package com.example.bookingsystem.dto;

import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Component
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SportFieldDto {

	private String name;
	private String description;
	@JsonFormat(pattern = "HH",shape=JsonFormat.Shape.STRING)
	private LocalTime startHour;
//	@JsonFormat(pattern = "HH:mm")
	@JsonFormat(pattern = "HH",shape=JsonFormat.Shape.STRING)
	private LocalTime endingHour;
	private LocalDateTime requestOn;
	private Integer noOfPlayer;
	
	
}
