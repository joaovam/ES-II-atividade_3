package com.jcg.hibernate.crud.operations.controle;


import com.jcg.hibernate.crud.operations.*;

import java.util.List;


public class VitimaCrimeCT {

    public  List<Vitima> getVitimas(){
       return DbOperations_Vitima.displayRecords();
    }

    public  List<Crime> getCrimes(){
        return DbOperations_Crime.displayCrimes();
    }

    public void createVitimaCrime(Crime crime, Vitima vitima){
        VitimaCrime vitimaCrime = new VitimaCrime(crime, vitima);
        DbOperations_Vitima_Crime.createVitimaCrime(vitimaCrime);
    }

    public Vitima select(String nomeDigitado) {
        return DbOperations_Vitima.getByName(nomeDigitado);
    }
}
