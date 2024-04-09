package com.spring.ems.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.ems.model.Employee;

public interface EmployeeDAO extends JpaRepository<Employee, Integer>{

}
