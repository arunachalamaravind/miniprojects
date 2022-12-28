package com.example.bookingsystem.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class AvailabiltyCheckingDate implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer sNo;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate bookedDate;
	
	@JsonFormat(pattern = "HH", shape = JsonFormat.Shape.STRING)
	private LocalTime startHour;
	
	@JsonFormat(pattern = "HH", shape = JsonFormat.Shape.STRING)
	private LocalTime endingHour;
	
}
