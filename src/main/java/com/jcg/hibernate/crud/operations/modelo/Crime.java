package com.jcg.hibernate.crud.operations.modelo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="CRIME_689386_698159")
public class Crime implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="tipo")
	private String tipo;

	@Column(name="descricao")
	private String descricao;

	@Column(name="local")
	private String local;

	@Column(name="data")
	private String data;

	@Column(name="visto")
	private boolean visto;

	@OneToMany(mappedBy = "crime")
	private List<CriminosoCrime> criminosoCrimes;

	@OneToMany(mappedBy = "crime")
	private List<VitimaCrime> vitimaCrimes;

	@OneToMany(mappedBy = "crime")
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public boolean isVisto() {
		return visto;
	}

	public void setVisto(boolean visto) {
		this.visto = visto;
	}

	@Override
	public String toString() {
		return "Crime{" +
				"id=" + id +
				", tipo='" + tipo + '\'' +
				", descricao='" + descricao + '\'' +
				", local='" + local + '\'' +
				", data='" + data + '\'' +
				", visto=" + visto +
				'}';
	}
}