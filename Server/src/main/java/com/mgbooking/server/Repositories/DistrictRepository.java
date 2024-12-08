package com.mgbooking.server.Repositories;

import com.mgbooking.server.Entities.District;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistrictRepository extends JpaRepository<District, Integer> {
    @Query("select c from District c where c.city.id = :idCity")
    List<District> findDistrictByCity(@Param("idDistrict") int idCity);
}
