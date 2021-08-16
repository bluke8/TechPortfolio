package com.cognixia.jump.ems;

import java.io.Serializable;

public class Employee implements Serializable{

	private static final long serialVersionUID = 4902234136861152341L;

	private static int idCount=1;
	
	private int id;
	
	private String name;
	private String dept;
	private int salary;
	
	public Employee(String name, String dept, int salary) {
		super();
		this.id = idCount++;
		this.name = name;
		this.dept = dept;
		this.salary = salary;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	
	
	@Override
	public String toString() {
		return "\t[id: " + id + ", name: " + name + ", dept: " + dept + ", salary: $" + salary + "]";
	}
	
	
}
