package com.aot.be.repositories;

import com.aot.be.model.entities.Payment;
import org.springframework.data.repository.Repository;

public interface PaymentRepository extends Repository<Payment, Integer> {
}