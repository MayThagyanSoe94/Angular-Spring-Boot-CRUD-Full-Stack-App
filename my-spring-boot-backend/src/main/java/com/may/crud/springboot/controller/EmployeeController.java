package com.may.crud.springboot.controller;

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

import com.may.crud.springboot.dto.EmployeeDTO;
import com.may.crud.springboot.exception.ResourceNotFoundException;
import com.may.crud.springboot.model.Employee;
import com.may.crud.springboot.repository.EmployeeRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepository;

	// get all employees
	@GetMapping
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	@PostMapping
	public Employee createEmployee(@RequestBody EmployeeDTO dto) {
		Employee employee = new Employee(dto);
		return employeeRepository.save(employee);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not exit with id : " + id));
		return ResponseEntity.ok(employee);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Employee> updteEmployee(@PathVariable Long id, @RequestBody EmployeeDTO dto) {
		Employee oldEmployee = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not exit with id : " + id));
		Employee employee = new Employee(dto);
		employee = updateData(oldEmployee, employee);
		employee = employeeRepository.save(employee);
		return ResponseEntity.ok(employee);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id) {
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not exit with id : " + id));
		employeeRepository.delete(employee);
		Map<String, Boolean> response = new HashMap<>();
		response.put("Successfully deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}

	private Employee updateData(Employee oldData, Employee newData) {
		oldData.setFirstName(newData.getFirstName());
		oldData.setLastName(newData.getLastName());
		oldData.setEmail(newData.getEmail());
		oldData.setPhoneNo(newData.getPhoneNo());
		oldData.setAddress(newData.getAddress());
		return oldData;
	}

}