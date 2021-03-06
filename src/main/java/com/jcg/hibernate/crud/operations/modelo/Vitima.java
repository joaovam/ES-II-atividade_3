package com.jcg.hibernate.crud.operations.modelo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="VITIMA_689386_698159")
public class Vitima implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="nome")
	private String nome;

	@Column(name="genero")
	private String genero;

	@Column(name="idade")
	private int idade;

	@Column(name="cpf")
	private String cpf;

	@OneToMany(mappedBy = "vitima" , cascade = CascadeType.MERGE)
	private List<CriminosoVitima> criminosoVitima;

	@OneToMany(mappedBy = "vitima", cascade = CascadeType.MERGE)
	private List<VitimaCrime> vitimaCrimes;


	public Vitima() {
	}

	public Vitima(int id, String nome, String genero, int idade, String cpf) {
		this.id = id;
		this.nome = nome;
		this.genero = genero;
		this.idade = idade;
		this.cpf = cpf;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	@Override
	public String toString() {
		return "Vitima{" +
				"id=" + id +
				", nome='" + nome + '\'' +
				", genero='" + genero + '\'' +
				", idade='" + idade + '\'' +
				", cpf='" + cpf + '\'' +
				'}';
	}
}