package com.jcg.hibernate.crud.operations.modelo;

import com.jcg.hibernate.crud.operations.modelo.idsCompostos.CriminosoCrimeId;

import javax.persistence.*;

@Entity
@IdClass(CriminosoCrimeId.class)
@Table(name = "CRIMINOSO_CRIME_689386_698159")
public class CriminosoCrime {
    @Id
    @ManyToOne
    @JoinColumn(name="criminoso_id")
    private Criminoso criminoso;

    @Id
    @ManyToOne
    @JoinColumn(name = "crime_id")
    private Crime crime;

    public CriminosoCrime() {

    }

    public CriminosoCrime(Criminoso criminoso, Crime crime) {
        this.criminoso = criminoso;
        this.crime = crime;

    }



    public Criminoso getCriminoso() {
        return criminoso;
    }

    public void setCriminoso(Criminoso criminoso) {
        this.criminoso = criminoso;
    }

    public Crime getCrime() {
        return crime;
    }

    public void setCrime(Crime crime) {
        this.crime = crime;
    }

    @Override
    public String toString() {
        return "CriminosoCrime{" +
                "criminoso=" + criminoso +
                ", crime=" + crime +
                '}';
    }
}
