package com.jcg.hibernate.crud.operations;

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

public class DbOperations_Criminoso_Vitima {

    static Session sessionObj;
    static SessionFactory sessionFactoryObj;
    public final static Logger logger = Logger.getLogger(DbOperations_Criminoso_Vitima.class);

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
    public static void createCriminosoVitima(String nomeCriminoso, String nomeVitima) {
        try {

            // Getting Session Object From SessionFactory
            sessionObj = buildSessionFactory().openSession();
            // Getting Transaction Object From Session Object
            sessionObj.beginTransaction();
            // Creating Transaction Entities
            Criminoso criminoso = (Criminoso) sessionObj.createQuery("from Criminoso where nome = :nome").setParameter("nome", nomeCriminoso).uniqueResult();
            Vitima vitima = (Vitima) sessionObj.createQuery("from Vitima where nome = :nome").setParameter("nome", nomeVitima).uniqueResult();
            CriminosoVitima criminosoVitimaObj = new CriminosoVitima(criminoso, vitima);
            sessionObj.persist(criminosoVitimaObj);


            sessionObj.getTransaction().commit();
            System.out.println("\nSuccessfully Created Victim for  the criminoso:'" + criminosoVitimaObj.getCriminoso() + "' in The Database!\n");
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


    // Method 2: This Method Is Used To Display The Records From The Database Table
    @SuppressWarnings("unchecked")
    public static List<CriminosoVitima> displayCriminosoVitima() {
        List<CriminosoVitima> criminosoVitimaList = new ArrayList<CriminosoVitima>();
        try {
            // Getting Session Object From SessionFactory
            sessionObj = buildSessionFactory().openSession();
            // Getting Transaction Object From Session Object
            sessionObj.beginTransaction();

            criminosoVitimaList = sessionObj.createQuery("FROM CriminosoVitima").list();
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
        return criminosoVitimaList;
    }

    // Method 3: This Method Is Used To Update A Record In The Database Table
    public static void updateCriminosoVitima(CriminosoVitima criminosoVitima) {
        try {
            // Getting Session Object From SessionFactory
            sessionObj = buildSessionFactory().openSession();
            // Getting Transaction Object From Session Object
            sessionObj.beginTransaction();

            // Creating Transaction Entity
            CriminosoVitima criminosoVitimaObj = (CriminosoVitima) sessionObj.get(CriminosoVitima.class, (Serializable) new CriminosoVitimaId(criminosoVitima.getCriminoso().getId(), criminosoVitima.getVitima().getId()));
            criminosoVitimaObj = criminosoVitima;


            // Committing The Transactions To The Database
            sessionObj.getTransaction().commit();
            System.out.println("\nCriminosoVitima With Id?= " + criminosoVitima.getCriminoso().getId() + " , " + criminosoVitima.getCriminoso().getId() + " Is Successfully Updated In The Database!\n");
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

    // Method 4(a): This Method Is Used To Delete A Particular Record From The Database Table
    public static void deleteCriminosoVitima(String nomeCriminoso, String nomeVitima) {
        try {
            // Getting Session Object From SessionFactory
            sessionObj = buildSessionFactory().openSession();
            // Getting Transaction Object From Session Object
            sessionObj.beginTransaction();

            Criminoso criminoso = (Criminoso) sessionObj.createQuery("from Criminoso where nome = :nome").setParameter("nome", nomeCriminoso).uniqueResult();
            Vitima vitima = (Vitima) sessionObj.createQuery("from Vitima where nome = :nome").setParameter("nome", nomeVitima).uniqueResult();
            CriminosoVitima criminosoVitimaObj = new CriminosoVitima(criminoso, vitima);
            sessionObj.delete(criminosoVitimaObj);

            // Committing The Transactions To The Database
            sessionObj.getTransaction().commit();
            System.out.println("\ncriminosoVitima With Id?= " + criminoso.getId() + ", " + vitima.getId() + " Is Successfully Deleted From The Database!\n");
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
    public static CriminosoVitima findRecordById(Criminoso criminoso, Vitima vitima) {
        CriminosoVitima findcriminosoVitimaObj = null;
        try {
            // Getting Session Object From SessionFactory
            sessionObj = buildSessionFactory().openSession();
            // Getting Transaction Object From Session Object
            sessionObj.beginTransaction();
            //ArmaCrime armaCrimeObj = (ArmaCrime) sessionObj.get( ArmaCrime.class,(new ArmaCrimeId(armaCrime.getArma().getId(),armaCrime.getCrime().getId())));
            findcriminosoVitimaObj = (CriminosoVitima) sessionObj.load(CriminosoVitima.class, (new CriminosoVitimaId(criminoso.getId(), vitima.getId())));
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
        return findcriminosoVitimaObj;
    }

    // Method 5: This Method Is Used To Delete All Records From The Database Table
    public static void deleteAllRecords() {
        try {
            // Getting Session Object From SessionFactory
            sessionObj = buildSessionFactory().openSession();
            // Getting Transaction Object From Session Object
            sessionObj.beginTransaction();

            Query queryObj = sessionObj.createQuery("DELETE FROM CriminosoVitima");
            queryObj.executeUpdate();

            // Committing The Transactions To The Database
            sessionObj.getTransaction().commit();
            System.out.println("\nSuccessfully Deleted All Records From The Database Table!\n");
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

}