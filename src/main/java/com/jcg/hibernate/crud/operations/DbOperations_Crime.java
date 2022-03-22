package com.jcg.hibernate.crud.operations;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.jboss.logging.Logger;

import java.util.ArrayList;
import java.util.List;

public class DbOperations_Crime {

	static Session sessionObj;
	static SessionFactory sessionFactoryObj;
	public final static Logger logger = Logger.getLogger(DbOperations_Crime.class);

	// This Method Is Used To Create The Hibernate's SessionFactory Object
	private static SessionFactory buildSessionFactory() {
		// Creating Configuration Instance & Passing Hibernate Configuration File
		Configuration configObj = new Configuration();
		configObj.configure("hibernate.cfg.xml");

		// Since Hibernate Version 4.x, ServiceRegistry Is Being Used
		ServiceRegistry serviceRegistryObj = new StandardServiceRegistryBuilder().applySettings(configObj.getProperties()).build(); 

		// Creating Hibernate SessionFactory Instance
		sessionFactoryObj = configObj.buildSessionFactory(serviceRegistryObj);
		return sessionFactoryObj;
	}

	// Method 1: This Method Used To Create A New crime Record In The Database Table
	public static void createCrime(Crime crime) {
		try {
			// Getting Session Object From SessionFactory
			sessionObj = buildSessionFactory().openSession();
			// Getting Transaction Object From Session Object
			sessionObj.beginTransaction();
			// Creating Transaction Entities
				sessionObj.save(crime);
			// Committing The Transactions To The Database
			sessionObj.getTransaction().commit();

			System.out.println("\nSuccessfully Created '" + crime.getId() + "' Records In The Database!\n");
		} catch(Exception sqlException) {
			if(null != sessionObj.getTransaction()) {
				System.out.println("\n.......Transaction Is Being Rolled Back.......\n");
				sessionObj.getTransaction().rollback();
			}
			sqlException.printStackTrace();
		} finally {
			if(sessionObj != null) {
				sessionObj.close();
			}
		}
	}


	// Method 2: This Method Is Used To Display The Records From The Database Table
	@SuppressWarnings("unchecked")
	public static List<Crime> displayCrimes() {
		List<Crime> crimeList = new ArrayList<Crime>();
		try {
			// Getting Session Object From SessionFactory
			sessionObj = buildSessionFactory().openSession();
			// Getting Transaction Object From Session Object
			sessionObj.beginTransaction();

			crimeList = sessionObj.createQuery("FROM CRIME_689386_698159").list();
		} catch(Exception sqlException) {
			if(null != sessionObj.getTransaction()) {
				System.out.println("\n.......Transaction Is Being Rolled Back.......\n");
				sessionObj.getTransaction().rollback();
			}
			sqlException.printStackTrace();
		} finally {
			if(sessionObj != null) {
				sessionObj.close();
			}
		}
		return crimeList;
	}

	// Method 3: This Method Is Used To Update A Record In The Database Table	
	public static void updateCrime(Crime crime) {
		try {
			// Getting Session Object From SessionFactory
			sessionObj = buildSessionFactory().openSession();
			// Getting Transaction Object From Session Object
			sessionObj.beginTransaction();

			// Creating Transaction Entity
			Crime crimeObj = (Crime) sessionObj.get(Crime.class, crime.getId());
			crimeObj = crime;
			// Committing The Transactions To The Database
			sessionObj.getTransaction().commit();
			System.out.println("\nCrime With Id?= " + crime.getId() + " Is Successfully Updated In The Database!\n");
		} catch(Exception sqlException) {
			if(null != sessionObj.getTransaction()) {
				System.out.println("\n.......Transaction Is Being Rolled Back.......\n");
				sessionObj.getTransaction().rollback();
			}
			sqlException.printStackTrace();
		} finally {
			if(sessionObj != null) {
				sessionObj.close();
			}
		}
	}

	// Method 4(a): This Method Is Used To Delete A Particular Record From The Database Table
	public static void deleteCrime(Integer id) {
		try {
			// Getting Session Object From SessionFactory
			sessionObj = buildSessionFactory().openSession();
			// Getting Transaction Object From Session Object
			sessionObj.beginTransaction();

			Crime crimeObj = findCrimeById(id);
			sessionObj.delete(crimeObj);

			// Committing The Transactions To The Database
			sessionObj.getTransaction().commit();
			System.out.println("\ncrime With Id?= " + id + " Is Successfully Deleted From The Database!\n");
		} catch(Exception sqlException) {
			if(null != sessionObj.getTransaction()) {
				System.out.println("\n.......Transaction Is Being Rolled Back.......\n");
				sessionObj.getTransaction().rollback();
			}
			sqlException.printStackTrace();
		} finally {
			if(sessionObj != null) {
				sessionObj.close();
			}
		}
	}

	// Method 4(b): This Method To Find Particular Record In The Database Table
	public static Crime findCrimeById(Integer id) {
		Crime findCrimeObj = null;
		try {
			// Getting Session Object From SessionFactory
			sessionObj = buildSessionFactory().openSession();
			// Getting Transaction Object From Session Object
			sessionObj.beginTransaction();

			findCrimeObj = (Crime) sessionObj.load(Crime.class, id);
		} catch(Exception sqlException) {
			if(null != sessionObj.getTransaction()) {
				System.out.println("\n.......Transaction Is Being Rolled Back.......\n");
				sessionObj.getTransaction().rollback();
			}
			sqlException.printStackTrace();
		} 
		return findCrimeObj;
	}

	// Method 5: This Method Is Used To Delete All Records From The Database Table
	public static void deleteAllCrimes() {
		try {
			// Getting Session Object From SessionFactory
			sessionObj = buildSessionFactory().openSession();
			// Getting Transaction Object From Session Object
			sessionObj.beginTransaction();

			Query queryObj = sessionObj.createQuery("DELETE FROM CRIME_689386_698159");
			queryObj.executeUpdate();

			// Committing The Transactions To The Database
			sessionObj.getTransaction().commit();
			System.out.println("\nSuccessfully Deleted All Records From The Database Table!\n");
		} catch(Exception sqlException) {
			if(null != sessionObj.getTransaction()) {
				System.out.println("\n.......Transaction Is Being Rolled Back.......\n");
				sessionObj.getTransaction().rollback();
			}
			sqlException.printStackTrace();
		} finally {
			if(sessionObj != null) {
				sessionObj.close();
			}
		}
	}
}