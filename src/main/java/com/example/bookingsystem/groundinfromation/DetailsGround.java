package com.example.bookingsystem.groundinfromation;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class DetailsGround implements Serializable{

	@Id
	private String groundName;
	private Integer capacity;
	private double priceperhour;
	@JsonFormat(pattern = "HH", shape = JsonFormat.Shape.STRING)
	private LocalTime startHour;
	@JsonFormat(pattern = "HH", shape = JsonFormat.Shape.STRING)
	private LocalTime endingHour;
	
	private Duration input;
	//Duration.between(startHour, endingHour);
}
