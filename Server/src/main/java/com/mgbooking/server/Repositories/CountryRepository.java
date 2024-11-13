package com.mgbooking.server.Repositories;

import com.mgbooking.server.Entities.Country;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {
Page<Country>findAll(Pageable pageable);
}
