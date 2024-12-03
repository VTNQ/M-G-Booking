package com.mgbooking.server.Repositories;

import com.mgbooking.server.Entities.Country;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {
    @Query("select t from Country t where t.name like %:name%")
Page<Country>findAll(Pageable pageable,@Param("name")String name);

}
