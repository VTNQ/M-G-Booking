package com.mgbooking.server.Repositories;

import com.mgbooking.server.DTOS.Hotel.ImageListDto;
import com.mgbooking.server.DTOS.ResultFlightDTO;
import com.mgbooking.server.Entities.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Picture,Integer> {
@Query("SELECT  a from Picture a where a.airlineId = :id")
    public Picture findByImageId(@Param("id") Integer id);
@Query("select new com.mgbooking.server.DTOS.Hotel.ImageListDto(a.id,a.imageUrl)  FROM Picture  a where a.hotelId = :id")
    List<ImageListDto>findListImage(@Param("id") int id);

}
