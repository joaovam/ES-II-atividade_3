package com.jcg.hibernate.crud.operations.controle;


import com.jcg.hibernate.crud.operations.*;

import java.util.List;


public class VitimaCT {

    public void postVitima(Vitima vitima) {
        DbOperations_Vitima.createRecord(vitima);
    }

    public List<Vitima> getVitimas() {
        return DbOperations_Vitima.displayRecords();
    }

    public Vitima select(String nomeDigitado) {

        return DbOperations_Vitima.getByName(nomeDigitado);
    }

    public void delete(Vitima cbusca) {
        DbOperations_Vitima.deleteRecord(cbusca.getId());
    }

    public void update(Vitima vitima) {
        DbOperations_Vitima.updateRecord(vitima);
    }
}
