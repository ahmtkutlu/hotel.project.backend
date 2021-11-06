package com.kutlu.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kutlu.hotel.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}
