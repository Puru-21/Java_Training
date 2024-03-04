package com.training.project.bean;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;
import org.springframework.lang.NonNull;

public class EmployeeBean {
	private Integer id;
	@NotEmpty(message = "Name can't be blank")
	private String name;
	@NotEmpty(message = "Role can't be blank")
	private String  role;
	@NotNull(message = "Salary can't be blank")
	@Range(min = (long) 1000.0,max = (long) 10000000.0)
	private Double salary;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Double getSalary() {
		return salary;
	}
	public void setSalary(Double salary) {
		this.salary = salary;
	}
	public EmployeeBean(Integer id, String name, String role, Double salary) {
		super();
		this.id = id;
		this.name = name;
		this.role = role;
		this.salary = salary;
	}
	public EmployeeBean() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "EmployeeBean [id=" + id + ", name=" + name + ", role=" + role + ", salary=" + salary + "]";
	}

}
