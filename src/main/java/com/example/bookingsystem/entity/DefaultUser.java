package com.example.bookingsystem.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DefaultUser implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;
	private String email;
	private String password;
	private Integer groupId;
	private String ipAddress;
	private Integer active;
	private String activationCode;
	private LocalDateTime createdOn;
	private LocalDateTime lastLogin;
	private String userName;
	private long phoneNumber;
	private String gender;
	private String forgottenPasswordCode;
	private String rememberCode;
	@ElementCollection
	@CollectionTable(
			name="rolestab",
			joinColumns = @JoinColumn(name="id")
			)
	private Set <String> roles;
	
	
	@ManyToMany(cascade = CascadeType.ALL,mappedBy = "defaultUser")
	@JsonIgnore
	private List<Booking> booking=Lists.newArrayList();
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "defaultUser")
	@JsonIgnore
	private List<SportField> fields;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "defaultUser")
	@JsonIgnore
	private List<BookStatus> bookStatus; 
	
	

}
