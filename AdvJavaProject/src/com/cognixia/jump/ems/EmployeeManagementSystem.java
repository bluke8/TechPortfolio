package com.cognixia.jump.ems;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class EmployeeManagementSystem {
	private static Scanner scan = new Scanner(System.in).useDelimiter("\n");
	static ArrayList <Employee> employeeList = new ArrayList<Employee>();
	/*
	 * -Employee management system should be able to : 
	 * -add new employees
	 * -update employee information
	 * -remove employees
	 * -list employee information
	 * 
	 * -keep track of departments and which
	 *  employees are apart of which department
	 * 
	 * -read employees from a text file
	 *  
	 *  
	 */
	
	
	
	
	public static void main(String[] args) {
		File employees = new File("resources/employees.txt");
		readEmployeesFromFile(employees);
		
		//creating manager object and assigning IT Department to their team list
		Manager m1 = new Manager("Sandra", "IT Department", 300000);
		ArrayList<Employee> itDeptTeam = new ArrayList<Employee>();
		employeeList.stream().filter(e -> e.getDept().equals("IT Department")).forEach(e -> itDeptTeam.add(e));
		
		m1.fillTeam(itDeptTeam);
		//m1.printTeam();
		
		run(employees);
		
	}
	
	//method that initiates EMS, 
		public static void run(File file) throws InputMismatchException{
			System.out.println("Welcome to the Employee Management System, please enter an option:");
			System.out.print("\n 1: view employees\n 2: add new employee\n 3: remove employee\n 4: update employee\n"
					+ " 5: find by department\n 6: save employees to file\n 7: quit \n\n input: ");
			
			int option = scan.nextInt();
			while(option != 7) {
			switch(option) {
					case 1: listEmployees(employeeList);
					break;
					case 2: Employee newEmp = createEmployee();
							employeeList.add(newEmp);
							System.out.println("\n\t ~~Employee Added~~ \n");
					break;
					case 3:	removeEmployee();
							System.out.println("\n\t ~~Employee Removed~~ \n");
					break;
					case 4: updateEmployee();
					break;
					case 5: findByDepartment(employeeList);
					break;
					case 6: writeEmployeesToFile(file);
					System.out.println("\n\t ~~Employee list has been saved~~ \n");
					break;
					default: System.out.println("\n\tERROR: Invalid Input\n\t *Try again*");
				}
			System.out.println("\n 1: view employees\n 2: add new employee\n 3: remove employee\n 4: update employee\n"
					+ " 5: find by departments\n 6: save employees to file\n 7: quit\n");
			System.out.print("Select another option: ");
			option = scan.nextInt();
			}
			scan.close();
			System.out.println("Quitting...\n\n\t **Thank you for using the Employee Management System!**");
		}
	
	
	
	public static Employee createEmployee() {
		
		System.out.print("Enter Name: ");
		String empName = scan.next();
		
		System.out.print("Enter Department: ");
		String empDept = scan.next();
		
		System.out.print("Enter Salary: ");
		int empSal = scan.nextInt();
		
		Employee newEmployee = new Employee(empName,empDept,empSal);
		//Need to +5 in order to start ID at 6
		newEmployee.setId(newEmployee.getId()+5);
		return newEmployee;
	}
	
	public static void listEmployees(ArrayList<Employee> employeeArry) {
		System.out.println("\n Employee list \n");
		for(Employee e : employeeArry) {
			System.out.println(e);
		}
	}
	
	public static void removeEmployee() {
		System.out.println("Which employee would you like to remove?");
		System.out.print("Enter employee ID: ");
		int empIdToBeRemoved = scan.nextInt();
		employeeList.remove(empIdToBeRemoved-1);
	}
	public static void updateEmployee() {
		System.out.print("Which employee would you like to update (Enter employee id): ");
		int empIdToBeUpdated = scan.nextInt();
		System.out.print("What would you like to update?"
				+ "\n 1: Name"
				+ "\n 2: Department"
				+ "\n 3: Salary"
				+ "\n Please enter information to be updated: ");
		int selection = scan.nextInt();
		
		switch(selection) {
		case 1: System.out.print("Enter new name: ");
				String name = scan.next();
				employeeList.get(empIdToBeUpdated-1).setName(name);
				System.out.println("\n\t ~~Employee name Updated~~ \n");
				break;
		case 2: System.out.print("Enter new department: ");
				String dept = scan.next();
				employeeList.get(empIdToBeUpdated-1).setDept(dept);
				System.out.println("\n\t ~~Employee department Updated~~ \n");
				break;
		case 3: System.out.print("Enter new salary: ");
				int sal = scan.nextInt();
				employeeList.get(empIdToBeUpdated-1).setSalary(sal);
				System.out.println("\n\t ~~Employe salary Updated~~ \n");
				break;
			default: System.out.println("Invalid case selected");;
		}
		
		
		
	}
	
	public static void findByDepartment(ArrayList<Employee> list) {
		/// list all possible departments
		String allDepts = employeeList.stream()
										.map(Employee::getDept)
										.distinct()
										.reduce((dept1,dept2) -> dept1 + ", "+dept2)
										.get();
		System.out.println("\n\tDepartments: "+allDepts);
		System.out.print("\t\nDepartment name you'd like to filter by: ");
		String dept = scan.next();
		
		System.out.println("\n\tListing employees in "+dept+"...\n");
		for (Employee e : list) {
			if(e.getDept().equals(dept)) {
				System.out.println("\t"+e);
			}
		}
		System.out.println("\n\n");
	}
	
	public static void writeEmployeesToFile(File file) {
		try(ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(file))){
			for(Employee e : employeeList) {
				writer.writeObject(e);
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	
	//helper method that reads employees from file
	public static void readEmployeesFromFile(File file) {
		try(ObjectInputStream reader = new ObjectInputStream(new FileInputStream(file))){
			
			while(true) {
				
				Employee e = (Employee) reader.readObject();
				employeeList.add(e);
			}
			
		}catch(EOFException e) {
		}catch(IOException e) {
			e.printStackTrace();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}	
	}
	
}
