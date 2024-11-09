package com.mgbooking.server.Repositories;

import com.mgbooking.server.Entities.Account;
import com.mgbooking.server.Entities.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Account, Integer> {
}
