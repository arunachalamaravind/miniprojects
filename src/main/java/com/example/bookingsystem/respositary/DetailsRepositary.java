package com.example.bookingsystem.respositary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bookingsystem.groundinfromation.DetailsGround;
@Repository
public interface DetailsRepositary extends JpaRepository<DetailsGround,String>{

}
