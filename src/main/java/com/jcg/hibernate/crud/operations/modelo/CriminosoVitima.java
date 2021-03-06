package com.jcg.hibernate.crud.operations.modelo;

import com.jcg.hibernate.crud.operations.modelo.idsCompostos.CriminosoVitimaId;

import javax.persistence.*;

@Entity
@IdClass(CriminosoVitimaId.class)
@Table(name = "CRIMINOSO_VITIMA_689386_698159")
public class CriminosoVitima {
    @Id
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="criminoso_id")
    private Criminoso criminoso;

    @Id
    @ManyToOne
    @JoinColumn(name = "vitima_id")
    private Vitima vitima;

    public CriminosoVitima() {

    }

    public CriminosoVitima(Criminoso criminoso, Vitima vitima) {
        this.criminoso = criminoso;
        this.vitima = vitima;

    }

    public Criminoso getCriminoso() {
        return criminoso;
    }

    public void setCriminoso(Criminoso criminoso) {
        this.criminoso = criminoso;
    }

    public Vitima getVitima() {
        return vitima;
    }

    public void setVitima(Vitima vitima) {
        this.vitima = vitima;
    }

    @Override
    public String toString() {
        return "CriminosoVitima{" +
                "criminoso=" + criminoso +
                ", vitima=" + vitima +
                '}';
    }
}
