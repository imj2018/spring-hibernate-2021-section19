package com.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.demo.entity.Student;

public class ReadStudentDemo {
	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Student.class)
									.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			// use the session object to save Java object

			// create a student object
			System.out.println("Creating new student object...");
			Student student = new Student("Daffy", "Duck", "daffy@email.com");
			
			// start a transaction
			session.beginTransaction();
			
			// save the student object
			System.out.println("Saving the student...");
			System.out.println(student);
			session.save(student);
			
			// commit transaction
			session.getTransaction().commit();
			
			// MY NEW CODE
			
			// find out the student's id: primary key
			System.out.println("Saved student. Generated id: " + student.getId());
			
			// now get a new session and start a transaction
			// hibernate always uses transaction (part of development process)
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			// retrieve the student based on the id: primary key
			System.out.println("\nGetting student with id: " + student.getId());
			
			Student getStudent = session.get(Student.class, student.getId()); 
			
			System.out.println("Get complete: " + getStudent);
			
			// commit the transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
		}
		finally {
			factory.close();
		}
		
	}
}
