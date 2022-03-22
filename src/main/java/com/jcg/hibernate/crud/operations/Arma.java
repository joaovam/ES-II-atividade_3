package com.jcg.hibernate.crud.operations;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="ARMA_689386_698159")
public class Arma implements Serializable {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  int id;

    @Column(name="tipo")
    private String tipo;

    @Column(name="nome")
    private String nome;

    @OneToMany(mappedBy = "arma")
    private List<ArmaCrime> armaCrimes;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Arma{" +
                "id=" + id +
                ", tipo='" + tipo + '\'' +
                ", nome='" + nome + '\'' +
                '}';
    }
}
