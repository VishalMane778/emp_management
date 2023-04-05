package com.ems.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ems.model.Employee;
import com.ems.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
	private EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		super();
		this.employeeService = employeeService;
	}
	//create employee REST API
	@PostMapping()
	public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee)
	{
	   return new ResponseEntity<Employee>(employeeService.saveEmployee(employee), HttpStatus.CREATED);	
	}

	//create get all employees REST API
	@GetMapping
	public List<Employee> getAllEmployees() {
		return employeeService.getAllEmployees();
	}
	//create get Employee By id REST API
	@GetMapping("{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") long employeeId)
	{
	 
		return new ResponseEntity<Employee>(employeeService.getEmployeeById(employeeId), HttpStatus.OK);
	}
	//create update Employee REST API
	@PutMapping("{id}")
	public ResponseEntity<Employee> updateEmpoyee(@PathVariable("id") long id,@RequestBody Employee employee)
	{
	   return new ResponseEntity<Employee>(employeeService.updateEmployee(employee, id),HttpStatus.OK);  	
	}
	//create delete Employee By id REST API
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable("id") long id)
	{
	   employeeService.deleteEmployee(id);
	   return new ResponseEntity<String>("Employee deleted successfully!.",HttpStatus.OK);
	}
}
