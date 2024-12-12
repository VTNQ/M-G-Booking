package com.mgbooking.server.Repositories;

import com.mgbooking.server.Entities.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ServiceRepository extends JpaRepository<Service,Integer> {
    @Query("SELECT a from Service a where a.hotel.id = :id")
    List<Service>FindAll(@Param("id")int id);
}
