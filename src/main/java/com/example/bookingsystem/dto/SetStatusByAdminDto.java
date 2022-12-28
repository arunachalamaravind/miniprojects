package com.example.bookingsystem.dto;


import org.springframework.stereotype.Component;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SetStatusByAdminDto {

	private Integer bookstatusId;
	private String name;
}
