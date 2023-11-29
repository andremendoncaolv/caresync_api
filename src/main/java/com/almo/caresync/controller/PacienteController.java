package com.almo.caresync.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.almo.caresync.dto.PacienteDTO;
import com.almo.caresync.model.entity.Cuidador;
import com.almo.caresync.model.entity.Paciente;
import com.almo.caresync.service.CuidadorService;
import com.almo.caresync.service.PacienteService;
import com.almo.caresync.service.exception.RegraNegocioException;

@RestController
@RequestMapping("/api/paciente")
public class PacienteController {

	@Autowired
	private PacienteService pacienteService;
	@Autowired
	private CuidadorService cuidadorService;
	
	@PostMapping
	public ResponseEntity salvar(@RequestBody PacienteDTO dto) {
		try {
			Paciente paciente = converterDto(dto);
			paciente = pacienteService.salvar(paciente);
			return new ResponseEntity(paciente, HttpStatus.CREATED);
		} catch (RegraNegocioException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping("/listar")
	public ResponseEntity listar(){
		List<Paciente> lista = new ArrayList<>();
		try {
			lista = pacienteService.listar();
			return ResponseEntity.ok(lista);
		} catch (RegraNegocioException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PutMapping("{id}")
	public ResponseEntity atualizar(@PathVariable("id") Integer id, @RequestBody PacienteDTO dto) {
		return pacienteService.obterPorId(id).map(entity -> { 
			try {
				Paciente paciente  = converterDto(dto);
				paciente.setIdPaciente(entity.getIdPaciente());
				pacienteService.atualizar(paciente);
				return ResponseEntity.ok(paciente);
			} catch (RegraNegocioException e) {
				return ResponseEntity.badRequest().body(e.getMessage());
			}
		}).orElseGet(() -> new ResponseEntity<>("Paciente não encontrado na base de dados", HttpStatus.BAD_REQUEST));
		
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity deletar(@PathVariable("id") Integer id) {
		return pacienteService.obterPorId(id).map(entidade -> {
			pacienteService.deletar(entidade);
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}).orElseGet(() -> new ResponseEntity<>("Paciente não encontrado na base de Dados", HttpStatus.BAD_REQUEST));
		
	}

	private Paciente converterDto(PacienteDTO dto) {
		Paciente paciente = new Paciente();
		paciente.setIdPaciente(dto.getIdPaciente());
		paciente.setNome(dto.getNome());
		paciente.setDataNascimento(dto.getDataNascimento());
		paciente.setGenero(dto.getGenero());
		paciente.setEndereco(dto.getEndereco());
		
		Optional<Cuidador> cuidador = cuidadorService.obterPorId(dto.getIdCuidador());
		paciente.setCuidador(cuidador.get());
		return paciente;
	}
}
