package com.hred.weblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hred.weblog.model.Pack;

@Repository
public interface PackRepository extends JpaRepository<Pack, Long>{
}


