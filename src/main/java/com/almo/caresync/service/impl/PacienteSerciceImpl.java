package com.almo.caresync.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.almo.caresync.model.entity.Paciente;
import com.almo.caresync.model.entity.repository.PacienteRepository;
import com.almo.caresync.service.PacienteService;
import com.almo.caresync.service.exception.RegraNegocioException;

import jakarta.transaction.Transactional;

@Service
public class PacienteSerciceImpl implements PacienteService {

	@Autowired
	PacienteRepository pacienteRepository;

	@Override
	@Transactional
	public Paciente salvar(Paciente paciente) {
		return pacienteRepository.save(paciente);
	}

	@Override
	public Optional<Paciente> obterPorId(Integer id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	@Transactional
	public List<Paciente> listar() {
		List<Paciente> lista = new ArrayList<>();
		lista = pacienteRepository.findAll();
		if (lista.isEmpty()) {
			throw new RegraNegocioException("Não exitem cuidador(es) cadastrado(s)!");
		}
		return lista;
	}

	@Override
	public Paciente atualizar(Paciente Paciente) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletar(Paciente entidade) {
		// TODO Auto-generated method stub

	}

}
