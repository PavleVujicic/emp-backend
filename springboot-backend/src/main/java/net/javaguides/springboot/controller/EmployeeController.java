package net.javaguides.springboot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.Employee;
import net.javaguides.springboot.repository.EmployeeRepository;

// Explicitly allow Firebase frontend URL
@CrossOrigin(origins = {"https://employee-mgmt-system-pv.web.app", "http://localhost:4200"})
@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	// Environment variable for rate limiting (can be used with external services)
	@Value("${app.max-requests-per-minute:60}")
	private int maxRequestsPerMinute;
	
	// get all employees
	@GetMapping("/employees")
	public List<Employee> getAllEmployees(){
		return employeeRepository.findAll();
	}		
	
	// create employee rest api with validation
	@PostMapping("/employees")
	public Employee createEmployee(@Valid @RequestBody Employee employee) {
		// Input sanitization
		if (employee.getFirstName() != null) {
			employee.setFirstName(employee.getFirstName().trim());
		}
		if (employee.getLastName() != null) {
			employee.setLastName(employee.getLastName().trim());
		}
		if (employee.getEmailId() != null) {
			employee.setEmailId(employee.getEmailId().trim().toLowerCase());
		}
		if (employee.getGender() != null) {
			employee.setGender(employee.getGender().trim());
		}
		
		// Validate age range
		if (employee.getAge() != null && (employee.getAge() < 16 || employee.getAge() > 100)) {
			throw new IllegalArgumentException("Age must be between 16 and 100");
		}
		
		// Generate projectId: EMP{count+1}-{age}-{gender}
		long count = employeeRepository.count() + 1;
		String genderShort = (employee.getGender() != null && employee.getGender().toLowerCase().startsWith("m")) ? "M" : "F";
		String projectId = "EMP" + count + "-" + employee.getAge() + "-" + genderShort;
		employee.setProjectId(projectId);
		
		return employeeRepository.save(employee);
	}
	
	// get employee by id rest api
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
		// Validate ID is positive
		if (id <= 0) {
			throw new IllegalArgumentException("Employee ID must be positive");
		}
		
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + id));
		return ResponseEntity.ok(employee);
	}
	
	// update employee rest api with validation
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @Valid @RequestBody Employee employeeDetails){
		// Validate ID is positive
		if (id <= 0) {
			throw new IllegalArgumentException("Employee ID must be positive");
		}
		
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + id));
		
		// Input sanitization and validation
		if (employeeDetails.getFirstName() != null) {
			employee.setFirstName(employeeDetails.getFirstName().trim());
		}
		if (employeeDetails.getLastName() != null) {
			employee.setLastName(employeeDetails.getLastName().trim());
		}
		if (employeeDetails.getEmailId() != null) {
			employee.setEmailId(employeeDetails.getEmailId().trim().toLowerCase());
		}
		if (employeeDetails.getAge() != null) {
			// Validate age range
			if (employeeDetails.getAge() < 16 || employeeDetails.getAge() > 100) {
				throw new IllegalArgumentException("Age must be between 16 and 100");
			}
			employee.setAge(employeeDetails.getAge());
		}
		if (employeeDetails.getGender() != null) {
			employee.setGender(employeeDetails.getGender().trim());
			// Regenerate projectId if age or gender changed
			long count = employee.getId();
			String genderShort = employee.getGender().toLowerCase().startsWith("m") ? "M" : "F";
			String projectId = "EMP" + count + "-" + employee.getAge() + "-" + genderShort;
			employee.setProjectId(projectId);
		}
		
		Employee updatedEmployee = employeeRepository.save(employee);
		return ResponseEntity.ok(updatedEmployee);
	}
	
	// delete employee rest api
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id){
		// Validate ID is positive
		if (id <= 0) {
			throw new IllegalArgumentException("Employee ID must be positive");
		}
		
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + id));
		
		employeeRepository.delete(employee);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}
