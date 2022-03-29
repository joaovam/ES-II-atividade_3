package com.jcg.hibernate.crud.operations.controle;


import com.jcg.hibernate.crud.operations.*;
import com.jcg.hibernate.crud.operations.dbOperations.DbOperations_Arma;
import com.jcg.hibernate.crud.operations.dbOperations.DbOperations_Arma_Crime;
import com.jcg.hibernate.crud.operations.dbOperations.DbOperations_Crime;
import com.jcg.hibernate.crud.operations.dbOperations.DbOperations_Criminoso_Vitima;
import com.jcg.hibernate.crud.operations.modelo.*;

import java.util.List;


public class ArmaCrimeCT {

    public  List<Arma> getArma(){
       return DbOperations_Arma.displayRecords();
    }

    public  List<Crime> getCrimes(){
        return DbOperations_Crime.displayCrimes();
    }

    public void createArmaCrime(Arma arma, Crime crime){
        ArmaCrime armaCrime = new ArmaCrime(crime, arma);
        DbOperations_Arma_Crime.createRecord(armaCrime);
    }

    public ArmaCrime select(Arma arma, Crime crime) {
        return DbOperations_Arma_Crime.findRecordById(crime,arma);
    }

    public void delete(String armaNome, String nomeCrime) {
        DbOperations_Arma_Crime.deleteRecord(armaNome,nomeCrime);
    }
}
