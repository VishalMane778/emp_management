package com.ems.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ems.exception.ResourceNotFoundException;
import com.ems.model.Employee;
import com.ems.repository.EmployeeRepository;
import com.ems.service.EmployeeService;
@Service
public class EmployeeServiceImpl implements EmployeeService{
	private EmployeeRepository employeeRepository;

	@Override
	public Employee saveEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return employeeRepository.save(employee);
	}

	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		super();
		this.employeeRepository = employeeRepository;
	}

	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}
    @Override
	public Employee getEmployeeById(long id) {
		/*
		 * Optional<Employee> employee=employeeRepository.findById(id);
		 * if(employee.isPresent()) { return employee.get(); }else { throw new
		 * ResourceNotFoundException("Employee", "Id", "id"); }
		 */
    	
    	return employeeRepository.findById(id).orElseThrow(
    			()->new ResourceNotFoundException("Employee", "Id", "id"));
	}
   
	@Override
	public Employee updateEmployee(Employee employee, long id) {
		Employee existingEmployee=employeeRepository.findById(id).orElseThrow(
				()->new ResourceNotFoundException("Employee", "Id", "id"));
		existingEmployee.setFirstName(employee.getFirstName());
		existingEmployee.setLastName(employee.getLastName());
		existingEmployee.setEmail(employee.getEmail());
		employeeRepository.save(existingEmployee);
		return existingEmployee;
	}

	@Override
	public void deleteEmployee(long id) {
		//check whether employee exist in a DB or not
		employeeRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee", "Id", "id"));
		employeeRepository.deleteById(id);
		
	}
	
    
}
