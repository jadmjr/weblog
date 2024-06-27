package com.hred.weblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hred.weblog.model.Recipient;

@Repository
public interface RecipientRepository extends JpaRepository<Recipient, Long>{
}


