package com.jcg.hibernate.crud.operations.controle;


import com.jcg.hibernate.crud.operations.*;

import java.util.List;


public class CriminosoVitimaCT {

    public  List<Vitima> getVitimas(){
       return DbOperations_Vitima.displayRecords();
    }

    public  List<Criminoso> getCriminosos(){
        List<Criminoso> criminosos = DbOperations_Criminoso.displayRecords();
        return criminosos;
    }

    public void createCriminosoVitima(Criminoso criminoso, Vitima vitima){
        CriminosoVitima criminosoVitima = new CriminosoVitima(criminoso, vitima);
        DbOperations_Criminoso_Vitima.createCriminosoVitima(criminosoVitima);
    }

    public Criminoso select(String nomeDigitado) {
        return DbOperations_Criminoso.getByName(nomeDigitado);
    }
}
