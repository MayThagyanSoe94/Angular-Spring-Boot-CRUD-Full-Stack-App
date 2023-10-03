package com.may.crud.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.may.crud.springboot.model.Employee;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}
