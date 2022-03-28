package com.jcg.hibernate.crud.operations;

import com.jcg.hibernate.crud.operations.dbOperations.DbOperations_Arma;
import com.jcg.hibernate.crud.operations.modelo.Arma;
import com.jcg.hibernate.crud.operations.modelo.Contato;

import java.util.List;



public class AppMain {

	public static void main(String[] args) {
		System.out.println(".......Hibernate Crud Operations Example.......\n");

		System.out.println("\n=======CREATE RECORDS=======\n");
		DbOperations_Arma.createRecord(new Arma(0,"branca","faca"));
		//DbOperations_Arma.createVitimasECriminososRecords();

		System.out.println("\n=======READ RECORDS=======\n");
		List<Arma> viewArmas = DbOperations_Arma.displayRecords();
		if(viewArmas != null & viewArmas.size() > 0) {
			for(Arma contatoObj : viewArmas) {
				System.out.println(contatoObj.toString());
			}
		}

		System.out.println("\n=======UPDATE RECORDS=======\n");
		int updateId = 1;
		DbOperations_Arma.updateRecord(new Arma(updateId,"branca","faca"));
		System.out.println("\n=======READ RECORDS AFTER UPDATION=======\n");
		List<Arma> updateContato = DbOperations_Arma.displayRecords();
		if(updateContato != null & updateContato.size() > 0) {
			for(Arma contatoObj : updateContato) {
				System.out.println(contatoObj.toString());
			}
		}

		System.out.println("\n=======DELETE RECORD=======\n");
		int deleteId = 5;
		DbOperations_Arma.deleteRecord(deleteId);
		System.out.println("\n=======READ RECORDS AFTER DELETION=======\n");
		List<Arma> deleteContatoRecord = DbOperations_Arma.displayRecords();
		for(Arma contatoObj : deleteContatoRecord) {
			System.out.println(contatoObj.toString());
		}

		System.exit(0);
	} 
}