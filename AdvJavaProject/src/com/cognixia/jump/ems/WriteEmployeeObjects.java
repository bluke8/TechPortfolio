package com.cognixia.jump.ems;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class WriteEmployeeObjects {
	
	public static void main(String[]args) {
		
		File file = new File("resources/employees.txt");
		
		try {
			file.createNewFile();
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		//helper methods
		writeEmployeesToFile(file);
	}
	
	public static void writeEmployeesToFile(File file) {
		
		try(ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(file))){
			Employee e1 = new Employee ("Brandon", "IT Department", 100000);
			Employee e2 = new Employee ("John", "Heath Department", 90000);
			Employee e3 = new Employee ("Doe", "HR Department", 75000);
			Employee e4 = new Employee ("Jane", "Medical Department", 100000);
			Employee e5 = new Employee ("Roe", "IT Department", 150000);
			
			writer.writeObject(e1);
			writer.writeObject(e2);
			writer.writeObject(e3);
			writer.writeObject(e4);
			writer.writeObject(e5);
			
			System.out.println("initial Employees written to file");
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
}
