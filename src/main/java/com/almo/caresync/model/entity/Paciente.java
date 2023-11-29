package com.almo.caresync.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "paciente")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Paciente {

	@Id
	@Column(name = "idPaciente")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idPaciente;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "dataNascimento")
	private String dataNascimento;
	
	@Column(name = "genero")
	private String genero;
	
	@Column(name = "endereco")
	private String endereco;
	
	// Relacionamento muitos para um com Cuidador
	@JsonIgnore
    @ManyToOne
    @JoinColumn(name = "idCuidador")
    private Cuidador cuidador;

	
}
