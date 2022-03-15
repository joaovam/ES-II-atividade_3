package com.jcg.hibernate.crud.operations;
import javax.persistence.*;
@Entity
@IdClass(ArmaCrimeId.class)
@Table(name="ARMA_CRIME_689386_698159")
public class ArmaCrime {
    @Id
    @ManyToOne
    @JoinColumn(name="crime_id")
    private Crime crime;

    @Id
    @ManyToOne
    @JoinColumn(name="arma_id")
    private Arma arma;

    public ArmaCrime() {
    }

    public ArmaCrime(Crime crime, Arma arma) {
        this.crime = crime;
        this.arma = arma;
    }

    public Crime getCrime() {
        return crime;
    }

    public void setCrime(Crime crime) {
        this.crime = crime;
    }

    public Arma getArma() {
        return arma;
    }

    public void setArma(Arma arma) {
        this.arma = arma;
    }

    @Override
    public String toString() {
        return "ArmaCrime{" +
                "crime=" + crime +
                ", arma=" + arma +
                '}';
    }
}
