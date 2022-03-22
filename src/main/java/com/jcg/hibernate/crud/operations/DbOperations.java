package com.jcg.hibernate.crud.operations;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DbOperations {

	static Session sessionObj;
	static SessionFactory sessionFactoryObj;


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
	public static void createRecord() {
		int count = 0;
		Contato contatoObj = null;
		try {
			// Getting Session Object From SessionFactory
			sessionObj = buildSessionFactory().openSession();
			// Getting Transaction Object From Session Object
			sessionObj.beginTransaction();

			// Creating Transaction Entities
			for(int j = 101; j <= 105; j++) {
				count = count + 1;
				contatoObj = new Contato();
				contatoObj.setEndereco("RUA XXXXX, 999");
				contatoObj.setNome("aluno " + j);
				contatoObj.setTelefone("(31)9999-8877");
				sessionObj.save(contatoObj);
			}

			// Committing The Transactions To The Database
			sessionObj.getTransaction().commit();
			System.out.println("\nSuccessfully Created '" + count + "' Records In The Database!\n");
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
	private static List<Vitima> getVitimas(){
		List<Vitima> vitimas = new ArrayList<>();
		List<String> nomes = new ArrayList<>();
		List<String> generos = new ArrayList<>();
		List<Integer> idades = new ArrayList<>();
		nomes.add("Joana");
		generos.add("feminino");
		idades.add(25);
		nomes.add("Carlos");
		generos.add("masculino");
		idades.add(30);
		nomes.add("José Souza");
		generos.add("masculino");
		idades.add(18);
		nomes.add("Flávia");
		generos.add("feminino");
		idades.add(40);
		int count = 0;
		for(int j = 0; j <= 3; j++) {
			count = count + 1;
			Random random = new Random();
			int cpf = random.nextInt(999999);
			vitimas.add(new Vitima(0, nomes.get(j), generos.get(j), idades.get(j), String.format("%06d", cpf)));
		}
		return vitimas;
	}
	private static List<Criminoso> getCriminosos(){
		List<Criminoso> criminosos = new ArrayList<>();
		List<String> nomes = new ArrayList<>();
		List<String> generos = new ArrayList<>();
		List<Integer> idades = new ArrayList<>();
		nomes.add("Tamires Tolero");
		generos.add("feminino");
		idades.add(25);
		nomes.add("Junio Trancoso");
		generos.add("masculino");
		idades.add(19);
		nomes.add("Rick Ribeiro");
		generos.add("masculino");
		idades.add(27);
		nomes.add("Fabricia Joana");
		generos.add("feminino");
		idades.add(35);
		int count = 0;
		for(int j = 0; j <= 3; j++) {
			count = count + 1;
			Random random = new Random();
			int cpf = random.nextInt(999999);
			criminosos.add(new Criminoso(0, nomes.get(j), generos.get(j), idades.get(j), String.format("%06d", cpf)));
		}
		return criminosos;
	}
	@SuppressWarnings("unchecked")
	private static List<Arma> getArmas(){
		List<Arma> armasList = new ArrayList<Arma>();
		try {
			// Getting Session Object From SessionFactory
			sessionObj = buildSessionFactory().openSession();
			// Getting Transaction Object From Session Object
			sessionObj.beginTransaction();

			armasList = sessionObj.createQuery("FROM Arma").list();
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
		return armasList;
	}

	public static void createVitimasECriminososRecords() {

		try {
			// Getting Session Object From SessionFactory
			sessionObj = buildSessionFactory().openSession();
			// Getting Transaction Object From Session Object
			sessionObj.beginTransaction();
			List<Vitima> vitimas = getVitimas();
			List<Criminoso> criminosos = getCriminosos();
			int count = 0;
			// Creating Transaction Entities
			for(int i = 0; i < criminosos.size() ; i++){
				sessionObj.save(vitimas.get(i));
				sessionObj.save(criminosos.get(i));
				CriminosoVitima criminosoVitima = new CriminosoVitima(criminosos.get(i), vitimas.get(i));
				sessionObj.save(criminosoVitima);
				count += 3;
			}

			// Committing The Transactions To The Database
			sessionObj.getTransaction().commit();
			System.out.println("\nSuccessfully Created '" + count + "' Records In The Database!\n");

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
	public static List<Contato> displayRecords() {
		List<Contato> contatosList = new ArrayList<Contato>();
		try {
			// Getting Session Object From SessionFactory
			sessionObj = buildSessionFactory().openSession();
			// Getting Transaction Object From Session Object
			sessionObj.beginTransaction();

			contatosList = sessionObj.createQuery("FROM Contato").list();
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
		return contatosList;
	}

	// Method 3: This Method Is Used To Update A Record In The Database Table	
	public static void updateRecord(int id) {
		try {
			// Getting Session Object From SessionFactory
			sessionObj = buildSessionFactory().openSession();
			// Getting Transaction Object From Session Object
			sessionObj.beginTransaction();

			// Creating Transaction Entity
			Contato contatObj = (Contato) sessionObj.get(Contato.class, id);
			contatObj.setNome("Jose");
			contatObj.setEndereco("AV AAA, 777");

			// Committing The Transactions To The Database
			sessionObj.getTransaction().commit();
			System.out.println("\nContato With Id?= " + id + " Is Successfully Updated In The Database!\n");
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
	public static void deleteRecord(Integer id) {
		try {
			// Getting Session Object From SessionFactory
			sessionObj = buildSessionFactory().openSession();
			// Getting Transaction Object From Session Object
			sessionObj.beginTransaction();

			Contato contatoObj = findRecordById(id);
			sessionObj.delete(contatoObj);

			// Committing The Transactions To The Database
			sessionObj.getTransaction().commit();
			System.out.println("\nContato With Id?= " + id + " Is Successfully Deleted From The Database!\n");
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
	public static Contato findRecordById(Integer id) {
		Contato findContatoObj = null;
		try {
			// Getting Session Object From SessionFactory
			sessionObj = buildSessionFactory().openSession();
			// Getting Transaction Object From Session Object
			sessionObj.beginTransaction();

			findContatoObj = (Contato) sessionObj.load(Contato.class, id);
		} catch(Exception sqlException) {
			if(null != sessionObj.getTransaction()) {
				System.out.println("\n.......Transaction Is Being Rolled Back.......\n");
				sessionObj.getTransaction().rollback();
			}
			sqlException.printStackTrace();
		} 
		return findContatoObj;
	}

	// Method 5: This Method Is Used To Delete All Records From The Database Table
	public static void deleteAllRecords() {
		try {
			// Getting Session Object From SessionFactory
			sessionObj = buildSessionFactory().openSession();
			// Getting Transaction Object From Session Object
			sessionObj.beginTransaction();

			Query queryObj = sessionObj.createQuery("DELETE FROM Contato");
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