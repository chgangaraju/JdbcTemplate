package com.imaginea.jdbctemplate;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"Beans.xml");
		StudentJDBCTemplate studentJDBCTemplate = (StudentJDBCTemplate) context
				.getBean("studentJDBCTemplate");
		studentJDBCTemplate.create("nirmal", 21);
		studentJDBCTemplate.create("gangaraju", 22);
		studentJDBCTemplate.create("dilip", 23);
		
		List<Student> students = studentJDBCTemplate.listStudents();
		for (Student record : students) {
			System.out.print("ID : " + record.getId());
			System.out.print(", Name : " + record.getName());
			System.out.println(", Age : " + record.getAge());
		}
		
		System.out.println("----Updating Record with ID = 2 -----");
		studentJDBCTemplate.update(2, 20);
		
		System.out.println("----Listing Record with ID = 2 -----");
		Student student = studentJDBCTemplate.getStudent(2);
		System.out.print("ID : " + student.getId());
		System.out.print(", Name : " + student.getName());
		System.out.println(", Age : " + student.getAge());
	}
}
