package com.jcg.hibernate.crud.operations.controle;


import com.jcg.hibernate.crud.operations.*;

import java.util.List;


public class ArmaCT {

    public  List<Arma> getArmas(){
       return DbOperations_Arma.displayRecords();
    }


    public void createArma(String tipo,String nome){
        Arma arma = new Arma(0,tipo, nome);
        DbOperations_Arma.createRecord(arma);
    }

}
