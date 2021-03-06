package com.jcg.hibernate.crud.operations.dbOperations;

import com.jcg.hibernate.crud.operations.modelo.*;
import com.jcg.hibernate.crud.operations.modelo.idsCompostos.ArmaCrimeId;
import com.jcg.hibernate.crud.operations.modelo.idsCompostos.CriminosoVitimaId;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.jboss.logging.Logger;

import java.util.ArrayList;
import java.util.List;

public class DbOperations_Arma_Crime {

	static Session sessionObj;
	static SessionFactory sessionFactoryObj;
	public final static Logger logger = Logger.getLogger(DbOperations_Arma_Crime.class);

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
	public static void createRecord(ArmaCrime armaCrimeObj) {
		try {
			// Getting Session Object From SessionFactory
			sessionObj = buildSessionFactory().openSession();
			// Getting Transaction Object From Session Object
			sessionObj.beginTransaction();
			Crime crime = (Crime) sessionObj.createQuery("from Crime where descricao = :descricao").setParameter("descricao", armaCrimeObj.getCrime().getDescricao()).uniqueResult();
			Arma arma = (Arma) sessionObj.createQuery("from Arma where nome = :nome").setParameter("nome", armaCrimeObj.getArma().getNome()).uniqueResult();
			armaCrimeObj = new ArmaCrime(crime, arma);
			// Creating Transaction Entities
			sessionObj.persist(armaCrimeObj);


			// Committing The Transactions To The Database
			sessionObj.getTransaction().commit();
			System.out.println("\nSuccessfully Created Gun for  the crime:'" + armaCrimeObj.getCrime() + "' in The Database!\n");
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
	public static List<ArmaCrime> displayRecords() {
		List<ArmaCrime> armaCrimeList = new ArrayList<ArmaCrime>();
		try {
			// Getting Session Object From SessionFactory
			sessionObj = buildSessionFactory().openSession();
			// Getting Transaction Object From Session Object
			sessionObj.beginTransaction();

			armaCrimeList = sessionObj.createQuery("FROM ArmaCrime").list();
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
		return armaCrimeList;
	}

	// Method 3: This Method Is Used To Update A Record In The Database Table	
	public static void updateRecord(ArmaCrime armaCrime) {
		try {
			// Getting Session Object From SessionFactory
			sessionObj = buildSessionFactory().openSession();
			// Getting Transaction Object From Session Object
			sessionObj.beginTransaction();

			// Creating Transaction Entity
			ArmaCrime armaCrimeObj = (ArmaCrime) sessionObj.get( ArmaCrime.class,(new ArmaCrimeId(armaCrime.getArma().getId(),armaCrime.getCrime().getId())));
			armaCrimeObj = armaCrime;


			// Committing The Transactions To The Database
			sessionObj.getTransaction().commit();
			System.out.println("\nContato With Id?= " + armaCrime.getCrime() + " Is Successfully Updated In The Database!\n");
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
	public static void deleteRecord(String nomeArma,String nomeCrime) {
		try {
			// Getting Session Object From SessionFactory
			sessionObj = buildSessionFactory().openSession();
			// Getting Transaction Object From Session Object
			sessionObj.beginTransaction();

			Arma arma = (Arma) sessionObj.createQuery("from Arma where nome = :nome").setParameter("nome", nomeArma).uniqueResult();
			Crime crime = (Crime) sessionObj.createQuery("from Crime where descricao = :descricao").setParameter("descricao", nomeCrime).uniqueResult();
			ArmaCrime armaCrimeOBJ = new ArmaCrime(crime,arma);
			sessionObj.delete(armaCrimeOBJ);

			// Committing The Transactions To The Database
			sessionObj.getTransaction().commit();
			System.out.println("\nArmaCrime With Id?= " + crime.getId() + ", " + arma.getId() + " Is Successfully Deleted From The Database!\n");
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
	public static ArmaCrime findRecordById(Crime crime, Arma arma) {
		ArmaCrime findArmaCrime = null;
		try {
			// Getting Session Object From SessionFactory
			sessionObj = buildSessionFactory().openSession();
			// Getting Transaction Object From Session Object
			sessionObj.beginTransaction();
			//findcriminosoVitimaObj = (CriminosoVitima) sessionObj.load(CriminosoVitima.class, (new CriminosoVitimaId(criminoso.getId(), vitima.getId())))
			//ArmaCrime armaCrimeObj = (ArmaCrime) sessionObj.get( ArmaCrime.class,(new ArmaCrimeId(armaCrime.getArma().getId(),armaCrime.getCrime().getId())));
			findArmaCrime = (ArmaCrime) sessionObj.load(ArmaCrime.class, (new ArmaCrimeId(arma.getId(), crime.getId())));
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
		return findArmaCrime;
	}

	// Method 5: This Method Is Used To Delete All Records From The Database Table
	public static void deleteAllRecords() {
		try {
			// Getting Session Object From SessionFactory
			sessionObj = buildSessionFactory().openSession();
			// Getting Transaction Object From Session Object
			sessionObj.beginTransaction();

			Query queryObj = sessionObj.createQuery("DELETE FROM ArmaCrime");
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