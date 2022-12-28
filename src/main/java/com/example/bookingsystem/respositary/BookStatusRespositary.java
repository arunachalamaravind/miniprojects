package com.example.bookingsystem.respositary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bookingsystem.entity.BookStatus;

@Repository
public interface BookStatusRespositary extends JpaRepository<BookStatus,Integer>{

}
