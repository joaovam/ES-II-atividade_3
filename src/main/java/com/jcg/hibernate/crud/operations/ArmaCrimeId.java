package com.jcg.hibernate.crud.operations;
import java.io.Serializable;
import java.util.Objects;

public class ArmaCrimeId implements Serializable {
    private int arma;
    private int crime;

    public ArmaCrimeId() {
    }

    public ArmaCrimeId(int arma, int crime) {
        this.arma = arma;
        this.crime = crime;
    }


    public int getArma() {
        return arma;
    }

    public void setArma(int arma) {
        this.arma = arma;
    }

    public int getCrime() {
        return crime;
    }

    public void setCrime(int crime) {
        this.crime = crime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArmaCrimeId that = (ArmaCrimeId) o;
        return getArma() == that.getArma() && getCrime() == that.getCrime();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getArma(), getCrime());
    }
}
