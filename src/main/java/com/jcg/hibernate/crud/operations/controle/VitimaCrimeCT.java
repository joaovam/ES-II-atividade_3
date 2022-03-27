package com.jcg.hibernate.crud.operations.controle;


import com.jcg.hibernate.crud.operations.*;
import com.jcg.hibernate.crud.operations.dbOperations.DbOperations_Crime;
import com.jcg.hibernate.crud.operations.dbOperations.DbOperations_Criminoso_Vitima;
import com.jcg.hibernate.crud.operations.dbOperations.DbOperations_Vitima;
import com.jcg.hibernate.crud.operations.dbOperations.DbOperations_Vitima_Crime;
import com.jcg.hibernate.crud.operations.modelo.Crime;
import com.jcg.hibernate.crud.operations.modelo.Vitima;
import com.jcg.hibernate.crud.operations.modelo.VitimaCrime;

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

    public VitimaCrime select(Vitima vitima,Crime crime) {
        return DbOperations_Vitima_Crime.findRecordById(crime,vitima);
    }
    public void delete(int idCrime, String nomeVitima) {
        DbOperations_Vitima_Crime.deleteVitimaCrime(idCrime, nomeVitima);
    }
}
