package com.jcg.hibernate.crud.operations.modelo.idsCompostos;

import java.io.Serializable;
import java.util.Objects;

public class CriminosoVitimaId implements Serializable {

    private int criminoso;
    private int vitima;

    public CriminosoVitimaId() {
    }

    public CriminosoVitimaId(int criminoso, int vitima) {
        this.criminoso = criminoso;
        this.vitima = vitima;
    }

    public int getCriminoso() {
        return criminoso;
    }

    public void setCriminoso(int criminoso) {
        this.criminoso = criminoso;
    }

    public int getCrime() {
        return vitima;
    }

    public void setCrime(int vitima) {
        this.vitima = vitima;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CriminosoVitimaId that = (CriminosoVitimaId) o;
        return criminoso == that.criminoso && vitima == that.vitima;
    }

    @Override
    public int hashCode() {
        return Objects.hash(criminoso, vitima);
    }
}
