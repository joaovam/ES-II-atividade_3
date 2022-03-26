package com.jcg.hibernate.crud.operations;

import com.jcg.hibernate.crud.operations.dbOperations.DbOperations;
import com.jcg.hibernate.crud.operations.modelo.Contato;

import java.util.List;



public class AppMain {

	public static void main(String[] args) {
		System.out.println(".......Hibernate Crud Operations Example.......\n");

		System.out.println("\n=======CREATE RECORDS=======\n");
		DbOperations.createRecord();
		//DbOperations.createVitimasECriminososRecords();

		System.out.println("\n=======READ RECORDS=======\n");
		List<Contato>viewContatos = DbOperations.displayRecords();
		if(viewContatos != null & viewContatos.size() > 0) {
			for(Contato contatoObj : viewContatos) {
				System.out.println(contatoObj.toString());
			}
		}

		System.out.println("\n=======UPDATE RECORDS=======\n");
		int updateId = 1;
		DbOperations.updateRecord(updateId);
		System.out.println("\n=======READ RECORDS AFTER UPDATION=======\n");
		List<Contato> updateContato = DbOperations.displayRecords();
		if(updateContato != null & updateContato.size() > 0) {
			for(Contato contatoObj : updateContato) {
				System.out.println(contatoObj.toString());
			}
		}

		System.out.println("\n=======DELETE RECORD=======\n");
		int deleteId = 5;
		DbOperations.deleteRecord(deleteId);
		System.out.println("\n=======READ RECORDS AFTER DELETION=======\n");
		List<Contato> deleteContatoRecord = DbOperations.displayRecords();
		for(Contato contatoObj : deleteContatoRecord) {
			System.out.println(contatoObj.toString());
		}

		System.exit(0);
	} 
}