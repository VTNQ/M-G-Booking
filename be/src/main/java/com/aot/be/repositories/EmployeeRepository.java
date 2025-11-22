package com.aot.be.repositories;

import com.aot.be.model.entities.Employee;
import org.springframework.data.repository.Repository;

public interface EmployeeRepository extends Repository<Employee, Integer> {
}