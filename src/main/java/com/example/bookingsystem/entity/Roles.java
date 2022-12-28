package com.example.bookingsystem.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.google.common.collect.Lists;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class Roles implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer roleId;
	
	@ManyToMany(mappedBy = "roles",cascade = CascadeType.ALL)
	private List<AppUser> appUser = Lists.newArrayList();
	
}
