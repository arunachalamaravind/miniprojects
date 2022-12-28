package com.example.bookingsystem.groundinfromation;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.bookingsystem.respositary.GroundRepositary;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component

public class GroundDetails {
	
	@Id
	private String groundName;
	private Integer capacity;
	private double priceperhour;
	
	
	


}
