package com.hred.weblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hred.weblog.model.Sender;

@Repository
public interface SenderRepository extends JpaRepository<Sender, Long>{
}


