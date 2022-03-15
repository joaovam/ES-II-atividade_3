package com.jcg.hibernate.crud.operations;
import java.util.Objects;
public class VitimaCrimeId {
    private int vitima;
    private int crime;

    public VitimaCrimeId() {
    }

    public VitimaCrimeId(int vitima, int crime) {
        this.vitima = vitima;
        this.crime = crime;
    }

    public int getVitima() {
        return vitima;
    }

    public void setVitima(int vitima) {
        this.vitima = vitima;
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
        VitimaCrimeId that = (VitimaCrimeId) o;
        return vitima == that.vitima && crime == that.crime;
    }

    @Override
    public int hashCode() {
        return Objects.hash(vitima, crime);
    }
}
