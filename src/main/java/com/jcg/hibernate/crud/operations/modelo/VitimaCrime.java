package com.jcg.hibernate.crud.operations.modelo;
import com.jcg.hibernate.crud.operations.modelo.idsCompostos.VitimaCrimeId;

import javax.persistence.*;

@Entity
@IdClass(VitimaCrimeId.class)
@Table(name="VITIMA_CRIME_689386_698159")
public class VitimaCrime {
    @Id
    @ManyToOne
    @JoinColumn(name="crime_id")
    private Crime crime;

    @Id
    @ManyToOne
    @JoinColumn(name="vitima_id")
    private Vitima vitima;

    public VitimaCrime(Crime crime, Vitima vitima) {
        this.crime = crime;
        this.vitima = vitima;
    }

    public VitimaCrime() {
    }

    @Override
    public String toString() {
        return "VitimaCrime{" +
                "crime=" + crime +
                ", vitima=" + vitima +
                '}';
    }

    public Crime getCrime() {
        return crime;
    }

    public void setCrime(Crime crime) {
        this.crime = crime;
    }

    public Vitima getVitima() {
        return vitima;
    }

    public void setVitima(Vitima vitima) {
        this.vitima = vitima;
    }
}
