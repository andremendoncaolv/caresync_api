package com.almo.caresync.service;

import java.util.List;
import java.util.Optional;

import com.almo.caresync.model.entity.Paciente;

public interface PacienteService {
	Paciente salvar(Paciente Paciente);
	Optional<Paciente> obterPorId(Integer id);
	List<Paciente> listar();
	Paciente atualizar(Paciente Paciente);
	void deletar(Paciente entidade);
}
