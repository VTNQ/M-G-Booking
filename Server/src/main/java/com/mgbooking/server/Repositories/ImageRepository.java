package com.mgbooking.server.Repositories;

import com.mgbooking.server.Entities.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Picture,Integer> {
@Query("SELECT  a from Picture a where a.airlineId = :id")
    public Picture findByImageId(@Param("id") Integer id);
}
