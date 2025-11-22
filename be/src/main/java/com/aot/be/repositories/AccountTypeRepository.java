package com.aot.be.repositories;

import com.aot.be.model.entities.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountTypeRepository extends JpaRepository<AccountType, Integer> {
    Optional<AccountType> findByName(String name);
}