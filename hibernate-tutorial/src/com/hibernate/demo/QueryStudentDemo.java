package com.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.demo.entity.Student;

public class QueryStudentDemo {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Student.class)
									.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {

			// start a transaction
			session.beginTransaction();
			
			
			// query students
			// using HQL "from Student"
			List<Student> students = session.createQuery("from Student").getResultList();
			
			// display the students
			// use refactor to extract to a method
			//for (Student item : students) {
			//	System.out.println(item);
			//}
			displayStudents(students);
			
			// query students: lastName 'Doe'
			// "Use the java property name (not column name)"
			students = session.createQuery("from Student s where s.lastName='Doe'").getResultList();
				
			System.out.println("\n\nStudents who have last name of Doe");
			displayStudents(students);
			
			// query students: lastName='Doe' or firstName='Daffy'
			// a space is needed second line to work + " s.lastName='Doe'...
			students = session.createQuery("from Student student where" 
					+ " student.lastName='Doe' OR student.firstName='Daffy'").getResultList();
			
			System.out.println("\n\nStudents who have last name of Doe OR first name Daffy");
			displayStudents(students);
			
			// query students where email LIKE '%luv2code.com'
			students = session.createQuery("from Student s where"
					+ " s.email LIKE '%luv2code.com'").getResultList();
			
			System.out.println("\n\nStudents whose email ends with luv2code.com");
			displayStudents(students);
			
			
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
					
		}
		finally {
			factory.close();
		}
		
	}

	private static void displayStudents(List<Student> students) {
		for (Student item : students) {
			System.out.println(item);
		}
	}

}
