package com.mgbooking.server.Repositories;

import com.mgbooking.server.Entities.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Integer> {
}
