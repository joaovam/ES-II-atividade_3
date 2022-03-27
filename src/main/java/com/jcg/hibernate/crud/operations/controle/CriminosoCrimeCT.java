package com.jcg.hibernate.crud.operations.controle;


import com.jcg.hibernate.crud.operations.*;
import com.jcg.hibernate.crud.operations.dbOperations.DbOperations_Crime;
import com.jcg.hibernate.crud.operations.dbOperations.DbOperations_Criminoso;
import com.jcg.hibernate.crud.operations.dbOperations.DbOperations_Criminoso_Crime;
import com.jcg.hibernate.crud.operations.dbOperations.DbOperations_Criminoso_Vitima;
import com.jcg.hibernate.crud.operations.modelo.*;

import java.util.List;


public class CriminosoCrimeCT {

    public  List<Crime> getCrimes(){
       return DbOperations_Crime.displayCrimes();
    }

    public  List<Criminoso> getCriminosos(){
        return DbOperations_Criminoso.displayRecords();
    }

    public void createCriminosoCrime(Criminoso criminoso, Crime crime){
        CriminosoCrime criminosoCrime = new CriminosoCrime(criminoso, crime);
        DbOperations_Criminoso_Crime.createCriminosoCrime(criminosoCrime);
    }

    public CriminosoCrime select(Criminoso criminoso, Crime crime) {
        return DbOperations_Criminoso_Crime.findRecordById(criminoso, crime);
    }
    public void delete(String nomeCriminoso, int idCrime) {
        DbOperations_Criminoso_Crime.deleteCriminosoCrime(nomeCriminoso, idCrime);

    }
}
