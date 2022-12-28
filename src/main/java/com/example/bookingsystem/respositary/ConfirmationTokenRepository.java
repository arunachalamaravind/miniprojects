package com.example.bookingsystem.respositary;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.bookingsystem.entity.ConfirmationToken;

@Repository

public interface ConfirmationTokenRepository extends CrudRepository<ConfirmationToken, String> {
	//@Query(nativeQuery = true)
  public abstract  ConfirmationToken findByConfirmationToken(String confirmationToken);
}

