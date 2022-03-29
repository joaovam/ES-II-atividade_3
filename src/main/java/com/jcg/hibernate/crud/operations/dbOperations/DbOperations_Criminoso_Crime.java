package com.jcg.hibernate.crud.operations.dbOperations;

import com.jcg.hibernate.crud.operations.modelo.*;
import com.jcg.hibernate.crud.operations.modelo.idsCompostos.CriminosoCrimeId;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.jboss.logging.Logger;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DbOperations_Criminoso_Crime {

	static Session sessionObj;
	static SessionFactory sessionFactoryObj;
	public final static Logger logger = Logger.getLogger(DbOperations_Criminoso_Crime.class);

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

	// Method 1: This Method Used To Create A New Student Record In The Database Table
	public static void createCriminosoCrime(CriminosoCrime criminosoCrimeObj) {
		try {
			// Getting Session Object From SessionFactory
			sessionObj = buildSessionFactory().openSession();
			// Getting Transaction Object From Session Object
			sessionObj.beginTransaction();

			// Creating Transaction Entities

			sessionObj.save(criminosoCrimeObj);


			// Committing The Transactions To The Database
			sessionObj.getTransaction().commit();
			System.out.println("\nSuccessfully Created Criminoso for  the crime:'" + criminosoCrimeObj.getCrime() + "' in The Database!\n");
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
	public static List<CriminosoCrime> displayCriminosoCrime() {
		List<CriminosoCrime> criminosoCrimeList = new ArrayList<CriminosoCrime>();
		try {
			// Getting Session Object From SessionFactory
			sessionObj = buildSessionFactory().openSession();
			// Getting Transaction Object From Session Object
			sessionObj.beginTransaction();

			criminosoCrimeList = sessionObj.createQuery("FROM CriminosoCrime").list();
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
		return criminosoCrimeList;
	}

	// Method 3: This Method Is Used To Update A Record In The Database Table
	public static void updateCriminosoCrime(CriminosoCrime criminosoCrime) {
		try {
			// Getting Session Object From SessionFactory
			sessionObj = buildSessionFactory().openSession();
			// Getting Transaction Object From Session Object
			sessionObj.beginTransaction();

			// Creating Transaction Entity
			CriminosoCrime criminosoCrimeObj = (CriminosoCrime) sessionObj.get(CriminosoCrime.class, (Serializable) new CriminosoCrimeId(criminosoCrime.getCriminoso().getId(),criminosoCrime.getCrime().getId()));
			criminosoCrimeObj = criminosoCrime;


			// Committing The Transactions To The Database
			sessionObj.getTransaction().commit();
			System.out.println("\nCriminosoCrime With Id?= " + criminosoCrime.getCrime().getId() + " , " + criminosoCrime.getCriminoso().getId() + " Is Successfully Updated In The Database!\n");
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
	public static void deleteCriminosoCrime(String nomeCriminoso,int idCrime) {
		try {
			// Getting Session Object From SessionFactory
			sessionObj = buildSessionFactory().openSession();
			// Getting Transaction Object From Session Object
			sessionObj.beginTransaction();

			Criminoso criminoso = (Criminoso) sessionObj.createQuery("from Criminoso where nome = :nome").setParameter("nome", nomeCriminoso).uniqueResult();
			Crime crime = (Crime) sessionObj.createQuery("from Crime where id = :id").setParameter("id", idCrime).uniqueResult();
			CriminosoCrime criminosoCrimeOBJ = new CriminosoCrime(criminoso, crime);
			sessionObj.delete(criminosoCrimeOBJ);

			// Committing The Transactions To The Database
			sessionObj.getTransaction().commit();
			System.out.println("\ncriminosoVitima With Id?= " + criminoso.getId() + ", " + crime.getId() + " Is Successfully Deleted From The Database!\n");
		} catch (Exception sqlException) {
			if (null != sessionObj.getTransaction()) {
				System.out.println("\n.......Transaction Is Being Rolled Back.......\n");
				sessionObj.getTransaction().rollback();
			}
			sqlException.printStackTrace();
		} finally {
			if (sessionObj != null) {
				sessionObj.close();
			}
		}
	}

	// Method 4(b): This Method To Find Particular Record In The Database Table
	public static CriminosoCrime findRecordById(Criminoso criminoso, Crime crime) {
		CriminosoCrime findCriminosoCrime = null;
		try {
			// Getting Session Object From SessionFactory
			sessionObj = buildSessionFactory().openSession();
			// Getting Transaction Object From Session Object
			sessionObj.beginTransaction();
			//ArmaCrime armaCrimeObj = (ArmaCrime) sessionObj.get( ArmaCrime.class,(new ArmaCrimeId(armaCrime.getArma().getId(),armaCrime.getCrime().getId())));

			findCriminosoCrime = (CriminosoCrime) sessionObj.load(CriminosoCrime.class, (new CriminosoCrimeId(criminoso.getId(), crime.getId())));
		} catch ( ObjectNotFoundException notFoundException){
			return null;
		} catch (Exception sqlException) {
			if (null != sessionObj.getTransaction()) {
				System.out.println("\n.......Transaction Is Being Rolled Back.......\n");
				sessionObj.getTransaction().rollback();
				return null;
			}
			sqlException.printStackTrace();
			return null;
		}
		return findCriminosoCrime;
	}

	// Method 5: This Method Is Used To Delete All Records From The Database Table
	public static void deleteAllRecords() {
		try {
			// Getting Session Object From SessionFactory
			sessionObj = buildSessionFactory().openSession();
			// Getting Transaction Object From Session Object
			sessionObj.beginTransaction();

			Query queryObj = sessionObj.createQuery("DELETE FROM CiminosoCrime");
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