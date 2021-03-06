package com.jcg.hibernate.crud.operations.dbOperations;

import com.jcg.hibernate.crud.operations.modelo.*;
import com.jcg.hibernate.crud.operations.modelo.idsCompostos.VitimaCrimeId;
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

public class DbOperations_Vitima_Crime {

	static Session sessionObj;
	static SessionFactory sessionFactoryObj;
	public final static Logger logger = Logger.getLogger(DbOperations_Vitima_Crime.class);

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
	public static void createVitimaCrime(VitimaCrime vc) {
		try {
			// Getting Session Object From SessionFactory
			sessionObj = buildSessionFactory().openSession();
			// Getting Transaction Object From Session Object
			sessionObj.beginTransaction();
			Crime crime = (Crime) sessionObj.createQuery("from Crime where descricao = :descricao").setParameter("descricao", vc.getCrime().getDescricao()).uniqueResult();
			Vitima vitima = (Vitima) sessionObj.createQuery("from Vitima where nome = :nome").setParameter("nome", vc.getVitima().getNome()).uniqueResult();
			vc = new VitimaCrime(crime,vitima);
			// Creating Transaction Entities

			sessionObj.save(vc);


			// Committing The Transactions To The Database
			sessionObj.getTransaction().commit();
			System.out.println("\nSuccessfully Created Victim for  the crime:'" + vc.getCrime() + "' in The Database!\n");
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
	public static List<VitimaCrime> displayVitimaCrime() {
		List<VitimaCrime> vitimaCrimeList = new ArrayList<VitimaCrime>();
		try {
			// Getting Session Object From SessionFactory
			sessionObj = buildSessionFactory().openSession();
			// Getting Transaction Object From Session Object
			sessionObj.beginTransaction();

			vitimaCrimeList = sessionObj.createQuery("FROM VitimaCrime").list();
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
		return vitimaCrimeList;
	}

	// Method 3: This Method Is Used To Update A Record In The Database Table	
	public static void updateVitimaCrime(VitimaCrime vitimaCrime) {
		try {
			// Getting Session Object From SessionFactory
			sessionObj = buildSessionFactory().openSession();
			// Getting Transaction Object From Session Object
			sessionObj.beginTransaction();

			// Creating Transaction Entity
			VitimaCrime vitimaCrimeObj = (VitimaCrime) sessionObj.get(VitimaCrime.class, (Serializable) new VitimaCrimeId(vitimaCrime.getVitima().getId(),vitimaCrime.getCrime().getId()));
			vitimaCrimeObj = vitimaCrime;


			// Committing The Transactions To The Database
			sessionObj.getTransaction().commit();
			System.out.println("\nVitimaCrime With Id?= " + vitimaCrime.getCrime().getId() + " , " + vitimaCrime.getVitima().getId() + " Is Successfully Updated In The Database!\n");
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
	public static void deleteVitimaCrime(String idCrime,String nomeVitima) {
		try {
			// Getting Session Object From SessionFactory
			sessionObj = buildSessionFactory().openSession();
			// Getting Transaction Object From Session Object
			sessionObj.beginTransaction();

			Crime crime = (Crime) sessionObj.createQuery("from Crime where descricao = :descricao").setParameter("descricao", idCrime).uniqueResult();
			Vitima vitima = (Vitima) sessionObj.createQuery("from Vitima where nome = :nome").setParameter("nome", nomeVitima).uniqueResult();
			VitimaCrime vitimaCrimeOBJ = new VitimaCrime(crime, vitima);
			sessionObj.delete(vitimaCrimeOBJ);

			// Committing The Transactions To The Database
			sessionObj.getTransaction().commit();
			System.out.println("\nCrimeVitima With Id?= " + crime.getId() + ", " + vitima.getId() + " Is Successfully Deleted From The Database!\n");
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
	public static VitimaCrime findRecordById(Crime crime, Vitima vitima) {
		VitimaCrime findCrimeVitima = null;
		try {
			// Getting Session Object From SessionFactory
			sessionObj = buildSessionFactory().openSession();
			// Getting Transaction Object From Session Object
			sessionObj.beginTransaction();
			//ArmaCrime armaCrimeObj = (ArmaCrime) sessionObj.get( ArmaCrime.class,(new ArmaCrimeId(armaCrime.getArma().getId(),armaCrime.getCrime().getId())));
			findCrimeVitima = (VitimaCrime) sessionObj.load(VitimaCrime.class, (new VitimaCrimeId(vitima.getId(),crime.getId())));
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
		return findCrimeVitima;
	}

	// Method 5: This Method Is Used To Delete All Records From The Database Table
	public static void deleteAllRecords() {
		try {
			// Getting Session Object From SessionFactory
			sessionObj = buildSessionFactory().openSession();
			// Getting Transaction Object From Session Object
			sessionObj.beginTransaction();

			Query queryObj = sessionObj.createQuery("DELETE FROM VitimaCrime");
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