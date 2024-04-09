package com.spring.ems.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.ems.model.Employee;
import com.spring.ems.service.EmployeeService;

import org.springframework.ui.Model;

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
	  public String selectAll() {
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
	  
	  @PostMapping("select-employee")
	  public String selectEmployee(@RequestParam int empid) {
	  	  return "controller/select-employee";
	  }
	  
	  @PostMapping("update-employee")
	  public String updateEmployee(@RequestParam int empid) {
	  	  return "controller/update-employee";
	  }
	  
	  @PostMapping("delete-employee")
	  public String deleteEmployee(@RequestParam int empid) {
		  @SuppressWarnings("unused")
		  boolean isDeleted=employeeService.deleteEmployeeByEmpID(empid);
		  return "controller/delete-employee";
	  }
	  	 	  
}
