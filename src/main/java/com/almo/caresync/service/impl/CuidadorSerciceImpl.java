package com.almo.caresync.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.almo.caresync.model.entity.Cuidador;
import com.almo.caresync.model.entity.repository.CuidadorRepository;
import com.almo.caresync.service.CuidadorService;
import com.almo.caresync.service.exception.ErroAutenticacao;
import com.almo.caresync.service.exception.RegraNegocioException;

import jakarta.transaction.Transactional;


@Service
public class CuidadorSerciceImpl implements CuidadorService {
	
	@Autowired
	CuidadorRepository cuidadorRepository;

	@Override
	public Cuidador autenticar(String email, String senha) {
		Optional<Cuidador> cuidador = cuidadorRepository.findByEmail(email);
		if(!cuidador.isPresent()) {
			throw new ErroAutenticacao("O usuário não encontrado para o email informado");
		}
		if(!cuidador.get().getSenha().equals(senha)) {
			throw new ErroAutenticacao("Senha inválida");
		}
		return cuidador.get();
	}

	@Override
	@Transactional
	public Cuidador salvar(Cuidador cuidador) {
		validarEmail(cuidador.getEmail());
		return cuidadorRepository.save(cuidador);
	}
	
	public boolean validarEmail(String email){
		boolean exite = cuidadorRepository.existsByEmail(email);
		if(exite) {
			throw new RegraNegocioException("Já exite cuidador cadastrado com este email!");
		}
		return false;
	}
	
	@Override
	@Transactional
	public Optional<Cuidador> obterPorId(Integer id) {
		return cuidadorRepository.findById(id);
	}

	@Override
	@Transactional
	public List<Cuidador> listar() {
		List<Cuidador>  lista = new ArrayList<>(); 
		lista = cuidadorRepository.findAll();
		if(lista.isEmpty()) {
			throw new RegraNegocioException("Não exitem cuidador(es) cadastrado(s)!");
		}
		return lista;
	}

	@Override
	@Transactional
	public Cuidador atualizar(Cuidador cuidador) {
		Objects.requireNonNull(cuidador.getIdCuidador());
		return cuidadorRepository.save(cuidador);
	}

	@Override
	@Transactional
	public void deletar(Cuidador cuidador) {
		Objects.requireNonNull(cuidador.getIdCuidador());
		cuidadorRepository.delete(cuidador);
	}

}
