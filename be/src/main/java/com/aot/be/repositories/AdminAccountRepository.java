package com.aot.be.repositories;

import com.aot.be.model.entities.AdminAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface AdminAccountRepository extends JpaRepository<AdminAccount, Integer> {
    Optional<AdminAccount> findByUsername(String username);

}