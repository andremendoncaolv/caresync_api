package com.almo.caresync.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.almo.caresync.dto.CuidadorDTO;
import com.almo.caresync.model.entity.Cuidador;
import com.almo.caresync.service.CuidadorService;
import com.almo.caresync.service.exception.ErroAutenticacao;
import com.almo.caresync.service.exception.RegraNegocioException;

@RestController
@RequestMapping("/api/cuidador")
public class CuidadorController {

	@Autowired
	private CuidadorService cuidadorService;
	
	@PostMapping
	public ResponseEntity salvar(@RequestBody CuidadorDTO dto) {
		Cuidador cuidador = new Cuidador(dto.getNome(), dto.getEmail(), dto.getFoto(), dto.getSenha());
		try {
			Cuidador cuidadorSalvo = cuidadorService.salvar(cuidador);
			return new ResponseEntity(cuidadorSalvo, HttpStatus.CREATED);
		} catch (RegraNegocioException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PostMapping("/autenticar")
	public ResponseEntity autenticar(@RequestBody CuidadorDTO dto) {
		try {
			Cuidador cuidadorAutenticado = cuidadorService.autenticar(dto.getEmail(), dto.getSenha());
			return ResponseEntity.ok(cuidadorAutenticado);
		} catch (ErroAutenticacao e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping("/listar")
	public ResponseEntity listar(){
		List<Cuidador> lista = new ArrayList<>();
		try {
			lista = cuidadorService.listar();
			return ResponseEntity.ok(lista);
		} catch (RegraNegocioException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PutMapping("{id}")
	public ResponseEntity atualizar(@PathVariable("id") Integer id, @RequestBody CuidadorDTO dto) {
		return cuidadorService.obterPorId(id).map(entity -> { 
			try {
				Cuidador cuidador = converterDto(dto);
				cuidador.setIdCuidador(entity.getIdCuidador());
				cuidadorService.atualizar(cuidador);
				return ResponseEntity.ok(cuidador);
			} catch (RegraNegocioException e) {
				return ResponseEntity.badRequest().body(e.getMessage());
			}
		}).orElseGet(() -> new ResponseEntity<>("Cuidador nõa encontrado na base de dados", HttpStatus.BAD_REQUEST));
		
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity deletar(@PathVariable("id") Integer id) {
		return cuidadorService.obterPorId(id).map(entidade -> {
			cuidadorService.deletar(entidade);
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}).orElseGet(() -> new ResponseEntity<>("Lancamento não encontrado na base de Dados", HttpStatus.BAD_REQUEST));
		
	}

	private Cuidador converterDto(CuidadorDTO dto) {
		Cuidador cuidador = new Cuidador();
		cuidador.setIdCuidador(dto.getIdCuidador());
		cuidador.setNome(dto.getNome());
		cuidador.setEmail(dto.getEmail());
		cuidador.setSenha(dto.getSenha());
		cuidador.setFoto(dto.getFoto());
		return cuidador;
	}
}
