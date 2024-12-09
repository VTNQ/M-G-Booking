package com.mgbooking.server.Repositories;

import com.mgbooking.server.DTOS.Hotel.HotelListDto;
import com.mgbooking.server.DTOS.Hotel.HotelUpdateDTO;
import com.mgbooking.server.Entities.Hotel;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HotelRepository extends JpaRepository<Hotel, Integer> {
    @Query("select new com.mgbooking.server.DTOS.Hotel.HotelListDto(a.id,a.name,a.district.name,b.imageUrl) from Hotel a " +
            " join Picture b on a.id=b.hotelId "
            +"where a.ownerId = :id and b.isMain=true")
    List<HotelListDto>FindHotelAll(@Param("id") int id);
    @Query("select new com.mgbooking.server.DTOS.Hotel.HotelUpdateDTO(a.id,a.name,a.address,a.cityId,a.decription,a.ownerId,a.district.id,b.imageUrl) from Hotel a join Picture b on a.id=b.hotelId where a.id = :id and b.isMain=true")
    HotelUpdateDTO findHotelById(@Param("id") int id);
}
