package com.jcg.hibernate.crud.operations.controle;


import com.jcg.hibernate.crud.operations.*;

import java.util.List;


public class VitimaCrimeCT {

    public  List<Vitima> getVitimas(){
       return DbOperations_Vitima.displayRecords();
    }

    public  List<Crime> getCrimes(){
        List<Crime> crimes = DbOperations_Crime.displayCrimes();
        return crimes;
    }

    public void createVitimaCrime(Crime crime, Vitima vitima){
        VitimaCrime vitimaCrime = new VitimaCrime(crime, vitima);
        DbOperations_Vitima_Crime.createVitimaCrime(vitimaCrime);
    }

    public Vitima select(String nomeDigitado) {
        return DbOperations_Vitima.getByName(nomeDigitado);
    }
}
