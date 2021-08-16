package com.cognixia.jump.ems;

import java.util.ArrayList;

public class Manager extends Employee {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1863505044584146969L;
	ArrayList<Employee> team;

	public Manager(String name, String dept, int salary) {
		super(name, dept, salary);
	}
	
	public void printTeam() {
		System.out.println("Printing " +this.getName()+ "'s team: "+this.getDept());
		for(Employee e : this.team) {
			System.out.println(e);
		}
	}
	
	public void fillTeam(ArrayList<Employee> _team){
		this.team = new ArrayList<Employee>();
		for (Employee e : _team) {
			this.team.add(e);
		}
	}
	

}
