package com.jcg.hibernate.crud.operations.controle;


import com.jcg.hibernate.crud.operations.*;
import com.jcg.hibernate.crud.operations.dbOperations.DbOperations_Crime;
import com.jcg.hibernate.crud.operations.dbOperations.DbOperations_Criminoso;
import com.jcg.hibernate.crud.operations.dbOperations.DbOperations_Criminoso_Crime;
import com.jcg.hibernate.crud.operations.modelo.Crime;
import com.jcg.hibernate.crud.operations.modelo.Criminoso;
import com.jcg.hibernate.crud.operations.modelo.CriminosoCrime;

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

    public Criminoso select(String nomeDigitado) {
        return DbOperations_Criminoso.getByName(nomeDigitado);
    }
}
