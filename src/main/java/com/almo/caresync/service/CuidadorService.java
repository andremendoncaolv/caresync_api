package com.almo.caresync.service;

import java.util.List;
import java.util.Optional;

import com.almo.caresync.model.entity.Cuidador;

public interface CuidadorService {
	Cuidador autenticar(String email, String senha);
	Cuidador salvar(Cuidador cuidador);
	Optional<Cuidador> obterPorId(Integer id);
	List<Cuidador> listar();
	Cuidador atualizar(Cuidador cuidador);
	void deletar(Cuidador entidade);
}
