package com.spring.ems.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.ems.dao.EmployeeDAO;
import com.spring.ems.model.Employee;

import jakarta.transaction.Transactional;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeDAO employeeDAO;
	
	public String insertEmployee(Employee employee) {
		try {
			if(employeeDAO.existsById(employee.getEmpId())) {
				return "exists";
			}else {
				employeeDAO.save(employee);
				return "saved";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "failed";
		}
	}
	
	public boolean deleteEmployeeByEmpID(int empid) {
		if (employeeDAO.existsById(empid)) {
			employeeDAO.deleteById(empid);
            return true;
        }
		return false;
    }
	
	@Transactional
	public boolean updateEmployeeByEmpID(Employee employee) {
	    try {
	        if (employeeDAO.existsById(employee.getEmpId())) {
                employeeDAO.save(employee);
                return true;
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return false;
	}
	
	public Employee selectEmployeeByEmpID(int empid) {
		try {
	        Optional<Employee> optionalEmployee = employeeDAO.findById(empid);
	        if (optionalEmployee.isPresent()) {
	            return optionalEmployee.get();
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return null;
	}


	public List<Employee> getEmployees() {
		return employeeDAO.findAll(); 
	}

}
