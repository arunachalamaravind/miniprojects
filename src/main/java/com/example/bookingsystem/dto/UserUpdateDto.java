package com.example.bookingsystem.dto;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Component
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateDto {

	private Integer userId;
	private String email;
	private LocalDateTime createdOn;
	private String userName;
	private long phoneNumber;
	private String gender;
}
