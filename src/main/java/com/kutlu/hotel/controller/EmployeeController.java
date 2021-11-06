package com.kutlu.hotel.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kutlu.hotel.exception.ResourceNotFoundException;
import com.kutlu.hotel.model.Employee;
import com.kutlu.hotel.repository.EmployeeRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepository;

	// get all employees
	@GetMapping("employees")
	public List<Employee> getAllEmployees() {
		return this.employeeRepository.findAll();
	}

	// create employee rest api
	@PostMapping("employees")
	public Employee createEmployee(@RequestBody Employee employee) {
		return employeeRepository.save(employee);
	}

	// get employee by id rest api

	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
		Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id : " + id));
		return ResponseEntity.ok(employee);
	}
	
	//update employee rest api
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employee){
		Employee employeeDetails = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id : " + id));
		
		employeeDetails.setName(employee.getName());
		employeeDetails.setMail(employee.getMail());
		
		Employee updatedEmployee = employeeRepository.save(employeeDetails);
		return ResponseEntity.ok(updatedEmployee);
	}
	
	//delete employee rest api //Bu methodu şimdilik yazdım ama ben dbden silmeyeceğim status alanı ekleyip false yapacağım.
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id){
		Employee employeeDetails = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id : " + id));
		employeeRepository.delete(employeeDetails);
		//employeeRepository.deleteById(id);
		Map<String, Boolean> response = new HashMap<>();
		response.put("result", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}

}