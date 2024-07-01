package com.hred.weblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hred.weblog.model.Responsible;

@Repository
public interface ResponsibleRepository extends JpaRepository<Responsible, Long>{
}


