package com.almo.caresync.model.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "cuidador")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Cuidador {

	@Id
	@Column(name = "idCuidador")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idCuidador;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "senha")
	private String senha;
	
	@Column(name = "foto")
	private String foto;
	
	// Relacionamento um para muitos com Paciente
	@JsonIgnore
    @OneToMany(mappedBy = "cuidador", cascade = CascadeType.ALL)
    private List<Paciente> pacientes;

	public Cuidador(String nome, String email,  String foto, String senha) {
		super();
		this.nome = nome;
		this.email = email;
		this.foto = foto;
		this.senha = senha;
	}
	
	
}
