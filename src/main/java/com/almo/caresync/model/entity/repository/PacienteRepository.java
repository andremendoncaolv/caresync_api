package com.almo.caresync.model.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.almo.caresync.model.entity.Paciente;

public interface PacienteRepository  extends JpaRepository<Paciente, Integer> {

}
