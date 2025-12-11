package com.aot.be.repositories;

import com.aot.be.model.entities.Airline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.Repository;

public interface AirlineRepository extends JpaRepository<Airline, Integer>,
        JpaSpecificationExecutor<Airline> {
}