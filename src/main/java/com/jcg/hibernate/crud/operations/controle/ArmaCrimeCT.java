package com.jcg.hibernate.crud.operations.controle;


import com.jcg.hibernate.crud.operations.*;

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

    public Arma select(String nomeDigitado) {
        return DbOperations_Arma.getByName(nomeDigitado);
    }
}
