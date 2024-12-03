package com.mgbooking.server.Repositories;

import com.mgbooking.server.Entities.AccountToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountTokenRepository extends JpaRepository<AccountToken, Integer> {
}
