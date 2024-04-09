package com.spring.ems.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.ems.dao.EmployeeDAO;
import com.spring.ems.model.Employee;

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

	public List<Employee> getEmployees() {
		return employeeDAO.findAll(); 
	}

}
