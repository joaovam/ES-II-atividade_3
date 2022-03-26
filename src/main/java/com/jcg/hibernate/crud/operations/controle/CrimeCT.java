package com.jcg.hibernate.crud.operations.controle;


import com.jcg.hibernate.crud.operations.dbOperations.DbOperations_Arma;
import com.jcg.hibernate.crud.operations.dbOperations.DbOperations_Crime;
import com.jcg.hibernate.crud.operations.modelo.Arma;
import com.jcg.hibernate.crud.operations.modelo.Crime;
import com.jcg.hibernate.crud.operations.dbOperations.DbOperations;


public class CrimeCT {

    public void insert(Crime c) {
        DbOperations_Crime.createCrime(c);
    }

    public Crime select(int id){
        return DbOperations_Crime.findCrimeById(id);
    }
    public void delete(Crime cbusca) {
        DbOperations_Crime.deleteCrime(cbusca.getId());
    }

    public void update(Crime crime) {
        DbOperations_Crime.updateCrime(crime);
    }


}
