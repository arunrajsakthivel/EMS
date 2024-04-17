package com.spring.ems.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.ems.model.Employee;
import com.spring.ems.service.EmployeeService;

import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;

@Controller
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
		
	  @GetMapping("/")
	  public String index() {
		  return "index";		 
	  }
	  
	  @GetMapping("insert-employee-form")
	  public String insert() {
		  return "insert-employee-form";
	  } 
	  
	  @GetMapping("select-employee-form")
	  public String select() {
		  return "select-employee-form";
	  }
	  
	  @GetMapping("update-employee-form")
	  public String update() {
		  return "update-employee-form";
	  }
	  
	  @GetMapping("delete-employee-form")
	  public String delete() {
		  return "delete-employee-form";
	  }
	  
	  @GetMapping("select-all")
	  public String selectAll(Model model) {
	      List<Employee> employees = employeeService.getEmployees();
	      model.addAttribute("employees", employees);
	      return "controller/select-all";
	  }
	  
	  
	  
	  @PostMapping("save-employee")
	  public String saveEmployee(@ModelAttribute("employee") Employee employee, Model model) {
		  String result = employeeService.insertEmployee(employee);
	        switch (result) {
	            case "exists":
	                model.addAttribute("exists", true);
	                break;
	            case "saved":
	                model.addAttribute("saved", true);
	                break;
	            case "failed":
	                model.addAttribute("failed", true);
	                break;
	        }
		  return "controller/save-employee";
	  }
	  
	  @GetMapping("select-employee")
	  public String selectEmployee(@RequestParam int empid,Model model) {
	  	  Employee employee=employeeService.selectEmployeeByEmpID(empid);
	  	  model.addAttribute("employee",employee);
		  return "controller/select-employee";
	  }
	  	   
	  @PostMapping("delete-employee")
	  public String deleteEmployee(@RequestParam int empid,Model model) {
		  boolean isDeleted=employeeService.deleteEmployeeByEmpID(empid);
		  model.addAttribute("isDeleted", isDeleted);
		  return "controller/delete-employee";
	  }
	  
	  @PostMapping("update-employee")
	  public String updateEmployee(@RequestParam MultiValueMap<String, String> formData, Model model) {
		  
		  String empId = formData.getFirst("empid");
          String empName = formData.getFirst("empname");
          String empEmail = formData.getFirst("empemail");
          String empSal = formData.getFirst("empsal");
          
          Employee employee = new Employee();
	      employee.setEmpId(Integer.parseInt(empId));
	      employee.setEmpName(empName);
	      employee.setEmpEmail(empEmail);
	      employee.setEmpSal(Float.parseFloat(empSal));
	      
		  boolean isUpdated=employeeService.updateEmployeeByEmpID(employee);
		  
		  model.addAttribute("employee", employee);
		  model.addAttribute("isUpdated", isUpdated);
		  
		  return "controller/update-employee";
	  }
	  	  	 	  
}
