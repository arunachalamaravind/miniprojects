package com.example.bookingsystem.respositary;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bookingsystem.entity.AvailabiltyCheckingDate;
@Repository
public interface AvailabityRepositary extends JpaRepository<AvailabiltyCheckingDate, Integer>{

	Optional<AvailabiltyCheckingDate> findByBookedDate(LocalDate bookDate);
}
