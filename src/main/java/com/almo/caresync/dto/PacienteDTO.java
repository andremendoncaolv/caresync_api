package com.almo.caresync.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PacienteDTO {
	private Integer idPaciente;
	private String dataNascimento;
	private String endereco;
	private String genero;
	private String nome;
	private Integer idCuidador;
}
