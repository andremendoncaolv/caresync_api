package com.almo.caresync.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CuidadorDTO {
	private Integer idCuidador;
	private String nome;
	private String email;
	private String foto;
	private String senha;
}
