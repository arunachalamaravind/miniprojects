package com.example.bookingsystem.respositary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bookingsystem.entity.Booking;
@Repository
public interface BookingRespositary extends JpaRepository<Booking,Integer>{

}
