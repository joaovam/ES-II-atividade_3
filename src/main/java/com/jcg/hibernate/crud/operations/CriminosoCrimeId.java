package com.jcg.hibernate.crud.operations;

import java.io.Serializable;
import java.util.Objects;

public class CriminosoCrimeId implements Serializable {

    private int criminoso;
    private int crime;

    public CriminosoCrimeId() {
    }

    public CriminosoCrimeId(int criminoso, int crime) {
        this.criminoso = criminoso;
        this.crime = crime;
    }

    public int getCriminoso() {
        return criminoso;
    }

    public void setCriminoso(int criminoso) {
        this.criminoso = criminoso;
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
        CriminosoCrimeId that = (CriminosoCrimeId) o;
        return criminoso == that.criminoso && crime == that.crime;
    }

    @Override
    public int hashCode() {
        return Objects.hash(criminoso, crime);
    }
}
