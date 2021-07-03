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
			// use refactor to method
			//for (Student item : students) {
			//	System.out.println(item);
			//}
			displayStudents(students);
			
			// query students: lastName 'Doe'
			// "Use the java property name (not column name)"
			students = session.createQuery("from Student s where s.lastName='Doe'").getResultList();
				
			System.out.println("\n\nStudents who have last name of Doe");
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
