package net.javaguides.springboot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "employees")
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "first_name")
	@NotBlank(message = "First name is required")
	@Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
	@Pattern(regexp = "^[a-zA-Z\\s]+$", message = "First name can only contain letters and spaces")
	private String firstName;

	@Column(name = "last_name")
	@NotBlank(message = "Last name is required")
	@Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
	@Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Last name can only contain letters and spaces")
	private String lastName;
	
	@Column(name = "email_id", unique = true)
	@NotBlank(message = "Email is required")
	@Email(message = "Please provide a valid email address")
	@Size(max = 100, message = "Email must not exceed 100 characters")
	private String emailId;
	
	@Column(name = "age")
	@NotNull(message = "Age is required")
	@Min(value = 16, message = "Age must be at least 16")
	@Max(value = 100, message = "Age must not exceed 100")
	private Integer age;

	@Column(name = "gender")
	@NotBlank(message = "Gender is required")
	@Pattern(regexp = "^(Male|Female|M|F|male|female|m|f)$", message = "Gender must be Male, Female, M, or F")
	private String gender;

	@Column(name = "project_id", unique = true)
	@Size(max = 20, message = "Project ID must not exceed 20 characters")
	private String projectId;
	
	public Employee() {
		
	}
	
	public Employee(String firstName, String lastName, String emailId, Integer age, String gender, String projectId) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		this.age = age;
		this.gender = gender;
		this.projectId = projectId;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
}
