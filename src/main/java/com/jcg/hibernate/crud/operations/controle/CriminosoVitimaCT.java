package com.jcg.hibernate.crud.operations.controle;


import com.jcg.hibernate.crud.operations.dbOperations.DbOperations_Criminoso;
import com.jcg.hibernate.crud.operations.dbOperations.DbOperations_Criminoso_Vitima;
import com.jcg.hibernate.crud.operations.dbOperations.DbOperations_Vitima;
import com.jcg.hibernate.crud.operations.modelo.Criminoso;
import com.jcg.hibernate.crud.operations.modelo.CriminosoVitima;
import com.jcg.hibernate.crud.operations.modelo.Vitima;

import java.util.List;


public class CriminosoVitimaCT {

    public  List<Vitima> getVitimas(){
       return DbOperations_Vitima.displayRecords();
    }

    public  List<Criminoso> getCriminosos(){
        List<Criminoso> criminosos = DbOperations_Criminoso.displayRecords();
        return criminosos;
    }

    public void createCriminosoVitima(String nomeCriminoso, String nomeVitima){
        DbOperations_Criminoso_Vitima.createCriminosoVitima(nomeCriminoso, nomeVitima);
    }

    public Criminoso select(String nomeDigitado) {
        return DbOperations_Criminoso.getByName(nomeDigitado);
    }

    public CriminosoVitima select(Criminoso criminoso, Vitima vitima) {
        return DbOperations_Criminoso_Vitima.findRecordById(criminoso, vitima);
    }

    public void delete(String nomeCriminoso, String nomeVitima) {
         DbOperations_Criminoso_Vitima.deleteCriminosoVitima(nomeCriminoso, nomeVitima);
    }
}
